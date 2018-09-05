package com.stock.christian.todo_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * MainActivity to create User Tasks
 *
 * @author chrstock
 * @version 0.1
 */
public class MainActivity extends AppCompatActivity {

    private static ArrayList<Task> taskList;

    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();

        if (taskList.isEmpty()) {
            populateTaskList();
        }

        taskAdapter = new TaskAdapter(this, taskList);

        //setting GUI elements
        ListView mListViewTask = findViewById(R.id.list_view_tasks);
        mListViewTask.setAdapter(taskAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        taskAdapter = null;
        taskList = null;
    }

    /**
     * Fills List with Stub entries
     */
    private static void populateTaskList() {
        taskList.add(new Task("Housework Done.", R.drawable.ic_strawberry));
        taskList.add(new Task("Doctor called.", R.drawable.ic_tomato));
        taskList.add(new Task("Homework Done.", R.drawable.ic_turnip));
    }

    /**
     * Adding one element to the list
     * ToDo: User shall add Entries
     *
     * @param view not used
     */
    public void addTask(View view) {
        Task newTask = new Task("Stub Task", R.drawable.ic_tomato);
        taskAdapter.add(newTask);
    }


}
