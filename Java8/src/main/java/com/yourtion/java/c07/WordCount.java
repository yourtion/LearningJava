package com.yourtion.java.c07;

import java.util.stream.Stream;

public class WordCount {

    public static int countWordsIteratively(String s) {
        int counters = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else if (lastSpace) {
                counters += 1;
                lastSpace = false;
            }
        }
        return counters;
    }

    public static int countWordsStream(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine
        );
        return wordCounter.getCounter();
    }

    private static class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            // 和迭代方法一样，accumulate 方法一个个遍历 Character
            if (Character.isWhitespace(c)) {
                return lastSpace ? this : new WordCounter(counter, true);
            } else {
                // 如果上一个是空格，当前不是空格，则单词计数器加 1
                return lastSpace ? new WordCounter(counter + 1, false) : this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            // 合并两个 WordCounter 将结果累加，无需关系 lastSpace
            return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }

}
