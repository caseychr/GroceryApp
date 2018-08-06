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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class GroceryListFragment extends Fragment
{
    private RecyclerView mRecyclerView;
    private GroceryAdapter mGroceryAdapter;
    private Button mAddButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grocery_list_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.grocery_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAddButton = view.findViewById(R.id.create_new);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list_fragment_container, new GroceryItemFragment()).commit();
            }
        });
        updateUI();
        return view;
    }

    private void updateUI()
    {
        GroceryLab groceryLab = GroceryLab.get(getActivity());
        List<GroceryItem> groceryItems = groceryLab.getGroceries();//GroceryItem.sGroceryItems;
        if(mGroceryAdapter == null)
        {
            mGroceryAdapter = new GroceryAdapter(groceryItems);
            mRecyclerView.setAdapter(mGroceryAdapter);
        }
        else
        {
            mGroceryAdapter.setGroceries(groceryItems);
            mGroceryAdapter.notifyDataSetChanged();
        }
    }

    private class GroceryHolder extends RecyclerView.ViewHolder
    {
        private TextView mItemName;
        private TextView mItemAmount;
        private TextView mItemLocation;
        private CheckBox mItemCompleted;
        private GroceryItem mGroceryItem;

        public GroceryHolder(LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.list_item_grocery, parent, false));
            mItemName = itemView.findViewById(R.id.item_name);
            mItemAmount = itemView.findViewById(R.id.item_amount);
            mItemLocation = itemView.findViewById(R.id.item_location);
            mItemCompleted = itemView.findViewById(R.id.item_completed);

            mItemCompleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //GroceryItem.sGroceryItems.remove(mGroceryItem);
                    GroceryLab.get(getActivity()).deleteGrocery(mGroceryItem);
                    updateUI();
                }
            });
        }

        public void bind(GroceryItem groceryItem)
        {
            mGroceryItem = groceryItem;
            mItemName.setText(mGroceryItem.getGroceryName());
            mItemAmount.setText("("+mGroceryItem.getGroceryAmount()+")");
            mItemLocation.setText(mGroceryItem.getGroceryLocation());
        }
    }

    private class GroceryAdapter extends RecyclerView.Adapter<GroceryHolder>
    {
        private List<GroceryItem> mGroceries;

        public GroceryAdapter(List<GroceryItem> groceries){mGroceries = groceries;}

        @NonNull
        @Override
        public GroceryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new GroceryHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull GroceryHolder groceryHolder, int i) {
            GroceryItem groceryItem = mGroceries.get(i);
            groceryHolder.bind(groceryItem);
        }

        @Override
        public int getItemCount() {
            return mGroceries.size();
        }

        public void setGroceries(List<GroceryItem> groceries){mGroceries = groceries;}
    }
}
