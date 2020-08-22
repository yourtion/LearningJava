package com.yourtion.java.junit4;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AssertTest {
    /**
     * 判断一个数是否为素数
     */
    public static Boolean Prime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testPrime() {
        int n = 7;
        assertTrue(AssertTest.Prime(n));
    }
}
