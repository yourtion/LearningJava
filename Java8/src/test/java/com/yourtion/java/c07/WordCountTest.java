package com.yourtion.java.c07;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class WordCountTest {

    private static final String str = "I love    Java I love Java I love Java I love Java I love Java I love Java I love Java";

    @Test
    void countWordsIteratively() {
        int i = WordCount.countWordsIteratively(str);
        System.out.println("countWordsIteratively Found " + i + " Words");
    }

    @Test
    void countWordsStream() {
        Stream<Character> stream = IntStream.range(0, str.length()).mapToObj(str::charAt);
        int i = WordCount.countWordsStream(stream);
        System.out.println("countWordsStream Found " + i + " Words");
    }

    @Test
    void countWordsStreamWrong() {
        Stream<Character> stream = IntStream.range(0, str.length()).mapToObj(str::charAt);
        int i = WordCount.countWordsStream(stream.parallel());
        System.out.println("countWordsStreamWrong Found " + i + " Words");
    }

    @Test
    void countWordsStreamSpliterator() {
        WordCounterSpliterator spliterator = new WordCounterSpliterator(str);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        int i = WordCount.countWordsStream(stream.parallel());
        System.out.println("countWordsStreamSpliterator Found " + i + " Words");
    }
}