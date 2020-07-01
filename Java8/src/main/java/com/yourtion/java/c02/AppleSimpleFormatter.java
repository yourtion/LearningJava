package com.yourtion.java.c02;

import com.yourtion.java.utils.apple.Apple;

/**
 * 简单格式化
 *
 * @author Yourtion
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
