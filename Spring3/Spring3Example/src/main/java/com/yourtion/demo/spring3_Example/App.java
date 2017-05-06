package com.yourtion.demo.spring3_Example;

/**
 * Created by yourtion on 06/05/2017.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yourtion.demo.helloworld.HelloWorld;

public class App {

    private static ApplicationContext context;

    public static void main( String[] args )
    {
        context = new ClassPathXmlApplicationContext("SpringBeans.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();
    }

}
