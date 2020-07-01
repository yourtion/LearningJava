package com.yourtion.java.c02;

import com.yourtion.java.utils.apple.Apple;
import com.yourtion.java.utils.apple.AppleUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.yourtion.java.c02.PrettyPrintApple.prettyPrintApple;

class PrettyPrintAppleTest {
    private List<Apple> inventory = AppleUtils.getAppleList();

    @Test
    void AppleSimpleFormatter() {
        prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    @Test
    void AppleFancyFormatter() {
        prettyPrintApple(inventory, new AppleFancyFormatter());
    }
}