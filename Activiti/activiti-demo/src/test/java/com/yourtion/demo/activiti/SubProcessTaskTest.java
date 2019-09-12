package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.testng.collections.Maps;

import java.util.Map;

import static com.yourtion.demo.activiti.example.MyPayJavaDelegate.ERROR_FLAG;
import static junit.framework.TestCase.assertEquals;


@Slf4j
public class SubProcessTaskTest {

    private static final String KEY = "my-process";

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"p-sub-process1.bpmn20.xml"})
    public void testSubProcess1() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("订单完成", task.getName());
    }

    @Test
    @Deployment(resources = {"p-sub-process1.bpmn20.xml"})
    public void testSubProcess2() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put(ERROR_FLAG, true);
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("异常处理", task.getName());
    }

}
