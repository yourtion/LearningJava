package com.yourtion.java.junit5;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class MethodNameRule implements TestRule {

    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                //在测试方法运行之前做一些事情，在base.evaluate()之前
                String className = description.getClassName();
                String methodName = description.getMethodName();

                base.evaluate();  //运行测试方法

                //在测试方法运行之后做一些事情，在base.evaluate()之后
                System.out.println("Class name:" + className + ", method name: " + methodName);
            }
        };
    }
}
