package com.yourtion.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @author yourtion
 */
@Slf4j
public class DemoMain {
    public static void main(String[] args) {
        log.info("启动我们的程序");
        // 创建流程引擎
        ProcessEngineConfiguration conf = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = conf.buildProcessEngine();
        String name = processEngine.getName();
        String version = ProcessEngine.VERSION;
        log.info("流程引擎名称【{}】. 版本版本【{}】",name, version);

        // 部署流程定义文件
        // 启动运行流程
        // 处理流程任务
        log.info("结束我们的程序");
    }

}
