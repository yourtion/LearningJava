package com.yourtion.java.c06;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

class CollectorTest {

    @Test
    void testToListCollector() {
        List<Dish> collect = DishUtils.getMenu().stream().collect(new Collector.ToListCollector<>());
        System.out.println("ToListCollector: " + collect);
    }

    @Test
    void simpleCollector() {
        Collector.simpleCollector();
    }
}