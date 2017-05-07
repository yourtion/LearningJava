package com.yourtion.demo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yourtion on 07/05/2017.
 */
public class App {
    private static ApplicationContext context;

    public static void main( String[] args )
    {
        context = new ClassPathXmlApplicationContext("SpringBeans.xml");

        FileNameGenerator obj = (FileNameGenerator) context.getBean("FileNameGenerator");
        obj.printFileName();
    }
}
