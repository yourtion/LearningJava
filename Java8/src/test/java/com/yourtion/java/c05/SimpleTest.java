package com.yourtion.java.c05;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.yourtion.java.c05.Simple.*;

class SimpleTest {

    private List<Dish> menu = DishUtils.getMenu();

    @Test
    void distinctTest() {
        List<Integer> ret = distinct(Arrays.asList(1, 2, 1, 3, 3, 2, 4));
        System.out.println(ret);
    }

    @Test
    void limitTest() {
        List<Dish> dishes = limit(menu);
        System.out.println(dishes);
    }

    @Test
    void skipTest() {
        List<Dish> dishes = skip(menu);
        System.out.println(dishes);
    }

    @Test
    void selectTest() {
        List<Dish> menu = DishUtils.getSpecialMenu();
        DishUtils.printDishList(menu);

        System.out.println("filter");
        List<Dish> filter = filter(menu);
        System.out.println(filter);

        System.out.println("takeWhile");
        List<Dish> takeWhile = takeWhile(menu);
        System.out.println(takeWhile);

        System.out.println("dropWhile");
        List<Dish> dropWhile = dropWhile(menu);
        System.out.println(dropWhile);
    }

    @Test
    void flatMapTest() {
        String[] words = new String[]{"Hello", "World"};

        List<String[]> c1 = flatMap1(words);
        System.out.println(c1);

        List<Stream<String>> c2 = flatMap2(words);
        System.out.println(c2);

        List<String> c3 = flatMap3(words);
        System.out.println(c3);

        List<int[]> ret = flatMap4();
        for (int[] a : ret) {
            System.out.print(Arrays.toString(a));
        }
        System.out.println();

        List<int[]> ret2 = flatMap5();
        for (int[] a : ret2) {
            System.out.print(Arrays.toString(a));
        }
        System.out.println();
    }
}