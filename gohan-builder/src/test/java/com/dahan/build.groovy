package com.dahan

import com.dahan.gohan.buildscript.SkateBuilder

class build extends SkateBuilder {

    static void main(String[] args) {

        new build().init()

    }

    void init() {

        groupId 'com.dahan'
        artifactId 'gohan'
        version '1.0.0.CARVING'

        repository {
        }

        optionals {
            optional group: 'org.codehaus.groovy', name: 'groovy-all', version: '3.0.4'
        }

    }

}