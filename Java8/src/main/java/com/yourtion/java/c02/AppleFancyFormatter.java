package com.yourtion.java.c02;

import com.yourtion.java.utils.apple.Apple;

/**
 * 高级格式化
 *
 * @author Yourtion
 */
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy " : "light ";
        return "A " + characteristic + apple.getColor() + " apple";
    }
}
