package com.yourtion.java.c07;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Yourtion
 * java -jar target/benchmarks.jar ParallelStreamBenchmark
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@State(Scope.Benchmark)
public class ParallelStreamBenchmark {

    private static final long N = 10_000_000L;

    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }

    @Benchmark
    public Long sequentialSum() {
        return Stream.iterate(1L, i -> i + 1).limit(N).reduce(0L, Long::sum);
    }

    @Benchmark
    public Long iterativeSum() {
        long result = 0L;
        for (long i = 0; i <= N; i++) {
            result += i;
        }
        return result;
    }

    @Benchmark
    public Long parallelSum() {
        return Stream.iterate(1L, i -> i + 1).limit(N).parallel().reduce(0L, Long::sum);
    }

    @Benchmark
    public Long rangedSum() {
        return LongStream.rangeClosed(1, N).reduce(0L, Long::sum);
    }

    @Benchmark
    public Long parallelRangedSum() {
        return LongStream.rangeClosed(1, N).parallel().reduce(0L, Long::sum);
    }

    @Benchmark
    public Long forkJoinSum() {
        return ForkJoinSumCalculator.forkJoinSum(N);
    }

}