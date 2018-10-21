package com.stock.christian.todo_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity to create User Tasks
 *
 * @author chrstock
 * @version 0.1
 */
public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TaskDataSource dataSource;

    private void showAllListEntries() {
        List<Task> taskList = dataSource.getAllTasks();


//        ArrayAdapter<Task> taskArrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_multiple_choice,
//                taskList);

        ArrayAdapter<Task> taskArrayAdapter = new TaskAdapter(this, taskList);

        ListView taskListView = findViewById(R.id.list_view_tasks);
        taskListView.setAdapter(taskArrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "Die Datenquelle wird angelegt.");
        dataSource = new TaskDataSource(this);

        activateAddButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void activateAddButton(){
        Button buttonAddTask = findViewById(R.id.button_add_task);
        final EditText editTextTitle = findViewById(R.id.editText_title);
        //Image simulated

        buttonAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int image = 2131165292;
                String title = editTextTitle.getText().toString();

                if(TextUtils.isEmpty(title)){
                    editTextTitle.setError(getString(R.string.editText_hint_title));
                    return;
                }

                editTextTitle.setText("");

                dataSource.createTask(title,image);

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(getCurrentFocus() != null){
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }
                showAllListEntries();
            }
                                         }
        );

    }


}
