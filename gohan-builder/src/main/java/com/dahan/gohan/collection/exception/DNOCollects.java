package com.dahan.gohan.collection.exception;

/*
 * Creates on 2020/12/3.
 */

import com.dahan.gohan.Formprint;
import com.dahan.gohan.exception.ErrorCollects;
import com.dahan.gohan.repository.dependency.Dependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DependencyNotObtained异常收集器
 *
 * @author kevin
 */
public class DNOCollects extends ErrorCollects<DependencyNotObtained> {

    private static final Logger logger = LoggerFactory.getLogger(DNOCollects.class);

    @Override
    public void rollout() {
        if (!stack.isEmpty()) {
            Formprint formprint = new Formprint("groupId", "name", "type", "version", "cause");
            for (DependencyNotObtained dependencyNotObtained : stack) {
                formprint.addFormContent(dependencyNotObtained.getGroupId(), dependencyNotObtained.getName(),
                        dependencyNotObtained.getVersion(),
                        dependencyNotObtained.getType() == Dependency.getJAR() ? "jar" : "pom", dependencyNotObtained.getSelfCause());
            }
            logger.error(formprint.printf());
        }
    }

}
