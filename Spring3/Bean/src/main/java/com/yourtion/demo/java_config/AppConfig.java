package com.yourtion.demo.java_config;

import com.yourtion.demo.innerBean.Customer;
import com.yourtion.demo.innerBean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yourtion on 07/05/2017.
 */
@Configuration
public class AppConfig {

    @Bean(name="Admin")
    public Person getAdmin(){
        return new Person();
    }
}
