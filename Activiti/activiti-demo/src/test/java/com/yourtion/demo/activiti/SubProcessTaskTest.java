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

import static com.yourtion.demo.activiti.example.MyPayJavaDelegate.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;


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

        Map<String, Object> vars = activitiRule.getRuntimeService().getVariables(processInstance.getId());
        log.info("vars = {}", vars);
        Object v2 = vars.get(KEY2);
        assertNull(v2);
    }

    @Test
    @Deployment(resources = {"p-sub-process2.bpmn20.xml"})
    public void testSubProcess3() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put(ERROR_FLAG, true);
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("异常处理", task.getName());

        Map<String, Object> vars = activitiRule.getRuntimeService().getVariables(processInstance.getId());
        log.info("vars = {}", vars);
        Object v2 = vars.get(KEY2);
        assertEquals("value2", v2);
    }

    @Test
    @Deployment(resources = {"p-sub-process3.bpmn20.xml", "p-sub-process4.bpmn20.xml"})
    public void testSubProcess4() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("订单完成", task.getName());

        // 只有 KEY1 可以传出来
        Map<String, Object> vars = activitiRule.getRuntimeService().getVariables(processInstance.getId());
        log.info("vars = {}", vars);
        Object v3 = vars.get(KEY3);
        assertNull(v3);
        Object v1 = vars.get(KEY1);
        assertEquals("value1", v1);
    }

    @Test
    @Deployment(resources = {"p-sub-process3.bpmn20.xml", "p-sub-process4.bpmn20.xml"})
    public void testSubProcess5() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put(ERROR_FLAG, true);
        variables.put("key0", "value0");
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("异常处理", task.getName());

        // 没有数据可以出来
        Map<String, Object> vars = activitiRule.getRuntimeService().getVariables(processInstance.getId());
        log.info("vars = {}", vars);
        Object v3 = vars.get(KEY3);
        assertNull(v3);
        Object v1 = vars.get(KEY1);
        assertNull(v1);
        Object v2 = vars.get(KEY2);
        assertNull(v2);
    }

}
