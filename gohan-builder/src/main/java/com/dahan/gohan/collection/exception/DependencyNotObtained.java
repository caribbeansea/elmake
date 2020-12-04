package com.dahan.gohan.collection.exception;

/*
 * Creates on 2020/12/2.
 */

import com.dahan.gohan.Formprint;
import com.dahan.gohan.MultiLanguage;
import com.dahan.gohan.StringUtils;
import com.dahan.gohan.exception.GohanException;
import com.dahan.gohan.repository.dependency.Dependency;

/**
 * @author kevin
 */
public class DependencyNotObtained extends GohanException {

    private final Dependency dependency;

    private final int type;

    private final String selfCause;

    public DependencyNotObtained(Dependency dependency, int type) {
        this(dependency, type, MultiLanguage.ERROR_DEPENDENCY_CANNOT_IMPORT);
    }

    public DependencyNotObtained(Dependency dependency, int type, String message) {
        super(dependency.getGroupId().concat(":").concat(dependency.getArtifactId())
                .concat(":").concat(dependency.getVersion()).concat(" ").concat(message));
        this.dependency = dependency;
        this.type = type;
        this.selfCause = message;
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
        return selfCause;
    }

    public int getType() {
        return type;
    }
}
