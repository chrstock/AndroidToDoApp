package com.stock.christian.todo_app.models;

/**
 * Model Class for plain Appointments with title and icon as id
 *
 * @author chrstock
 * @version 0.1
 */
public class Appointment {
    private String title;
    private int image;

    public Appointment(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
