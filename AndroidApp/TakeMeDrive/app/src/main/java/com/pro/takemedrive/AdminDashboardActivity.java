package com.pro.takemedrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    public void ManageUsers(View v)
    {
        Intent i = new Intent(this,ManageUserActivity.class);
        startActivity(i);
    }

    public void AddTrip(View v)
    {
        Intent i = new Intent(this,AddNewTripActivity.class);
        startActivity(i);
    }

    public void BrowseTripts(View v)
    {
        Intent i = new Intent(this, BrowseTripsActivity.class);
        startActivity(i);
    }


    public void RegTrips(View v)
    {
        Intent i = new Intent(this,RegTripsActivity.class);
        startActivity(i);
    }

    public void MyTrips(View v)
    {
        Intent i = new Intent(this,MyTripsActivity.class);
        startActivity(i);
    }

    public void Archive(View v)
    {
        Intent i = new Intent(this,ArchiveActivity.class);
        startActivity(i);
    }
}
