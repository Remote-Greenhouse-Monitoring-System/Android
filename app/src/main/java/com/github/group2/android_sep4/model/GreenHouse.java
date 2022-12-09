package com.github.group2.android_sep4.model;

public class GreenHouse {
    private long id;
    private String name;

    public GreenHouse() {
      // Required for the framework to work
    }

    public GreenHouse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GreenHouse(String name) {
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
