package com.yourtion.demo.innerBean;

/**
 * Created by yourtion on 07/05/2017.
 */
public class Person {
    private String name;
    private String addrss;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrss() {
        return addrss;
    }

    public void setAddrss(String addrss) {
        this.addrss = addrss;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [address=" + addrss + ", age=" + age +", name=" + name + "]";
    }
}
