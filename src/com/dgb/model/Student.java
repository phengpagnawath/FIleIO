package com.dgb.model;

import com.dgb.Utils.Constants;

public class Student {
    int id;
    String name;
    String gender;

    public Student(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + Constants.FIELD_SPLIT + name + Constants.FIELD_SPLIT + gender + Constants.RECORD_SPLIT;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
