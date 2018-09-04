package com.stock.christian.todo_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.stock.christian.todo_app.R;
import com.stock.christian.todo_app.adapters.AppointmentAdapter;
import com.stock.christian.todo_app.models.Appointment;

import java.util.ArrayList;

/**
 * MainActivity to create User Tasks
 *
 * @author chrstock
 * @version 0.1
 */
public class MainActivity extends AppCompatActivity {

    private static ArrayList<Appointment> appointmentList;

    private AppointmentAdapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appointmentList = new ArrayList<>();

        if (appointmentList.isEmpty()) {
            populateAppointmentList();
        }

        appointmentAdapter = new AppointmentAdapter(this, appointmentList);

        //setting GUI elements
        ListView mListViewAppointment = findViewById(R.id.list_view_appointments);
        mListViewAppointment.setAdapter(appointmentAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appointmentAdapter = null;
        appointmentList = null;
    }

    /**
     * Fills List with Stub entries
     */
    private static void populateAppointmentList() {
        appointmentList.add(new Appointment("Housework Done.", R.drawable.ic_strawberry));
        appointmentList.add(new Appointment("Doctor called.", R.drawable.ic_tomato));
        appointmentList.add(new Appointment("Homework Done.", R.drawable.ic_turnip));
    }

    /**
     * Adding one element to the list
     * ToDo: User shall add Entries
     *
     * @param view not used
     */
    public void addAppointment(View view) {
        Appointment newAppointment = new Appointment("Stub Appointment", R.drawable.ic_tomato);
        appointmentAdapter.add(newAppointment);
    }


}
