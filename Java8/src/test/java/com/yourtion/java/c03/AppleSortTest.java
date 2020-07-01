package com.yourtion.java.c03;

import com.yourtion.java.utils.apple.Apple;
import com.yourtion.java.utils.apple.AppleUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class AppleSortTest {

    @Test
    @DisplayName("第1步：传递代码")
    void sort1() {
        List<Apple> inventory = AppleUtils.getAppleList();
        class AppleComparator implements Comparator<Apple> {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        }
        inventory.sort(new AppleComparator());
        System.out.println(inventory);
    }

    @Test
    @DisplayName("第2步：使用匿名类")
    void sort2() {
        List<Apple> inventory = AppleUtils.getAppleList();
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
        System.out.println(inventory);
    }

    @Test
    @DisplayName("第3步：使用Lambda表达式")
    void sort3() {
        List<Apple> inventory = AppleUtils.getAppleList();
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
    }

    @Test
    @DisplayName("第4步：使用方法引用")
    void sort4() {
        List<Apple> inventory = AppleUtils.getAppleList();
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);
    }

    @Test
    @DisplayName("比较器复合 - 逆序")
    void sortRev() {
        List<Apple> inventory = AppleUtils.getAppleList();
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(inventory);
    }

    @Test
    @DisplayName("比较器复合 - 比较器链")
    void sortChan() {
        List<Apple> inventory = AppleUtils.getAppleList();
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        System.out.println(inventory);
    }

    void printAppleWithFilter(String name, Predicate<Apple> filter) {
        List<Apple> inventory = AppleUtils.getAppleList();
        System.out.println(name + ":");
        System.out.println(Arrays.toString(inventory.stream().filter(filter).toArray()));
        System.out.println();
    }

    @Test
    @DisplayName("谓词复合")
    void sortPredicate() {
        Predicate<Apple> redApple = (apple) -> Apple.Color.RED.equals(apple.getColor());
        printAppleWithFilter("redApple", redApple);

        // 使用negate方法来返回一个Predicate的非
        Predicate<Apple> nonRedApple = redApple.negate();
        printAppleWithFilter("nonRedApple", nonRedApple);

        // 链接两个谓词来生成另一个Predicate对象
        Predicate<Apple> redAndHeavyApple = redApple.and((apple) -> apple.getWeight() > 150);
        printAppleWithFilter("redAndHeavyApple", redAndHeavyApple);

        // 链接三个谓词来构造更复杂的Predicate对象
        Predicate<Apple> redAndHeavyAppleOrGreen =
                redApple.and(apple -> apple.getWeight() > 150)
                        .or(apple -> Apple.Color.GREEN.equals(apple.getColor()));
        printAppleWithFilter("redAndHeavyAppleOrGreen", redAndHeavyAppleOrGreen);
    }

    @Test
    void andThen() {
        // andThen方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = X -> X * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println("f(g(1)) -> (1+1)x2 = " + result);
        // 返回4
        Assertions.assertEquals(4, result);
    }

    @Test
    void compose() {
        // 先把给定的函数用作compose的参数里面给的那个函数，然后再把函数本身用于结果
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = X -> X * 2;
        Function<Integer, Integer> h = f.compose(g);
        int result = h.apply(1);
        System.out.println("g(f(1)) -> (1x2)+1 = " + result);
        // 返回4
        Assertions.assertEquals(3, result);
    }

    // 积分方程
    double integrate(DoubleUnaryOperator f, double a, double b) {
        return (f.applyAsDouble(a) + f.applyAsDouble(b)) * (b - a) / 2.0;
    }

    @Test
    @DisplayName("积分方程")
    void integrate() {
        // 函数f(x) = x + 10（x从3到7）下方的面积
        double ret = integrate((double x) -> x + 10, 3, 7);
        System.out.println("函数f(x) = x + 10（x从3到7）下方的面积 : " + ret);
    }
}