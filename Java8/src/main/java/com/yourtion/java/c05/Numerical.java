package com.yourtion.java.c05;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Yourtion
 */
public class Numerical {
    private static List<Dish> menu = DishUtils.getMenu();

    static void toInt() {
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("menu calories sum: " + sum);
    }

    static void optionalInt() {
        OptionalInt max = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println("menu calories max: " + max.orElse(0));
    }

    static void range() {
        // rangeClosed 包含结束值
        IntStream intStream = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(intStream.count());
        // range 不包含结束值
        IntStream intStream2 = IntStream.range(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(intStream2.count());
    }

    static void pythagoreanTriples() {
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples
                .limit(5)
                .forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));
    }
}
