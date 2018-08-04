package com.practice.mcasey.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class GroceryItemFragment extends Fragment {

    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_AMOUNT = "ITEM_AMOUNT";
    public static final String ITEM_RECURRING = "ITEM_RECURRING";

    private EditText mGroceryName;
    private EditText mGroceryAmount;
    private Switch mGroceryRecurring;
    private TextView mGroceryLocation;
    private Button mAddButton;
    private GroceryItem mGroceryItem;

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity().getIntent().getExtras() != null)
        {
            mGroceryName.setText(getActivity().getIntent().getStringExtra(ITEM_NAME));
            mGroceryAmount.setText(getActivity().getIntent().getStringExtra(ITEM_AMOUNT));
            mGroceryRecurring.setChecked(getActivity().getIntent().getExtras().getBoolean(ITEM_RECURRING));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroceryItem = new GroceryItem();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grocery_item_fragment, container, false);
        mGroceryName = view.findViewById(R.id.grocery_name);
        mGroceryName.setText(mGroceryItem.getGroceryName());
        mGroceryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mGroceryItem.setGroceryName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mGroceryAmount = view.findViewById(R.id.grocery_amount);
        mGroceryAmount.setText(mGroceryItem.getGroceryAmount());
        mGroceryAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mGroceryItem.setGroceryAmount(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mGroceryRecurring = view.findViewById(R.id.grocery_recurring);
        mGroceryRecurring.setChecked(mGroceryItem.isRecurring());
        mGroceryRecurring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mGroceryItem.setRecurring(b);
            }
        });

        mGroceryLocation = view.findViewById(R.id.grocery_location);
        if(getActivity().getIntent().getExtras() != null)
            mGroceryLocation.setText(getActivity().getIntent().getStringExtra(LocationFragment.LOCATION));
        mGroceryItem.setGroceryLocation(mGroceryLocation.getText().toString());
        mGroceryLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = LocationActivity.newInstance(getActivity());
                i.putExtra(ITEM_NAME, mGroceryName.getText().toString());
                i.putExtra(ITEM_AMOUNT, mGroceryAmount.getText().toString());
                i.putExtra(ITEM_RECURRING, mGroceryRecurring.isChecked());
                startActivity(i);
            }
        });

        mAddButton = view.findViewById(R.id.add_new);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroceryItem.sGroceryItems.add(mGroceryItem);
                Intent i = GroceryListActivity.newInstance(getActivity());
                startActivity(i);
            }
        });

        return view;
    }
}
