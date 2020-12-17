object start {

  group("com.dahan")
  name("gohan")
  version("gh-1+0")

  object includes {
    include("com.alibaba:fastjson:2.6.66")
  }

  object tasks {
    task("mytask", () => {
      println("x")
    })
  }

}