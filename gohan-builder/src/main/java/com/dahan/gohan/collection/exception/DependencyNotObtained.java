package com.dahan.gohan.collection.exception;

/*
 * Creates on 2020/12/2.
 */

import com.dahan.gohan.Formprint;
import com.dahan.gohan.exception.GohanException;
import com.dahan.gohan.repository.dependency.Dependency;

/**
 * @author kevin
 */
public class DependencyNotObtained extends GohanException {

    private Dependency dependency;

    public DependencyNotObtained(Dependency dependency, String message) {
        super(dependency.getGroupId().concat(":").concat(dependency.getArtifactId())
                .concat(":").concat(dependency.getVersion()).concat(" ").concat(message));
        this.dependency = dependency;
        dependency.putSettings("cause", message);
    }

    public void addToForm(Formprint formprint) {
        formprint.addFormContent(dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion());
    }

    public String getGroupId() {
        return dependency.getGroupId();
    }

    public String getName() {
        return dependency.getArtifactId();
    }

    public String getVersion() {
        return dependency.getVersion();
    }

    public String getSelfCause() {
        return dependency.getSettings().get("cause");
    }

}
