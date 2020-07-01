package com.yourtion.java.c02;

import com.yourtion.java.utils.apple.Apple;

/**
 * Apple 格式化
 *
 * @author Yourtion
 */
public interface AppleFormatter {
    /**
     * 格式化方法
     *
     * @param apple Apple
     * @return 格式化结果
     */
    String accept(Apple apple);
}
