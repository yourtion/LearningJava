package com.yourtion.java.c02;

import com.yourtion.java.utils.apple.Apple;

import java.util.List;

/**
 * 打印 Apple
 *
 * @author Yourtion
 */
public class PrettyPrintApple {
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String out = formatter.accept(apple);
            System.out.println(out);
        }
    }
}
