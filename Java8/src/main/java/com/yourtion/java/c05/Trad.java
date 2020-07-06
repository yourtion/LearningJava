package com.yourtion.java.c05;

import com.yourtion.java.utils.trad.Trader;
import com.yourtion.java.utils.trad.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yourtion
 */
public class Trad {
    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    static void q1() {
        // 找出2011年发生的所有交易，并按交易额排序（从低到高）
        List<Transaction> ret = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(ret);
    }

    static void q2() {
        // 交易员都在哪些不同的城市工作过
        List<String> ret = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(ret);
    }

    static void q3() {
        // 查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> ret = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(ret);
    }

    static void q4() {
        // 返回所有交易员的姓名字符串，按字母顺序排序
        String ret = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(ret);
    }

    static void q5() {
        // 有没有交易员是在米兰工作的
        boolean ret = transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println("any one work on milan: " + ret);
    }

    static void q6() {
        // 打印生活在剑桥的交易员的所有交易额
        List<Integer> ret = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println("Trader value in Cambridge: " + ret);
    }

    static void q7() {
        // 所有交易中，最高的交易额是多少？
        Integer ret = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println("Trader max value: " + ret);
    }

    static void q8() {
        // 找到交易额最小的交易
        Optional<Transaction> ret = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));
        System.out.println("Min trader is: " + ret);
    }
}
