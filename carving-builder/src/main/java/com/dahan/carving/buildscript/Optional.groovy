package com.dahan.carving.buildscript

/*
 * Creates on 2020/12/1.
 */

/**
 * @author kevin
 */
class Optional {

    private String groupId

    private String artifactId

    private String version

    private LinkedHashMap<String, String> settings

    Optional() {

    }

    Optional(String groupId, String artifactId, String version) {
        this.groupId = groupId
        this.artifactId = artifactId
        this.version = version
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

    LinkedHashMap<String, String> getSettings() {
        return settings
    }

    void setSettings(LinkedHashMap<String, String> settings) {
        this.settings = settings
    }

}
