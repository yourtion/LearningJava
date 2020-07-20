package com.yourtion.java.c06;

import com.yourtion.java.utils.dish.Dish;
import com.yourtion.java.utils.dish.DishUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author Yourtion
 */
public class Collector {
    static void simpleCollector() {
        // 永远都是一个IDENTITY_FINISH和CONCURRENT但并非UNORDERED的收集器
        List<Dish> collect = DishUtils.getMenu().stream().collect(
                ArrayList::new, // 源
                ArrayList::add, // 累加器
                ArrayList::addAll // 组合器
        );
        System.out.println("simpleCollector: " + collect);
    }

    static class ToListCollector<T> implements java.util.stream.Collector<T, List<T>, List<T>> {
        @Override
        public Supplier<List<T>> supplier() {
            // 建立新的结果容器
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            // 将元素添加到结果容器
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            // 合并两个结果容器
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            // 对结果容器应用最终转换
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            // 1. IDENTITY_FINISH，因为用来累积流中元素的List已经是我们要的最终结果，用不着进一步转换了
            // 2. 不是UNORDERED，因为用在有序流上的时候，希望顺序能够保留在得到的List中
            // 3. CONCURRENT的，仅仅在背后的数据源无序时才会并行处理。
            return Collections.unmodifiableSet(
                    EnumSet.of(IDENTITY_FINISH, CONCURRENT));
        }
    }
}
