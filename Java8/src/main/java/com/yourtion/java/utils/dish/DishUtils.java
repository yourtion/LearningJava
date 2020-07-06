package com.yourtion.java.utils.dish;

import java.util.Arrays;
import java.util.List;

/**
 * Dish 工具类
 *
 * @author Yourtion
 */
public class DishUtils {
    public static List<Dish> getMenu() {
        return Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static List<Dish> getSpecialMenu() {
        return Arrays.asList(
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER));
    }

    public static void printDishList(List<Dish> list) {
        list.forEach(Dish::print);
    }
}
