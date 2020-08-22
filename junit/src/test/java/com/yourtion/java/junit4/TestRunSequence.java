package com.yourtion.java.junit4;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

// 按字母人顺序执行
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRunSequence {

    @Test
    public void TestCase1() {
        assertEquals(2 + 2, 4);
    }

    @Test
    public void TestCase2() {
        assertEquals(2 + 2, 4);
    }

    @Test
    public void TestAa() {
        assertEquals("hello", "hi");
    }
}
