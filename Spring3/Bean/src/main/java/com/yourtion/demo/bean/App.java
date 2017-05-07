package com.yourtion.demo.bean;

import com.yourtion.demo.customerServices.CustomerService;
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

        System.out.println("Default: ");

        CustomerService CSDA = (CustomerService) context.getBean("CustomerServiceDefault");
        CSDA.setMessage("Message by CSDA");
        System.out.println("Message : "+ CSDA.getMessage());

        //retrieve it again
        CustomerService CSDB = (CustomerService) context.getBean("CustomerServiceDefault");
        System.out.println("Message : "+ CSDB.getMessage());

        System.out.println("Prototype: ");

        CustomerService CSPA = (CustomerService) context.getBean("CustomerServicePrototype");
        CSPA.setMessage("Message by CSPA");
        System.out.println("Message : "+ CSPA.getMessage());

        //retrieve it again
        CustomerService CSPB = (CustomerService) context.getBean("CustomerServicePrototype");
        System.out.println("Message : "+ CSPB.getMessage());
    }
}
