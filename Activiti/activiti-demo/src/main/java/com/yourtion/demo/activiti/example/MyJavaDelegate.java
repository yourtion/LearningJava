package com.yourtion.demo.activiti.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import java.io.Serializable;

/**
 * @author yourtion
 */
@Slf4j
public class MyJavaDelegate implements JavaDelegate, Serializable {

    private Expression name;
    private Expression desc;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        if (name != null) {
            Object value = name.getValue(delegateExecution);
            log.info("name = {}", value);
        }
        if (desc != null) {
            Object value = desc.getValue(delegateExecution);
            log.info("desc = {}", value);
        }
        log.info("run MyJavaDelegate {}", this);
    }
}
