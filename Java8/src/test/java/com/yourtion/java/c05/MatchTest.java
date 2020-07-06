package com.yourtion.java.c05;

import org.junit.jupiter.api.Test;

class MatchTest {

    @Test
    void matchTest() {
        Match.anyMatch();
        Match.allMatch();
        Match.noneMatch();
    }

    @Test
    void findTest() {
        Match.findAny();
        Match.findFirst();
    }

    @Test
    void reduceTest() {
        Match.reduceSum();
        Match.reduceOptional();
        Match.reduceMaxMin();
    }

    @Test
    void tradTest() {
        Trad.q1();
        Trad.q2();
        Trad.q3();
        Trad.q4();
        Trad.q5();
        Trad.q6();
        Trad.q7();
        Trad.q8();
    }

}