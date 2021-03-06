package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.testng.collections.Maps;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;


@Slf4j
public class GatewayTaskTest {

    private static final String KEY = "my-process";

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"p-exclusive-gateway1.bpmn20.xml"})
    public void testExclusiveGateway1() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("score", 91);
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("精英", task.getName());
    }

    @Test
    @Deployment(resources = {"p-exclusive-gateway1.bpmn20.xml"})
    public void testExclusiveGateway2() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("score", 60);
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("普通", task.getName());
    }

    @Test
    @Deployment(resources = {"p-parallel-gateway1.bpmn20.xml"})
    public void testParallelGateway1() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        List<Task> taskList = activitiRule.getTaskService()
                .createTaskQuery()
                .processInstanceId(processInstance.getId())
                .listPage(0, 100);
        for (Task task : taskList) {
            log.info("task.name = {}", task.getName());
            activitiRule.getTaskService().complete(task.getId());
        }
        log.info("task.size = {}", taskList.size());
        assertEquals(2, taskList.size());

        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        log.info("task.name = {}", task.getName());
        assertEquals("订单完成", task.getName());
    }
}
