package com.dahan.gohan.repository

/*
 * Creates on 2020/12/1.
 */

/**
 * @author kevin
 */
class Dependency {

    /** groupId **/
    private String groupId

    /** artifactId **/
    private String artifactId

    /** 版本好 **/
    private String version

    /** 运行返回 **/
    private Scope scope

    /** 其他配置信息 **/
    private LinkedHashMap<String, String> settings

    Dependency() {

    }

    /**
     * 提供最基本的依赖信息
     */
    Dependency(String groupId, String artifactId, String version) {
        this(groupId, artifactId, version, Scope.COMPILE)
    }

    /**
     * 提供依赖范围信息
     */
    Dependency(String groupId, String artifactId, String version, Scope scope) {
        this.groupId = groupId
        this.artifactId = artifactId
        this.version = version
        this.scope = scope
    }

    String getGroupId() {
        return groupId
    }

    void setGroupId(String groupId) {
        this.groupId = groupId
    }

    String getArtifactId() {
        return artifactId
    }

    void setArtifactId(String artifactId) {
        this.artifactId = artifactId
    }

    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
    }

    Scope getScope() {
        return scope
    }

    void setScope(Scope scope) {
        this.scope = scope
    }

    LinkedHashMap<String, String> getSettings() {
        return settings
    }

    void setSettings(LinkedHashMap<String, String> settings) {
        this.settings = settings
    }

}