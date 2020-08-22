package com.yourtion.java.junit4;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestFixture {
    //在当前测试类开始时运行。
    @BeforeClass
    public static void beforeClass() {
        System.out.println("-------------------beforeClass");
    }

    //在当前测试类结束时运行。
    @AfterClass
    public static void afterClass() {
        System.out.println("-------------------afterClass");
    }

    //每个测试方法运行之前运行
    @Before
    public void before() {
        System.out.println("=====before");
    }

    //每个测试方法运行之后运行
    @After
    public void after() {
        System.out.println("=====after");
    }

    @Test
    public void testAdd1() {
        int result = new Count().add(5, 3);
        assertEquals(8, result);
        System.out.println("test Run testAdd1");
    }

    @Test
    public void testAdd2() {
        int result = new Count().add(15, 13);
        assertEquals(28, result);
        System.out.println("test Run testAdd2");
    }
}
