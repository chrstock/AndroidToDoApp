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
import java.util.List;

/**
 * Custom Arrayadapter class for Task-model
 *
 * @author chrstock
 * @version 0.1
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    private final Context context;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context,0);
        this.context = context;
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

//        using getter if object is instantiated
        if (task != null) {
            title = task.title;
            image = task.image;
        }

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView mTextViewTitle = convertView.findViewById(R.id.text_title);
        ImageView mImageViewSymbol = convertView.findViewById(R.id.image_symbol);

        mTextViewTitle.setText(title);
        mImageViewSymbol.setImageResource(image);

        return convertView;
    }
}
