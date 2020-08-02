package com.yourtion.java.c07;

import java.util.stream.Stream;

/**
 * @author Yourtion
 */
public class Parallel {

    static void parallelSum(int n) {
        Long ret = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("parallelSum: " + ret);
    }
}
