package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;


@Slf4j
public class UserTaskTest {

    public static final String KEY = "my-process";

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"user-task.bpmn20.xml"})
    public void testUserTask() throws InterruptedException {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        TaskService taskService = activitiRule.getTaskService();

        Task task = taskService.createTaskQuery().taskCandidateUser("user1").singleResult();
        log.info("find by user1 task = {}", task);
        assertEquals("User Task", task.getName());

        task = taskService.createTaskQuery().taskCandidateUser("user2").singleResult();
        log.info("find by user2 task = {}", task);
        assertEquals("User Task", task.getName());

        task = taskService.createTaskQuery().taskCandidateGroup("group1").singleResult();
        log.info("find by group1 task = {}", task);
        assertEquals("User Task", task.getName());


        taskService.claim(task.getId(), "user2");
//        taskService.setAssignee(task.getId(), "user2");
        task = taskService.createTaskQuery().taskCandidateOrAssigned("user1").singleResult();
        log.info("find by user1 task = {}", task);
        assertNull(task);

        task = taskService.createTaskQuery().taskCandidateOrAssigned("user2").singleResult();
        log.info("find by user2 task = {}", task);
        assertEquals("User Task", task.getName());

        task = taskService.createTaskQuery().taskCandidateGroup("group1").singleResult();
        log.info("find by group1 task = {}", task);
        assertNull(task);
    }

    @Test
    @Deployment(resources = {"user-task2.bpmn20.xml"})
    public void testUserTask2() throws InterruptedException {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        TaskService taskService = activitiRule.getTaskService();

        Task task = taskService.createTaskQuery().taskCandidateUser("user1").singleResult();
        log.info("find by user1 task = {}", task);
        assertEquals("User Task", task.getName());

        taskService.complete(task.getId());
    }
}
