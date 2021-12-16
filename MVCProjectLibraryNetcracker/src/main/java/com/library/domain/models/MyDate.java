package com.library.domain.models;

public class MyDate {
    String data;

    public MyDate(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "data='" + data + '\'' +
                '}';
    }
}
