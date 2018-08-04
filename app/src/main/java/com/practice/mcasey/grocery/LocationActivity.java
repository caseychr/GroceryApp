package com.practice.mcasey.grocery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    public static Intent newInstance(Context context)
    {
        Intent intent = new Intent(context, LocationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.location_fragment_container);

        if(fragment == null)
        {
            fragment = new LocationFragment();
            fm.beginTransaction().add(R.id.location_fragment_container, fragment).commit();
        }
    }
}
