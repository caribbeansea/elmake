package com.dahan.gohan.collection.exception;

/*
 * Creates on 2020/12/3.
 */

import com.dahan.gohan.Formprint;
import com.dahan.gohan.exception.ErrorCollects;

import javax.swing.*;
import java.util.Map;

/**
 * DependencyNotObtained异常收集器
 *
 * @author kevin
 */
public class DNOCollects extends ErrorCollects<DependencyNotObtained> {

    @Override
    public void rollout(String... values) {
        Formprint formprint = new Formprint("groupId", "name", "version", "cause");
        for (DependencyNotObtained dependencyNotObtained : stack) {
            formprint.addFormContent(dependencyNotObtained.getGroupId(), dependencyNotObtained.getName(),
                    dependencyNotObtained.getVersion(), dependencyNotObtained.getSelfCause());
        }
        formprint.printf();
    }

}
