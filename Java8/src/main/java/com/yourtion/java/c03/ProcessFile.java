package com.yourtion.java.c03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件处理类
 *
 * @author Yourtion
 */
public class ProcessFile {
    private String path = "src/main/resources/data.txt";

    public ProcessFile() {
    }

    public ProcessFile(String path) {
        this.path = path;
    }


    /**
     * 只能读取一行的文件读取
     */
    public String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    /**
     * 通过一个 Lambda 处理读取的文件
     */
    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        /**
         * 处理方法
         *
         * @param b Buffer Reader
         * @return 处理结果
         * @throws IOException 读取文件报错
         */
        String process(BufferedReader b) throws IOException;
    }

}
