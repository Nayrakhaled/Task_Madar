package com.example.task_madar;

public class UserDetail {

    public static final String TABLE_NAME="db";
    public static final String TEXT_ID="text_id";
    public static final String TEXT_NAME="text_name";
    public static final String TEXT_AGE="text_age";
    public static final String TEXT_TITLE="text_title";
    public static final String TEXT_GENDER="text_gender";

    String name, title, gender;
    int age,id;

    public UserDetail(String name, String title, String gender, int age) {
        this.name = name;
        this.title = title;
        this.gender = gender;
        this.age = age;
    }

    public UserDetail() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
