package com.yourtion.demo.activiti.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.io.Serializable;

/**
 * @author yourtion
 */
@Slf4j
public class MyJavaDelegate implements JavaDelegate, Serializable {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("run MyJavaDelegate {}", this);
    }
}
