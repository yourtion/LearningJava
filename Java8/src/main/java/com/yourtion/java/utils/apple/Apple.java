package com.yourtion.java.utils.apple;

/**
 * Apple 类
 *
 * @author Yourtion
 */
public class Apple {
    private Color color;
    private int weight;

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public enum Color {
        /**
         * 红色
         */
        RED,
        /**
         * 绿色
         */
        GREEN
    }
}
