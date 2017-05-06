package com.yourtion.demo.helloworld;

/**
 * Created by yourtion on 06/05/2017.
 */
public class HelloWorld {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("The first Spring 3: hello " + name );
    }
}
