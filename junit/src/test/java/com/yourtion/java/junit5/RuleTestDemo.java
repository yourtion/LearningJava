package com.yourtion.java.junit5;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class RuleTestDemo {
    //使用Timeout这个Rule
    @Rule
    public Timeout timeout = new Timeout(1000);

    //使用自定义Rule，
    @Rule
    public MethodNameRule methodNameRule = new MethodNameRule();

    @Test
    public void testMethod1() throws Exception {
        Thread.sleep(1001);
    }

    @Test
    public void testMethod2() throws Exception {
        Thread.sleep(999);
    }
}
