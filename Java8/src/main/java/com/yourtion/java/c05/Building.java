package com.yourtion.java.c05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Yourtion
 */
public class Building {
    static void value() {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    static void empty() {
        Stream<Object> emptyStream = Stream.empty();
        emptyStream.forEach(System.out::println);
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        homeValueStream.forEach(System.out::println);
        Stream<String> homeValueStream2 = Stream.ofNullable(homeValue);
        homeValueStream2.forEach(System.out::println);
    }

    static void array() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum: " + sum);
    }

    static void file() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file: " + uniqueWords);
    }

    static void iterate() {
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(t -> System.out.print(t + ","));
        System.out.println();
    }

    static void fib() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(t -> System.out.print(t + ","));
        System.out.println();
    }

    static void stream() {
        // Java 9 解法
        IntStream.iterate(0, n -> n < 30, n -> n + 4)
                .forEach(t -> System.out.print(t + ","));
        System.out.println();
        // filter 根本无法了解数字是否需要持续递增，因此它只能不停地执行过滤操作！
        IntStream.iterate(0, n -> n + 4)
                .filter(n -> n < 30)
                .limit(20)
                .forEach(t -> System.out.print(t + ","));
        System.out.println();
        // 使用takeWhile解决这个问题
        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 30)
                .forEach(t -> System.out.print(t + ","));
        System.out.println();
    }

    static void generate() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    static void fib2() {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib)
                .limit(10)
                .forEach(t -> System.out.print(t + ","));
        System.out.println();
    }
}
