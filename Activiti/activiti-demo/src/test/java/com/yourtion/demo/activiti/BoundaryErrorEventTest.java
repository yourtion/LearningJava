package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


@Slf4j
public class BoundaryErrorEventTest extends PluggableActivitiTestCase {

    public static final String KEY = "reviewSalesLead";
    public static final String USER = "yourtion";
    public static final String GROUP1 = "accountancy";
    public static final String GROUP2 = "management";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Authentication.setAuthenticatedUserId(USER);
    }

    @Override
    protected void tearDown() throws Exception {
        Authentication.setAuthenticatedUserId(null);
        super.tearDown();
    }

    @Test
    @Deployment(resources = {"review-sales-lead.bpmn20.xml"})
    public void testReviewSalesLead() {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("details", "very interesting");
        variables.put("customerName", "me");

        ProcessInstance procId = runtimeService.startProcessInstanceByKey(KEY, variables);
        Task task = taskService.createTaskQuery().taskAssignee(USER).singleResult();
        log.info("ProcessInstance = {}, Task = {}", procId.getProcessDefinitionKey(), task.getName());
        assertEquals("Provide New Sales Lead", task.getName());

        taskService.complete(task.getId());
        Task ratingTask = taskService.createTaskQuery().taskCandidateGroup(GROUP1).singleResult();
        log.info("Task = {}", ratingTask.getName());
        assertEquals("Review Customer Rating", ratingTask.getName());
        Task profitabilityTask = taskService.createTaskQuery().taskCandidateGroup(GROUP2).singleResult();
        log.info("Task = {}", profitabilityTask.getName());
        assertEquals("Review Profitability", profitabilityTask.getName());

        variables = new HashMap<>();
        variables.put("notEnoughInformation", true);
        taskService.complete(profitabilityTask.getId(), variables);

        Task provideDetailsTask = taskService.createTaskQuery().taskAssignee(USER).singleResult();
        log.info("Task = {}", provideDetailsTask.getName());
        assertEquals("Provide Additional Details", provideDetailsTask.getName());

        taskService.complete(provideDetailsTask.getId());
        List<Task> reviewTasks = taskService.createTaskQuery().orderByTaskName().asc().list();
        log.info("Tasks = {}", reviewTasks);
        assertEquals("Review Customer Rating", reviewTasks.get(0).getName());
        assertEquals("Review Profitability", reviewTasks.get(1).getName());

        taskService.complete(reviewTasks.get(0).getId());
        variables.put("notEnoughInformation", false);
        taskService.complete(reviewTasks.get(1).getId(), variables);
        assertProcessEnded(procId.getProcessInstanceId());
    }
}
