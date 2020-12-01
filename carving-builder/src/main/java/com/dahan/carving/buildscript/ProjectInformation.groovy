package com.dahan.carving.buildscript

/*
 * Creates on 2020/12/1.
 */

/**
 * @author kevin
 */
class ProjectInformation {

    private String groupId

    private String artifactId

    private String version

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
}
