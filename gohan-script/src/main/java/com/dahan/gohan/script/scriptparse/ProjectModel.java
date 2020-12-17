package com.dahan.gohan.script.scriptparse;

import com.dahan.gohan.repository.GohanDependency;

/**
 * @author tiansheng
 */
public class ProjectModel
{

    private String groupId;

    private String artifactId;

    private String version;

    private GohanDependency parent;

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public void setArtifactId(String artifactId)
    {
        this.artifactId = artifactId;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

}
