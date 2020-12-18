package com.eimoto;

public class HelloWorld
{
  
    public static void main(String[] args)
    {

        System.out.println("Hello World!");

        Person preson = new Person();

	    preson.setName("张三");
	    preson.setAge(18);

        System.out.println(preson.getName());
        System.out.println(preson.getAge());

    }

}
