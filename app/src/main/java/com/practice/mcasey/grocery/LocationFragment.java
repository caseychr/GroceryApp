package com.practice.mcasey.grocery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment
{
    public static final String LOCATION = "LOCATION";

    private RecyclerView mRecyclerView;
    private LocationAdapter mLocationAdapter;
    private List<String> locations = new ArrayList<>();
    private Bundle mBundle;

    private String name;
    private String amount;
    private boolean recurring;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getArguments().getString(GroceryItemFragment.ITEM_NAME);
        amount = getArguments().getString(GroceryItemFragment.ITEM_AMOUNT);
        recurring = getArguments().getBoolean(GroceryItemFragment.ITEM_RECURRING);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.location_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.location_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        locations.add("ALDI");locations.add("Kroger");locations.add("Publix");locations.add("Trader Joe's");locations.add("Walmart");
        if(mLocationAdapter == null)
        {
            mLocationAdapter = new LocationAdapter(locations);
            mRecyclerView.setAdapter(mLocationAdapter);
        }
        mLocationAdapter.notifyDataSetChanged();
        return view;
    }

    private class LocationHolder extends RecyclerView.ViewHolder
    {
        private TextView mLocationTextView;
        private String mLocation;

        public LocationHolder(LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.list_item_location, parent, false));
            mLocationTextView = itemView.findViewById(R.id.location);

            mLocationTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBundle = new Bundle();
                    mBundle.putString(GroceryItemFragment.ITEM_NAME, name);
                    mBundle.putString(GroceryItemFragment.ITEM_AMOUNT, amount);
                    mBundle.putBoolean(GroceryItemFragment.ITEM_RECURRING, recurring);
                    mBundle.putString(LOCATION, mLocationTextView.getText().toString());
                    Fragment frag = new GroceryItemFragment();
                    frag.setArguments(mBundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.list_fragment_container, frag).commit();
                }
            });
        }

        public void bind(String location)
        {
            mLocation = location;
            mLocationTextView.setText(mLocation);
        }
    }

    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder>
    {
        private List<String> mLocations;

        public LocationAdapter(List<String> locations){mLocations = locations;}

        @NonNull
        @Override
        public LocationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LocationHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull LocationHolder locationHolder, int i) {
            String location = mLocations.get(i);
            locationHolder.bind(location);
        }

        @Override
        public int getItemCount() {
            return mLocations.size();
        }
    }
}
