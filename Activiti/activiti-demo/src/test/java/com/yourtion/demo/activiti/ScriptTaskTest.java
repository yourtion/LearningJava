package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


@Slf4j
public class ScriptTaskTest {

    private static final String KEY = "my-process";

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"p-script-task1.bpmn20.xml"})
    public void testScriptTask1() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY);
        HistoryService historyService = activitiRule.getHistoryService();

        List<HistoricVariableInstance> historicVariableInstanceList = historyService
                .createHistoricVariableInstanceQuery()
                .processInstanceId(processInstance.getId())
                .orderByVariableName()
                .asc().list();

        for (HistoricVariableInstance historicVariableInstance : historicVariableInstanceList) {
            log.info("Variable = {}", historicVariableInstance);
        }
        log.info("Variables.size= {}", historicVariableInstanceList.size());
    }

    @Test
    @Deployment(resources = {"p-script-task2.bpmn20.xml"})
    public void testScriptTask2() {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("key1", 3);
        variables.put("key2", 5);

        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey(KEY, variables);
        HistoryService historyService = activitiRule.getHistoryService();

        List<HistoricVariableInstance> historicVariableInstanceList = historyService
                .createHistoricVariableInstanceQuery()
                .processInstanceId(processInstance.getId())
                .orderByVariableName()
                .asc().list();

        for (HistoricVariableInstance historicVariableInstance : historicVariableInstanceList) {
            log.info("Variable = {}", historicVariableInstance);
        }
        log.info("Variables.size= {}", historicVariableInstanceList.size());
    }
}
