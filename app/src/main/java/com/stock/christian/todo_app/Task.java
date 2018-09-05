package com.stock.christian.todo_app;

/**
 * Model Class for plain Tasks with title and icon as id
 *
 * @author chrstock
 * @version 0.1
 */
public class Task {
    // stores title of task
    private String title;
    // sotred icon of task
    private int image;

    public Task(String title, int image) {
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
