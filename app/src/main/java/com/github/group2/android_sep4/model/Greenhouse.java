package com.github.group2.android_sep4.model;

public class Greenhouse {
    private long id;
    private String name;

    public Greenhouse() {
      // Required for the framework to work
    }

    public Greenhouse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
