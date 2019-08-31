package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author yourtion
 */
@Slf4j
public class DemoMain {
    public static void main(String[] args) {
        log.info("启动我们的程序");
        // 创建流程引擎
        ProcessEngine processEngine = getProcessEngine();
        // 部署流程定义文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);
        // 启动运行流程
        ProcessInstance processInstance = getProcessInstance(processEngine, processDefinition);
        // 处理流程任务
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().list();
        log.info("待处理任务数量【{}】", list.size());
        for (Task task : list) {
            log.info("待处理任务【{}】", task.getName());
        }

        log.info("结束我们的程序");
    }

    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        log.info("启动流程 【{}】", processInstance.getProcessDefinitionKey());
        return processInstance;
    }

    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("second_approve.bpmn20.xml");
        Deployment deployment = deploymentBuilder.deploy();
        String deploymentId = deployment.getId();
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
        log.info("流程定义文件 【{}】， 流程ID 【{}】", processDefinition.getName(), processDefinition.getId());
        return processDefinition;
    }

    private static ProcessEngine getProcessEngine() {
        ProcessEngineConfiguration conf = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = conf.buildProcessEngine();
        String name = processEngine.getName();
        String version = ProcessEngine.VERSION;
        log.info("流程引擎名称【{}】. 版本版本【{}】", name, version);
        return processEngine;
    }

}
