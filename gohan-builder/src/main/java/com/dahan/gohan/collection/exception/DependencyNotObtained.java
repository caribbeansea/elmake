package com.dahan.gohan.collection.exception;

/*
 * Creates on 2020/12/2.
 */

import com.dahan.gohan.repository.Dependency;

/**
 * @author kevin
 */
public class DependencyNotObtained extends SuperException {

    public DependencyNotObtained(Dependency dependency, String message) {
        super(dependency.getGroupId().concat(":").concat(dependency.getArtifactId())
                .concat(":").concat(dependency.getVersion()).concat(" ").concat(message));
    }

}
