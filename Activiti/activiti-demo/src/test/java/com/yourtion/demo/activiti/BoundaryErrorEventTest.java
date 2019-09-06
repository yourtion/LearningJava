package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.util.HashMap;
import java.util.List;


@Slf4j
public class BoundaryErrorEventTest extends PluggableActivitiTestCase {

    private static final String KEY = "reviewSalesLead";
    private static final String USER = "yourtion";
    private static final String GROUP1 = "accountancy";
    private static final String GROUP2 = "management";

    @Before
    public void setUp() {
        Authentication.setAuthenticatedUserId(USER);
    }

    @After
    public void tearDown() {
        Authentication.setAuthenticatedUserId(null);
    }

    @Deployment(resources = {"p-review-sales-lead.bpmn20.xml"})
    public void testReviewSalesLead() {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("details", "very interesting");
        variables.put("customerName", "me");

        ProcessInstance procId = runtimeService.startProcessInstanceByKey(KEY, variables);
        Task task = taskService.createTaskQuery().taskAssignee(USER).singleResult();
        log.info("ProcessInstance = {}, Task = {}", procId.getProcessDefinitionKey(), task.getName());
        Assert.assertEquals("Provide New Sales Lead", task.getName());

        taskService.complete(task.getId());
        Task ratingTask = taskService.createTaskQuery().taskCandidateGroup(GROUP1).singleResult();
        log.info("Task = {}", ratingTask.getName());
        Assert.assertEquals("Review Customer Rating", ratingTask.getName());
        Task profitabilityTask = taskService.createTaskQuery().taskCandidateGroup(GROUP2).singleResult();
        log.info("Task = {}", profitabilityTask.getName());
        Assert.assertEquals("Review Profitability", profitabilityTask.getName());

        variables = new HashMap<>();
        variables.put("notEnoughInformation", true);
        taskService.complete(profitabilityTask.getId(), variables);

        Task provideDetailsTask = taskService.createTaskQuery().taskAssignee(USER).singleResult();
        log.info("Task = {}", provideDetailsTask.getName());
        Assert.assertEquals("Provide Additional Details", provideDetailsTask.getName());

        taskService.complete(provideDetailsTask.getId());
        List<Task> reviewTasks = taskService.createTaskQuery().orderByTaskName().asc().list();
        log.info("Tasks = {}", reviewTasks);
        Assert.assertEquals("Review Customer Rating", reviewTasks.get(0).getName());
        Assert.assertEquals("Review Profitability", reviewTasks.get(1).getName());

        taskService.complete(reviewTasks.get(0).getId());
        variables.put("notEnoughInformation", false);
        taskService.complete(reviewTasks.get(1).getId(), variables);
        assertProcessEnded(procId.getProcessInstanceId());
    }
}
