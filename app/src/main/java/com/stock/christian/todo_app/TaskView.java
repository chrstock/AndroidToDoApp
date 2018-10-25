package com.stock.christian.todo_app;

import java.util.List;

public interface TaskView {
    void showTasks(List<Task> tasks);
    void showErrorMessage(String error);
}
