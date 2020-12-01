package com.dahan.carving.buildscript

class build extends BuildCarving {

    static void main(String[] args) {

        new build().init()

    }

    void init() {
        groupId 'com.dahan'
        artifactId 'carving'
        version '1.0.0.CARVING'

        optionals {
            optional group: 'org.codehaus.groovy', artifact: 'groovy-all', version: '3.0.4'
        }

    }

}