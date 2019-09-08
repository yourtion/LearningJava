package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ActivitiEngineAgenda;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;


@Slf4j
public class ServiceTaskTest {

    private static final String KEY = "my-process";

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"p-service-task1.bpmn20.xml"})
    public void testServiceTask1() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        HistoryService historyService = activitiRule.getHistoryService();
        List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery()
                .orderByHistoricActivityInstanceEndTime()
                .asc().list();

        for (HistoricActivityInstance activityInstance : activityInstances) {
            log.info("activity = {}", activityInstance);
        }
        assertEquals(3, activityInstances.size());
    }

    @Test
    @Deployment(resources = {"p-service-task2.bpmn20.xml"})
    public void testServiceTask2() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        HistoryService historyService = activitiRule.getHistoryService();
        List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery()
                .orderByHistoricActivityInstanceEndTime()
                .asc().list();

        for (HistoricActivityInstance activityInstance : activityInstances) {
            log.info("activity = {}", activityInstance);
        }
        assertEquals(2, activityInstances.size());

        Execution execution = activitiRule.getRuntimeService().createExecutionQuery().activityId("someTask").singleResult();
        log.info("execution = {}", execution);
        assertNotNull(execution);

        ManagementService managementService = activitiRule.getManagementService();
        managementService.executeCommand(commandContext -> {
            ActivitiEngineAgenda agenda = commandContext.getAgenda();
            agenda.planTakeOutgoingSequenceFlowsOperation((ExecutionEntity) execution, false);
            return null;
        });

        activityInstances = historyService.createHistoricActivityInstanceQuery()
                .orderByHistoricActivityInstanceEndTime()
                .asc().list();

        for (HistoricActivityInstance activityInstance : activityInstances) {
            log.info("activity = {}", activityInstance);
        }
        assertEquals(3, activityInstances.size());
    }

    @Test
    @Deployment(resources = {"p-service-task3.bpmn20.xml"})
    public void testServiceTask3() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("desc", "the test java delegate");
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
        assertTrue(processInstance.isEnded());
    }
}
