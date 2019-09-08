package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


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

}
