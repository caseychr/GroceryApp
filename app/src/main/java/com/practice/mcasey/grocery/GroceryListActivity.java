package com.practice.mcasey.grocery;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GroceryListActivity extends AppCompatActivity {

    public static Intent newInstance(Context context)
    {
        Intent intent = new Intent(context, GroceryListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.list_fragment_container);

        if(fragment == null)
        {
            fragment = new GroceryListFragment();
            fm.beginTransaction().add(R.id.list_fragment_container, fragment).commit();
        }
    }
}
