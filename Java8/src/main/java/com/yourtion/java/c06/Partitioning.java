package com.yourtion.java.c06;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * @author Yourtion
 */
public class Partitioning {
    private static final List<Dish> menu = DishUtils.getMenu();

    static void partition1() {
        Map<Boolean, List<Dish>> partitioningMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("partitioningMenu: " + partitioningMenu);

    }

    static void partition2() {
        Map<Boolean, Dish> mostCaloricPartitioningMenu = menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )
                ));
        System.out.println("mostCaloricPartitioningMenu: " + mostCaloricPartitioningMenu);
    }

    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(Partitioning::isPrime));
    }

    static void partition3() {
        Map<Boolean, List<Integer>> primeMap = partitionPrimes(10);
        System.out.println("primeMap: " + primeMap);
    }
}
