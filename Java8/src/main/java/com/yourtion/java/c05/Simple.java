package com.yourtion.java.c05;

import com.yourtion.java.utils.dish.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Yourtion
 */
public class Simple {
    static List<Integer> distinct(List<Integer> numbers) {
        return numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(toList());
    }

    static List<Dish> skip(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(toList());
    }

    static List<Dish> limit(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());
    }

    static List<Dish> filter(List<Dish> menu) {
        // 从头开始，丢弃所有谓词结果为false的元素。一旦遭遇谓词计算的结果为true，它就停止处理，并返回所有剩余的元素
        return menu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(toList());
    }

    static List<Dish> takeWhile(List<Dish> menu) {
        // 利用谓词对流进行分片，会在遭遇第一个不符合要求的元素时停止处理并返回
        return menu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
    }

    static List<Dish> dropWhile(List<Dish> menu) {
        return menu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
    }

    static List<String[]> flatMap1(String[] words) {
        // 不正确地使用map找出单词列表中各不相同的字符
        return Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
    }

    static List<Stream<String>> flatMap2(String[] words) {
        // 1．尝试使用map和Arrays.stream()
        return Arrays.stream(words)
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
    }

    static List<String> flatMap3(String[] words) {
        // 2．使用flatMap
        return Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
    }

    static List<int[]> flatMap4() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        return numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(toList());
    }

    static List<int[]> flatMap5() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        return numbers1.stream()
                .flatMap(i -> numbers2
                        .stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
    }

}
