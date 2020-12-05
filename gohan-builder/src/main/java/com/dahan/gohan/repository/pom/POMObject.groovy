package com.dahan.gohan.repository.pom

import com.dahan.gohan.collection.exception.DependencyNotObtained
import com.dahan.gohan.repository.Repository
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.dependency.Plugin
import com.dahan.gohan.repository.dependency.Scope
import com.dahan.gohan.repository.initialize.alibaba.MavenOfAlibaba
import org.dom4j.Element
import org.dom4j.io.SAXReader

/*
 * Creates on 2020/12/4.
 */

/**
 * pom xml的解析对象
 * @author kevin
 */
class POMObject
{

    /** 父模块 **/
    private Dependency parent

    /** 自生的依赖 **/
    private Dependency self

    /** 依赖列表 **/
    private List<Dependency> dependencies

    /** dependenciesManager标签下的依赖列表 **/
    private List<Dependency> dependenciesManager

    /** 属性列表 **/
    private Properties _properties

    /** 插件列表 **/
    private List<Plugin> plugins

    POMObject() {}

    POMObject(File file, Dependency self)
    {
        this.self = self
        // 解析pom.xml
        parse(file)
    }

    /**
     * 解析XML
     */
    static void parse(File file)
    {
        def reader = new SAXReader()
        def document = reader.read(file)
        def rootElement = document.getRootElement()

        // 获取依赖节点
        def dependencies = rootElement.element("dependencies")
        def foo = parseDependenciesElement(dependencies)
        println()
    }

    /**
     * 解析dependencies节点
     * @param el
     */
    static List<Dependency> parseDependenciesElement(Element el)
    {
        if (el != null)
        {
            def dependencyInstances = []
            def dependencyElementArray = el.elements() as Element[]
            dependencyElementArray.each {
                def dependency = parseDependencyElement(it)
                if (dependency)
                {
                    dependencyInstances.add(dependency)
                }
            }
            return dependencyInstances
        }
        return null
    }

    /**
     * 解析dependency节点
     * @param el
     */
    static Dependency parseDependencyElement(Element el)
    {
        try
        {
            def groupNode = el.element("groupId").text
            def artifactNode = el.element("artifactId").text
            def versionNode = el.element("version").text
            def scopeNode = el.element("scope").text
            return new MavenOfAlibaba().getDependency(groupNode, artifactNode, versionNode, Scope.valueOf(scopeNode.toUpperCase()))
        } catch (Throwable ignore)
        {
            return null
        }
    }

    /**
     * 将pom对象转成pom.xml
     */
    void toPOMXml()
    {
        // todo 将pom对象转成pom.xml
    }

    Dependency getParent()
    {
        return parent
    }

    void setParent(Dependency parent)
    {
        this.parent = parent
    }

    List<Dependency> getDependencies()
    {
        return dependencies
    }

    void setDependencies(List<Dependency> dependencies)
    {
        this.dependencies = dependencies
    }

    List<Dependency> getDependenciesManager()
    {
        return dependenciesManager
    }

    void setDependenciesManager(List<Dependency> dependenciesManager)
    {
        this.dependenciesManager = dependenciesManager
    }

    Properties getProperties()
    {
        return _properties
    }

    void setProperties(Properties properties)
    {
        this._properties = properties
    }

    List<Plugin> getPlugins()
    {
        return plugins
    }

    void setPlugins(List<Plugin> plugins)
    {
        this.plugins = plugins
    }

    Dependency getSelf()
    {
        return self
    }

    void setSelf(Dependency self)
    {
        this.self = self
    }

}
