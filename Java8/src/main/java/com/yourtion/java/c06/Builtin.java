package com.yourtion.java.c06;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * @author Yourtion
 */
public class Builtin {
    private static final List<Dish> menu = DishUtils.getMenu();

    static void count() {
        Long howMany = menu.stream().collect(counting());
        System.out.println("howMany: " + howMany);
    }

    static void max() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        System.out.println("mostCaloriesDish: " + mostCaloriesDish);
    }

    static void sum() {
        Integer totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories: " + totalCalories);
    }

    static void avg() {
        Double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("avgCalories: " + avgCalories);
    }

    static void summary() {
        IntSummaryStatistics avgCalories = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("avgCalories: " + avgCalories);
    }

    static void join() {
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("shortMenu: " + shortMenu);
    }


}
