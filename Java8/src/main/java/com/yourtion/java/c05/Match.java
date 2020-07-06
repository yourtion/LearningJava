package com.yourtion.java.c05;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Yourtion
 */
public class Match {
    private static List<Dish> menu = DishUtils.getMenu();
    private static List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);


    static void anyMatch() {
        // 检查谓词是否至少匹配一个元素
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is vegetarian friendly! ! ");
        }
    }

    static void allMatch() {
        // 检查谓词是否匹配所有元素
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("The menu healthy: " + isHealthy);
    }

    static void noneMatch() {
        // 检查谓词是否不匹配所有元素
        boolean isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println("The menu healthy: " + isHealthy);
    }

    static void findAny() {
        // 查找元素
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    static void findFirst() {
        // 查找第一个元素
        Optional<Integer> firstSquareDivisibleByThree = numbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();
        firstSquareDivisibleByThree.ifPresent(d -> System.out.println("firstSquareDivisibleByThree: " + d));
    }

    static void reduceSum() {
        // 元素求和
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(numbers + " sum is " + sum);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(numbers + " product is " + product);
    }

    static void reduceOptional() {
        // 元素求和
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        sum.ifPresent(s -> System.out.println(numbers + " sum is " + s));
    }

    static void reduceMaxMin() {
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(s -> System.out.println(numbers + " max is " + s));
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(s -> System.out.println(numbers + " min is " + s));
    }
}
