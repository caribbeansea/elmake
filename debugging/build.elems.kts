group("com.dahan")
name("debugging")
version("1.0.0")

includes {
    // 引入fastjson依赖，在根路径的build.elems.kts下引入的依赖是全局可用的
    include("com.alibaba:fastjson:2.6.66")
}