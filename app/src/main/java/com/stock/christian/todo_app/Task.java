package com.stock.christian.todo_app;

import com.orm.SugarRecord;

/**
 * Entity for SugarORM
 *
 * @author chrstock
 * @version 0.1
 */
public class Task extends SugarRecord<Task> {

    // stores title of task
    String title;
    // sotred icon of task
    int image;

    public Task() {

    }

    public Task(String title, int image) {
        this.title = title;
        this.image = image;
    }

    @Override
    public String toString() {
        return this.id + ", " + this.title + ", " + this.image;
    }
}
