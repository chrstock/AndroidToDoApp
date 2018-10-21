package com.stock.christian.todo_app;

/**
 * Model Class for plain Tasks with title and icon as id
 *
 * @author chrstock
 * @version 0.1
 */
public class Task {
    private long id;
    // stores title of task
    private String title;
    // sotred icon of task
    private int image;

    public Task(String title, int image, long id) {
        this.title = title;
        this.image = image;
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image=" + image +
                '}';
    }
}
