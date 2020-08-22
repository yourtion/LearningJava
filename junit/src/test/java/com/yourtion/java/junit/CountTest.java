package com.yourtion.java.junit;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountTest {
    //验证超时
    @Test(timeout = 100)
    public void testAdd() throws InterruptedException {
        Thread.sleep(101);
        new Count().add(1, 1);
    }

    //验证抛出异常
    @Test(expected = ArithmeticException.class)
    public void testDivision() {
        new Count().division(8, 0);
    }

    // 跳过该条用例
    @Ignore
    @Test
    public void testAdd2() {
        Count count = new Count();
        int result = count.add(2, 2);
        assertEquals(result, 5);
    }
}