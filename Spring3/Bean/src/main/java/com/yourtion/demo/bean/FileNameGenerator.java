package com.yourtion.demo.bean;

/**
 * Created by yourtion on 07/05/2017.
 */
public class FileNameGenerator {
    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printFileName() {
        System.out.println("FileName & FileType is : " + name + " & " + type);
    }
}
