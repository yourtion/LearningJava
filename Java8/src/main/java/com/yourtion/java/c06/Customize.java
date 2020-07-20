package com.yourtion.java.c06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * @author Yourtion
 */
public class Customize {

    private static boolean isPrime(List<Integer> primes, Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static void primeDemo1(int n) {
        Map<Boolean, List<Integer>> ret = IntStream.rangeClosed(2, n)
                .boxed()
                .collect(new PrimeNumbersCollector());
        System.out.println(ret);
    }

    public static void primeDemo2(int n) {
        Map<Boolean, List<Integer>> ret = IntStream.rangeClosed(2, n)
                .boxed()
                .collect(
                        () -> new HashMap<Boolean, List<Integer>>(2) {{
                            put(true, new ArrayList<>());
                            put(false, new ArrayList<>());
                        }},
                        (acc, candidate) -> {
                            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
                        },
                        (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        });
        System.out.println(ret);
    }

    static class PrimeNumbersCollector implements
            Collector<Integer,
                    Map<Boolean, List<Integer>>,
                    Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            // 从一个有两个空List的Map开始收集过程
            return () -> new HashMap<>(2) {{
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }};
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (acc, candidate) -> {
                // 根据 isPrime 方法返回值，从Map中取质数或非质数列表，把呗检测数加进去
                acc.get(
                        // 找到质数列表传递给 isPrime 方法
                        isPrime(acc.get(true), candidate))
                        .add(candidate);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (map1, map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }

    }
}

