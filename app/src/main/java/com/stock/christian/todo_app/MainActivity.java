package com.stock.christian.todo_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
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


    private void showAllListEntries() {
        List<Task> taskList = Task.listAll(Task.class);

        ArrayAdapter<Task> taskArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_gallery_item,
                taskList);

//        ArrayAdapter<Task> taskArrayAdapter = new TaskAdapter(this, taskList);

        ListView taskListView = findViewById(R.id.list_view_tasks);
        taskListView.setAdapter(taskArrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateAddButton();
        initializeContectualActionBar();

    }

    @Override
    protected void onResume() {
        super.onResume();

        showAllListEntries();
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

    private void activateAddButton() {
        Button buttonAddTask = findViewById(R.id.button_add_task);
        final EditText editTextTitle = findViewById(R.id.editText_title);
        //Image simulated

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 int image = 2131165292;
                                                 String title = editTextTitle.getText().toString();

                                                 if (TextUtils.isEmpty(title)) {
                                                     editTextTitle.setError(getString(R.string.editText_hint_title));
                                                     return;
                                                 }

                                                 editTextTitle.setText("");

                                                 Task task = new Task(title, image);
                                                 task.save();

                                                 InputMethodManager inputMethodManager;
                                                 inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                                 if (getCurrentFocus() != null) {
                                                     inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                                                 }
                                                 showAllListEntries();
                                             }
                                         }
        );

    }

    private void initializeContectualActionBar() {
        final ListView taskListView = findViewById(R.id.list_view_tasks);
        taskListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        taskListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_contextual_action_bar, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        SparseBooleanArray touchedTaskPositions = taskListView.getCheckedItemPositions();
                        for (int i = 0; i < touchedTaskPositions.size(); i++) {
                            boolean isChecked = touchedTaskPositions.valueAt(i);
                            if (isChecked) {
                                int positionListView = touchedTaskPositions.keyAt(i);
                                Task task = (Task) taskListView.getItemAtPosition(positionListView);
                                Log.d(LOG_TAG, "Position im ListView: " + positionListView + "Inhalt: " + task.toString());
                                task.delete();
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

}
