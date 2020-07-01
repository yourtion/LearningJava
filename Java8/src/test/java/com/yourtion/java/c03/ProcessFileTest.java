package com.yourtion.java.c03;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

class ProcessFileTest {

    @Test
    void processFileOld() throws IOException {
        String old = new ProcessFile().processFile();
        System.out.println(old);
    }

    @Test
    void processFileOne() throws IOException {
        String oneLine = new ProcessFile().processFile(BufferedReader::readLine);
        System.out.println(oneLine);
    }

    @Test
    void processFileTwo() throws IOException {
        String twoLines = new ProcessFile().processFile((br) -> br.readLine() + " - " + br.readLine());
        System.out.println(twoLines);
    }
}