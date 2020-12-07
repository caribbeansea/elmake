package com.dahan

import com.dahan.skate.buildscript.SkateBuilder

class build extends SkateBuilder {

    static void main(String[] args) {

        new build().init()

    }

    void init() {

        groupId 'com.dahan'
        artifactId 'skate'
        version '1.0.0.CARVING'

        optionals {
            optional group: 'org.codehaus.groovy', name: 'groovy-all', version: '3.0.4'
        }

    }

}