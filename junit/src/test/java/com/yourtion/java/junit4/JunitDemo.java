package com.yourtion.java.junit4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JunitDemo {
    @Test
    public void myFirstTest() {
        assertEquals(2 + 2, 4);
    }
}