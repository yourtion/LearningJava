package com.yourtion.demo.bean;

import com.yourtion.demo.collections.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yourtion on 07/05/2017.
 */
public class App {
    private static ApplicationContext context;

    public static void main( String[] args )
    {
        context = new ClassPathXmlApplicationContext(new String[] {"SpringBeans.xml"});

        System.out.println("List: ");

        Customer lists = (Customer) context.getBean("customerBean");
        System.out.println(lists.getLists().toString());

        System.out.println("Set: ");
        Customer sets = (Customer) context.getBean("customerBean");
        System.out.println(sets.getSets().toString());

        System.out.println("Map: ");
        Customer maps = (Customer) context.getBean("customerBean");
        System.out.println(maps.getMaps().toString());

        System.out.println("Prop: ");
        Customer pros = (Customer) context.getBean("customerBean");
        System.out.println(pros.getPros().toString());
    }
}
