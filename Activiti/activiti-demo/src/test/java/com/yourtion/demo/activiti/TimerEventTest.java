package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


@Slf4j
public class TimerEventTest {

    public static final String KEY = "my-process";

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"timer-boundary.bpmn20.xml"})
    public void testTimerBoundary() throws InterruptedException {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        List<Task> tasks = activitiRule.getTaskService().createTaskQuery().listPage(0, 100);
        for (Task task : tasks) {
            log.info("task.name = {}", task.getName());
        }
        log.info("tasks.size = {}", tasks.size());
        assertEquals(1, tasks.size());
        assertEquals("Common Task", tasks.get(0).getName());

        Thread.sleep(1000*10);

        tasks = activitiRule.getTaskService().createTaskQuery().listPage(0, 100);
        for (Task task : tasks) {
            log.info("task.name = {}", task.getName());
        }
        log.info("tasks.size = {}", tasks.size());
        assertEquals(1, tasks.size());
        assertEquals("Timeout Task", tasks.get(0).getName());
    }
}
