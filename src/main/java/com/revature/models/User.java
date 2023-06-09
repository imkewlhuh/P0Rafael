package com.revature.models;

public class User {

    private int user_id;

    private String name;

    private int age;

    public User() {
    }

    public User(int user_id, String name, int age) {
        this.user_id = user_id;
        this.name = name;
        this.age = age;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "\n User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
