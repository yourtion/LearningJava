package com.yourtion.demo.activiti.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author yourtion
 */
@Slf4j
public class MyPayJavaDelegate implements JavaDelegate, Serializable {

    public static final String ERROR_FLAG = "errorFlag";
    public static final String KEY2 = "key2";

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("run MyPayDelegate {}", this);

        delegateExecution.getParent().setVariableLocal(KEY2, "value2");

        Object errorFlag = delegateExecution.getVariable(ERROR_FLAG);
        if (Objects.equals(errorFlag, true)) {
            throw new BpmnError("bpmnError");
        }
    }
}
