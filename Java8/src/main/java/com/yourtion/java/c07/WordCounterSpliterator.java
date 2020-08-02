package com.yourtion.java.c07;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    private static final int THRESHOLD = 10;
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // 处理当前字符串
        action.accept(string.charAt(currentChar++));
        // 如果还有字符串待处理，则返回 true
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < THRESHOLD) {
            // 返回 null 表示当前字符串已经足够小，可以顺序处理
            return null;
        }
        // 尝试将拆分位置位字符串的中间
        for (int spiltPos = currentSize / 2 + currentChar;
             spiltPos < string.length(); spiltPos++) {
            // 拆分位前进直到下一个空格
            if (Character.isWhitespace(string.charAt(spiltPos))) {
                // 创建一个新的 WordCounterSpliterator 来解析从起始位置到拆分位置部分
                WordCounterSpliterator sp =
                        new WordCounterSpliterator(string.substring(currentChar, spiltPos));
                // 将当前 WordCounterSpliterator 起始位置置为拆分位置
                currentChar = spiltPos;
                // 发现一个空格后创建一个新的 WordCounterSpliterator 并返回退出循环
                return sp;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
