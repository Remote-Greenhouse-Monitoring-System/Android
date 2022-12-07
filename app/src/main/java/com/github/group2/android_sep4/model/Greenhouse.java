package com.github.group2.android_sep4.model;

public class Greenhouse {
    private int id;
    private String name;

    public Greenhouse(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Greenhouse(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
