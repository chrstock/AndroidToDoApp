package com.stock.christian.todo_app.adapters;

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

import com.stock.christian.todo_app.R;
import com.stock.christian.todo_app.models.Appointment;

import java.util.ArrayList;

/**
 * Custom Arrayadapter class for Appointment-model
 *
 * @author chrstock
 * @version 0.1
 */
public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    private ArrayList<Appointment> appointments;

    public AppointmentAdapter(Context context, ArrayList<Appointment> appointments) {
        super(context, 0, appointments);
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Appointment appointment = getItem(position);

        //setting to default values
        //ToDo Changing to real placeholders
        String title = "";
        int image = R.drawable.ic_sausage;

        //using getter if object is instantiated
        if (appointment != null) {
            title = appointment.getTitle();
            image = appointment.getImage();
        }

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_appointment, parent, false);
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
                appointments.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
