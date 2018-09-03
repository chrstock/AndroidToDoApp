package com.stock.christian.todo_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stock.christian.todo_app.R;
import com.stock.christian.todo_app.models.Appointment;

import java.util.ArrayList;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    private TextView mTextViewTitle;
    private TextView mTextViewDate;

    public AppointmentAdapter(Context context, ArrayList<Appointment> appointments) {
        super(context, 0, appointments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Appointment appointment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_appointment, parent, false);
        }

        mTextViewTitle = convertView.findViewById(R.id.text_title);
        mTextViewDate = convertView.findViewById(R.id.text_date);

        mTextViewTitle.setText(appointment.getTitle());
        mTextViewDate.setText(appointment.getDate().toString());

        return convertView;
    }
}
