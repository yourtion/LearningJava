package com.yourtion.java.c05;

import org.junit.jupiter.api.Test;

class NumericalTest {

    @Test
    void testNum() {
        Numerical.toInt();
        Numerical.optionalInt();
        Numerical.range();
    }

    @Test
    void pythagoreanTriplesTest() {
        Numerical.pythagoreanTriples();
    }

}