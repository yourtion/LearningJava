package com.yourtion.demo.activiti.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author yourtion
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MyJavaBean implements Serializable {

    private String name;

    public String getName() {
        log.info("run getName name: {}", name);
        return name;
    }

    public void sayHello() {
        log.info("run sayHello");
    }

}
