#!/bin/bash

curdir=$(pwd) # 当前脚本所在目录

function compile() {

  hellojar_src=$curdir/src # 源码文件夹
  hellojar_bin=$curdir/bin # 依赖库

  hellojar_build=$curdir/build/classes # 编译后的目录

  sources_list=$curdir/sources.list

  # 将hellojar的src目录下的所有java文件的名称存入到hellojar/src/sources.list文件中
  rm -rf $hellojar_build
  rm -rf $curdir/sources.list
  find $hellojar_src -name "*.java" > $sources_list
  cat $sources_list

  # 编译
  javac -d $hellojar_build -encoding utf-8 -sourcepath $hellojar_build @$sources_list

  # 打包jar
  cd build
  mkdir package
  cd classes
  jar cvf hellojar.jar *
  mv hellojar.jar ../package/hellojar.jar

  cd ..
  cd package
  tar -zxvf hellojar.jar

}

compile

ls -lar
