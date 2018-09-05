package com.stock.christian.todo_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom Arrayadapter class for Task-model
 *
 * @author chrstock
 * @version 0.1
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> tasks;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Task task = getItem(position);

        //setting to default values
        //ToDo Changing to real placeholders
        String title = "";
        int image = R.drawable.ic_sausage;

        //using getter if object is instantiated
        if (task != null) {
            title = task.getTitle();
            image = task.getImage();
        }

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView mTextViewTitle = convertView.findViewById(R.id.text_title);
        ImageView mImageViewSymbol = convertView.findViewById(R.id.image_symbol);

        mTextViewTitle.setText(title);
        mImageViewSymbol.setImageResource(image);

        FloatingActionButton buttonRemove = convertView.findViewById(R.id.button_delete);

        //implementing removing button to delete its own element
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
