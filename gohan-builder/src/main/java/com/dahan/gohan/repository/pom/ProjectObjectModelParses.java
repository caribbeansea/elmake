package com.dahan.gohan.repository.pom;

import com.dahan.gohan.Assert;
import com.dahan.gohan.Matches;
import com.dahan.gohan.StringUtils;
import com.dahan.gohan.exception.PropertyNotFoundException;
import com.dahan.gohan.repository.RepositoryUtils;
import com.dahan.gohan.repository.dependency.Dependency;
import com.dahan.gohan.repository.dependency.Scope;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* ************************************************************************
 *
 * Copyright (C) 2020 2B键盘 All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/*
 * Creates on 2020/12/5.
 */

/**
 * @author kevin
 */
public class ProjectObjectModelParses
{
    private ProjectObjectModelParses()
    {
    }

    /**
     * 解析XML
     */
    public static void parse(File file, ProjectObjectModel pom) throws DocumentException
    {

        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element rootNode = document.getRootElement();

        // 获取pom中的parent节点
        Element parentNode = rootNode.element("parent");
        if (parentNode != null)
        {
            pom.setParent(parseDependencyNode(parentNode, pom));
        }

        // 解析自生
        pom.setSelf(Assert.requiredNonNull(parseDependencyNode(rootNode, pom, false, true)));

        // 获取pom中定义的properties
        Element propertiesNode = rootNode.element("properties");
        if (propertiesNode != null)
        {
            parsePropertiesNode(propertiesNode, pom);
        }

        // 获取依赖管理（dependenciesManager）节点
        Element dependenciesManagerNode = rootNode.element("dependencyManagement");
        if (dependenciesManagerNode != null)
        {
            Element dependenciesInManagerNode = dependenciesManagerNode.element("dependencies");
            if (dependenciesInManagerNode != null)
            {
                List<Dependency> dependencies = parseDependenciesNode(dependenciesInManagerNode, pom, true);
                pom.setDependencyManager(dependencies.isEmpty() ? null : dependencies);
            }

        }

        // 获取依赖节点
        Element dependenciesNode = rootNode.element("dependencies");
        pom.setDependencies(parseDependenciesNode(dependenciesNode, pom));

    }

    public static List<Dependency> parseDependenciesNode(Element el, final ProjectObjectModel pom)
    {
        return parseDependenciesNode(el, pom, false);
    }

    /**
     * 解析dependencies节点
     */
    @SuppressWarnings("unchecked")
    public static List<Dependency> parseDependenciesNode(Element el, final ProjectObjectModel pom, boolean dm)
    {
        if (el != null)
        {
            final List<Dependency> dependencyInstances = new ArrayList<>();
            List<Element> dependencyElementArray = el.elements();
            for (Element element : dependencyElementArray)
            {
                Dependency dependency = parseDependencyNode(element, pom, dm, false);
                if (dependency != null)
                {
                    dependencyInstances.add(dependency);
                }
            }
            return dependencyInstances;
        }

        return null;
    }

    public static Dependency parseDependencyNode(Element el, ProjectObjectModel pom)
    {
        return parseDependencyNode(el, pom, false, false);
    }

    /**
     * 解析dependency节点
     */
    public static Dependency parseDependencyNode(Element el, ProjectObjectModel pom, boolean dependencyManager, boolean self)
    {
        try
        {

            String groupId;
            String artifactId = el.element("artifactId").getText();

            Element groupIdNode = el.element("groupId");

            if(groupIdNode != null)
            {
                groupId = groupIdNode.getText();
            } else
            {
                groupId = pom.getParent().getGroupId();
            }

            String versionText;
            Element versionNode = el.element("version");

            if (versionNode != null)
            {
                versionText = (processVersionText(versionNode, groupId, artifactId, pom));
            } else
            {
                return pom.findDependency(groupId, artifactId);
            }


            if (!self)
            {
                Element scopeText = el.element("scope");
                if (scopeText == null)
                {
                    return RepositoryUtils.getDependency(groupId, artifactId, versionText, dependencyManager);
                } else
                {
                    return RepositoryUtils.getDependency(groupId, artifactId, versionText,
                            Scope.valueOf(scopeText.getText().toUpperCase()), pom.getSelf(), dependencyManager);
                }

            } else
            {
                return new Dependency(groupId, artifactId, versionText);
            }

        } catch (Throwable ignore)
        {
            return null;
        }

    }

    /**
     * 处理version节点
     */
    private static String processVersionText(Element versionNode, String groupId, String artifactId, final ProjectObjectModel pom)
    {
        String versionText = versionNode.getText();

        if (versionText.contains("$"))
        {
            versionText = Matches.findAndReplace(versionText, "\\$\\{(.*?)}", it ->
            {
                String versionString = pom.findProperty(it);
                if (StringUtils.isEmpty(versionString))
                {
                    throw new PropertyNotFoundException("(", groupId, ":", artifactId, ":", versionString, ")", "中的版本属性未找到");
                }

                return versionString;
            });
        } else
        {
            if (versionText.contains("[") || versionText.contains(")") || versionText.contains(","))
            {
                @NotNull
                String parseArgs = Arrays.stream(versionText.split("\\),")).findFirst().orElseGet(null);
                if (parseArgs.contains(","))
                {
                    parseArgs = parseArgs.split(",")[0];
                }
                versionText = StringUtils.replaceEmpty(parseArgs, '[', ']', '(', ')', ',');
            }
        }

        return versionText;
    }

    /**
     * 解析properties节点
     *
     * @param el properties节点元素
     */
    @SuppressWarnings("unchecked")
    public static void parsePropertiesNode(Element el, final ProjectObjectModel pom)
    {
        List<Element> els = el.elements();
        if (els != null && !els.isEmpty())
        {
            for (Element element : els)
            {
                pom.putSelfProperties(element.getName(), element.getText().trim());
            }
        }

    }

}
