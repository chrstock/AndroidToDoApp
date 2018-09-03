package com.stock.christian.todo_app.models;

import java.util.Date;

/**
 * Model Class for plain Appointments
 *
 * @version 0.1
 */
public class Appointment {
    private String title;
    private Date date;

    public Appointment(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
