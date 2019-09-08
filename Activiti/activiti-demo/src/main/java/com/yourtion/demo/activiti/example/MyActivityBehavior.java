package com.yourtion.demo.activiti.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.delegate.ActivityBehavior;

/**
 * @author yourtion
 */
@Slf4j
public class MyActivityBehavior implements ActivityBehavior {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("run MyActivityBehavior {}", this);
    }
}
