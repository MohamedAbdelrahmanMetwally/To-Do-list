package com.example.to_dolist;

import java.io.Serializable;

public class Model implements Serializable {
    private int id;
    private String title;
    private String description;

    // Constructor فارغ (مهم لبعض المكتبات)
    public Model() {}

    // Constructor كامل
    public Model(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
