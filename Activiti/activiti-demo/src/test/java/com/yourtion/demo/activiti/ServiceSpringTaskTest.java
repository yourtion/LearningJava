package com.yourtion.demo.activiti;

import com.yourtion.demo.activiti.example.MyJavaDelegate;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti-context.xml")
public class ServiceSpringTaskTest {

    private static final String KEY = "my-process";

    @Resource
    @Rule
    public ActivitiRule activitiRule;

    @Test
    @Deployment(resources = {"p-service-task4.bpmn20.xml"})
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
    @Deployment(resources = {"p-service-task4.bpmn20.xml"})
    public void testServiceTask2() {
        Map<String, Object> variables = new HashMap<>();
        MyJavaDelegate myJavaDelegate = new MyJavaDelegate();
        log.info("myJavaDelegate = {}", myJavaDelegate);
        variables.put("myJavaDelegate", myJavaDelegate);
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
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
