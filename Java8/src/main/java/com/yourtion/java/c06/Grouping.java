package com.yourtion.java.c06;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * @author Yourtion
 */
public class Grouping {

    private static final List<Dish> menu = DishUtils.getMenu();
    private static final Map<String, List<String>> dishTags = DishUtils.getDishTags();

    static void grouping1() {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("dishesByType: " + dishesByType);
    }

    static void grouping2() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu
                .stream()
                .collect(groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            }
                            return CaloricLevel.FAT;
                        })
                );
        System.out.println("dishesByCaloricLevel: " + dishesByCaloricLevel);
    }

    static void grouping3() {
        // 过滤谓词
        Map<Dish.Type, List<Dish>> caloricDishesByType = menu
                .stream()
                .collect(
                        groupingBy(
                                Dish::getType,
                                filtering(dish -> dish.getCalories() > 500, toList())
                        )
                );
        System.out.println("caloricDishesByType: " + caloricDishesByType);
    }

    static void grouping4() {
        // 映射函数
        Map<Dish.Type, List<String>> dishesNameByType = menu
                .stream()
                .collect(
                        groupingBy(
                                Dish::getType,
                                mapping(Dish::getName, toList())
                        )
                );
        System.out.println("dishesNameByType: " + dishesNameByType);
    }

    static void grouping5() {
        Map<Dish.Type, Set<String>> dishesNameByType = menu
                .stream()
                .collect(
                        groupingBy(
                                Dish::getType,
                                flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())
                        )
                );
        System.out.println("dishesNameByType: " + dishesNameByType);
    }

    static void grouping6() {
        // 多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return CaloricLevel.NORMAL;
                                    }
                                    return CaloricLevel.FAT;
                                }
                        )
                )
        );
        System.out.println("dishesByTypeCaloricLevel: " + dishesByTypeCaloricLevel);
    }

    static void grouping7() {
        // 按子组收集数据
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting())
        );
        System.out.println("typesCount: " + typesCount);
    }

    static void grouping8() {
        // 查找每个子组中热量最高的Dish
        Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )
                )
        );
        System.out.println("mostCaloricByType: " + mostCaloricByType);
    }

    static void grouping9() {
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType = menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return CaloricLevel.NORMAL;
                                    }
                                    return CaloricLevel.FAT;
                                },
                                toSet()
                        )
                )
        );
        System.out.println("caloricLevelByType: " + caloricLevelByType);
    }

    enum CaloricLevel {DIET, NORMAL, FAT}
}
