package com.yourtion.java.utils.apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yourtion
 */
public class AppleUtils {
    public static List<Apple> getAppleList() {
        ArrayList<Apple> apples = new ArrayList<>(4);
        apples.add(new Apple(Apple.Color.RED, 150));
        apples.add(new Apple(Apple.Color.GREEN, 80));
        apples.add(new Apple(Apple.Color.RED, 60));
        apples.add(new Apple(Apple.Color.GREEN, 200));
        return apples;
    }
}
