package com.practice.mcasey.grocery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class GroceryItemActivity extends AppCompatActivity {

    public static Intent newInstance(Context context)
    {
        Intent intent = new Intent(context, GroceryItemActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.item_fragment_container);

        if(fragment == null)
        {
            fragment = new GroceryItemFragment();
            fm.beginTransaction().add(R.id.item_fragment_container, fragment).commit();
        }
    }
}
