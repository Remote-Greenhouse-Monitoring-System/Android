package com.github.group2.android_sep4.model;

public class User {

    private long id;
    private String email, username, password;


    public User() {
    }


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object obj){

        if (! (obj instanceof User)) return false;

        User user = (User) obj;

        return user.getEmail().equalsIgnoreCase(email) && password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}