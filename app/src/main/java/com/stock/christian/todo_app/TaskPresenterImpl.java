package com.stock.christian.todo_app;

import java.util.List;

public class TaskPresenterImpl implements TaskPresenter{

    private TaskView view;

    private final static String ERROR_EMPTY_TITLE = "Das Feld darf nicht leer sein";

    public TaskPresenterImpl(TaskView view){
        this.view = view;
    }

    @Override
    public void loadTasks(){
        List<Task> tasks = Task.listAll(Task.class);
        view.showTasks(tasks);
    }

    @Override
    public void addTask(String title, int image){
        if(title.isEmpty() || title == null ){
            view.showErrorMessage(ERROR_EMPTY_TITLE);
            return;
        }
        new Task(title,image).save();
        loadTasks();
    }

}
