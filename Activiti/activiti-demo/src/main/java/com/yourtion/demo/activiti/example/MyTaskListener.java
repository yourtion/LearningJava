package com.yourtion.demo.activiti.example;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * @author yourtion
 */
@Slf4j
public class MyTaskListener implements TaskListener {
    private static final String EVENT_CREATE = "create";
    private static final String EVENT_COMPLETE = "complete";

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();

        if (StringUtils.equals(EVENT_CREATE, eventName)) {
            log.info("config by listener");

            delegateTask.addCandidateUsers(Lists.newArrayList("user1", "user2"));
            delegateTask.addCandidateGroup("group1");

            delegateTask.setVariable("key1", "value1");
            delegateTask.setDueDate(DateTime.now().plusDays(3).toDate());
        } else if (StringUtils.equals(EVENT_COMPLETE, eventName)) {
            log.info("task complete");
        }
    }
}
