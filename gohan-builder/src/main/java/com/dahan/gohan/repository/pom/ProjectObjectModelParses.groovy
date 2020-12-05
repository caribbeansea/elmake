package com.dahan.gohan.repository.pom

import com.dahan.gohan.StringUtils
import com.dahan.gohan.collect.Maps
import com.dahan.gohan.repository.RepositoryUtils
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.dependency.Scope
import org.dom4j.Element
import org.dom4j.io.SAXReader

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
class ProjectObjectModelParses
{

    /**
     * 解析XML
     */
    static ProjectObjectModel parse(File file, ProjectObjectModel pom)
    {
        def reader = new SAXReader()
        def document = reader.read(file)
        def rootNode = document.getRootElement()

        pom.setSelf(parseDependencyNode(rootNode, pom, true))

        // 获取pom中的parent节点
        def parentNode = rootNode.element("parent")
        if (parentNode != null) pom.setParent(parseDependencyNode(parentNode, pom))

        // 获取pom中定义的properties
        def propertiesNode = rootNode.element("properties")
        if (propertiesNode != null)
        {
            parsePropertiesNode(propertiesNode, pom)
        }

        // 获取依赖管理（dependenciesManager）节点
        def dependenciesManagerNode = rootNode.element("dependenciesManager")
        if (dependenciesManagerNode != null)
        {
            def dependenciesInManagerNode = dependenciesManagerNode.element("dependencies")
            if (dependenciesInManagerNode != null)
            {
                pom.setDependenciesManager(parseDependenciesNode(dependenciesInManagerNode, pom))
            }
        }

        // 获取依赖节点
        def dependenciesNode = rootNode.element("dependencies")
        pom.setDependencies(parseDependenciesNode(dependenciesNode, pom))

        return pom
    }

    /**
     * 解析dependencies节点
     * @param el
     */
    static List<Dependency> parseDependenciesNode(Element el, ProjectObjectModel pom)
    {
        if (el != null)
        {
            def dependencyInstances = []
            def dependencyElementArray = el.elements() as Element[]
            dependencyElementArray.each {
                def dependency = parseDependencyNode(it, pom)
                if (dependency)
                {
                    dependencyInstances.add(dependency)
                }
            }
            return dependencyInstances
        }
        return null
    }

    static Dependency parseDependencyNode(Element el, ProjectObjectModel pom)
    {
        parseDependencyNode(el, pom, false)
    }

    /**
     * 解析dependency节点
     * @param el
     */
    static Dependency parseDependencyNode(Element el, ProjectObjectModel pom, boolean self)
    {
        try
        {
            def groupText = el.element("groupId").text
            def artifactText = el.element("artifactId").text

            def versionText = processVersionText(el.element("version"), pom)

            if (!self)
            {
                def scopeText = el.element("scope")
                if (scopeText == null)
                {
                    return RepositoryUtils.getDependency(groupText, artifactText, versionText)
                } else
                {
                    return RepositoryUtils.getDependency(groupText, artifactText, versionText,
                            Scope.valueOf(scopeText.text.toUpperCase()), pom.getSelf())
                }
            } else
            {
                return new Dependency(groupText, artifactText, versionText)
            }
        } catch (Throwable ignore)
        {
            return null
        }
    }

    /**
     * 处理version节点
     * @param versionText
     * @param pom
     * @return
     */
    private static String processVersionText(Element versionNode, ProjectObjectModel pom)
    {
        def versionText = StringUtils.emptyString
        if (versionNode != null)
        {
            versionText = versionNode.text
        }
        if (versionText.contains("\$"))
        {
            println(versionText)
        }
        return versionText
    }

    /**
     * 解析properties节点
     * @param el properties节点元素
     */
    static void parsePropertiesNode(Element el, ProjectObjectModel pom)
    {
        def els = el.elements() as List<Element>
        if (els != null && !els.isEmpty())
        {
            els.each { property ->
                pom.putSelfProperties(property.name, property.text.trim())
            }
        }
    }

}
