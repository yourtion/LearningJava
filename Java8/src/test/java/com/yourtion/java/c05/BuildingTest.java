package com.yourtion.java.c05;

import org.junit.jupiter.api.Test;

class BuildingTest {

    @Test
    void valueTest() {
        System.out.println("value");
        Building.value();
        System.out.println("empty");
        Building.empty();
        System.out.println("array");
        Building.array();
    }

    @Test
    void fileTest() {
        Building.file();
    }

    @Test
    void iterateTest() {
        System.out.println("iterate");
        Building.iterate();
        System.out.println("fib");
        Building.fib();
    }

    @Test
    void streamTest() {
        Building.stream();
    }

    @Test
    void generate() {
        Building.generate();
    }

    @Test
    void fib2Test() {
        Building.fib2();
    }
}