package com.dahan

import com.dahan.gohan.buildscript.GohanBuilder

class build extends GohanBuilder {

    static void main(String[] args) {

        new build().init()

    }

    void init() {
        groupId 'com.dahan'
        artifactId 'gohan'
        version '1.0.0.CARVING'

        optionals {
            optional group: 'org.codehaus.groovy', artifact: 'groovy-all', version: '3.0.4'
        }

    }

}