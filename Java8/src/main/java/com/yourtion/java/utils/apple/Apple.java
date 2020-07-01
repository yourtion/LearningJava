package com.yourtion.java.utils.apple;

/**
 * Apple 类
 *
 * @author Yourtion
 */
public class Apple {
    private Color color;
    private Integer weight;

    public Apple(Color color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{color=" + color + ", weight=" + weight + "}";
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
