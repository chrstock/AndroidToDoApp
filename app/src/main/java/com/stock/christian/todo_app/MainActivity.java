package com.stock.christian.todo_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

/**
 * MainActivity to create User Tasks
 *
 * @author chrstock
 * @version 0.1
 */
public class MainActivity extends AppCompatActivity implements TaskView {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private EditText editTextTitle;

    private TaskPresenter presenter;

    @Override
    public void showTasks(List<Task> tasks) {

        TaskAdapter adapter = new TaskAdapter(this,tasks);

        for (Task task : tasks) {
            adapter.add(task);
        }

        ListView listView = findViewById(R.id.list_view_tasks);
        listView.setAdapter(adapter);

        Log.d(LOG_TAG, "Tasks neu geladen geladen..." + tasks.toString());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String error) {
        editTextTitle.setError(error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.editText_title);

        activateButton();

        presenter = new TaskPresenterImpl(this);
        presenter.loadTasks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    void activateButton() {
        Button buttonAdd = findViewById(R.id.button_add_task);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int image = 2131165292;
                String title = editTextTitle.getText().toString();

                presenter.addTask(title,image);

                editTextTitle.setText("");
            }
        });

    }


}
