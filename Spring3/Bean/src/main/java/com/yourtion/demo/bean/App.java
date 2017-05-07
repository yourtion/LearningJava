package com.yourtion.demo.bean;

import com.yourtion.demo.collections.Customer;
import com.yourtion.demo.innerBean.Person;
import com.yourtion.demo.java_config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yourtion on 07/05/2017.
 */
public class App {
    private static ApplicationContext context;

    public static void main( String[] args )
    {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        Person obj = (Person) context.getBean("Admin");
        System.out.println(obj);
    }
}
