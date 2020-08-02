package com.yourtion.java.c07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    /**
     * 将任务分解为子任务的阈值大小
     */
    private static final long THRESHOLD = 10_000;
    /**
     * 要求和的数字数组
     */
    private final long[] numbers;
    /**
     * 由子任务处理的子数组的起始和终止位置
     */
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        // 公共构造函数用于创造主任务
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        // 私有化构造函数用以递归方式为主任务创建子任务
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public static long forkJoinSum(long n) {
        long[] nums = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(nums);
        return new ForkJoinPool().invoke(task);
    }

    @Override
    protected Long compute() {
        // 重写 RecursiveTask 抽象方法

        // 计算该子任务负责求和的子数组大小
        int len = end - start;
        // 如果小于阈值则顺序计算结果
        if (len < THRESHOLD) {
            return computeSequentially();
        }
        // 创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + len / 2);
        // 利用 ForkJoinPool 的另外一个线程异步地执行新创建的子任务
        leftTask.fork();
        // 创建一个子任务来为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + len / 2, end);
        // 同步执行第二个子任务，有可能进行进一步递归划分
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果尚未完成则等待
        Long leftResult = leftTask.join();
        return rightResult + leftResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
