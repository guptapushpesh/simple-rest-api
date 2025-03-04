package com.example.simplerestapi.model;

public class User {

    private final String firstName;
    private final String lastName;
    private final int age;
    private String alias;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " of age: " + age;

    }

}
