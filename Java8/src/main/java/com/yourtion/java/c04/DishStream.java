package com.yourtion.java.c04;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Yourtion
 */
public class DishStream {
    public static void main(String[] args) {
        List<Dish> menu = DishUtils.getMenu();
        // 从menu (菜肴列表)获得流
        List<String> threeHighCaloricDishNames = menu.stream()
                // 建立操作流水线:首先选出高热量的菜肴
                .filter(dish -> dish.getCalories() > 300)
                // 获取菜名
                .map(Dish::getName)
                //只选择头三个
                .limit(3)
                //  将结果保存在另一个List中
                .collect(toList());
        // 结果是[pork, beef, chicken]
        System.out.println(threeHighCaloricDishNames);
    }

}
