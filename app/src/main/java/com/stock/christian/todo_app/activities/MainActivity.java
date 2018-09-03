package com.stock.christian.todo_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.stock.christian.todo_app.R;
import com.stock.christian.todo_app.adapters.AppointmentAdapter;
import com.stock.christian.todo_app.models.Appointment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static ListView mListViewAppointment;

    private static ArrayList<Appointment> appointmentList;

    private AppointmentAdapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appointmentList = new ArrayList<>();

        if(appointmentList.isEmpty()) {
            populateAppointmentList();
        }

        appointmentAdapter = new AppointmentAdapter(this, appointmentList);

        mListViewAppointment = findViewById(R.id.list_view_appointments);
        mListViewAppointment.setAdapter(appointmentAdapter);
    }

    private static void populateAppointmentList(){
        appointmentList.add(new Appointment("Homework Done.",new Date(2018,10,15,20,15)));
        appointmentList.add(new Appointment("Homework Done.",new Date(2017,10,15,20,15)));
        appointmentList.add(new Appointment("Homework Done.",new Date(2016,10,15,20,15)));
        appointmentList.add(new Appointment("Homework Done.",new Date(2015,10,15,20,15)));
    }

    public void addAppointment(View view){
        Appointment newAppointment = new Appointment("New Appointment",new Date(2018,10,15,20,15));
        appointmentAdapter.add(newAppointment);
    }
}
