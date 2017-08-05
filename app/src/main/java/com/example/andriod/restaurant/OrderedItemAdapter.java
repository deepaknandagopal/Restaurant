package com.example.andriod.restaurant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Deepak on 7/15/2017.
 */

public class OrderedItemAdapter extends ArrayAdapter<OrderedItem> {
    private static final String LOG_TAG = OrderedItemAdapter.class.getSimpleName();

    public OrderedItemAdapter(Activity context, ArrayList<OrderedItem> orderedItems) {
        super(context, 0, orderedItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }
        OrderedItem orderedItem =getItem(position);
        TextView mItemName = (TextView) listItemView.findViewById(R.id.orderedItem_textView);
        mItemName.setText(orderedItem.getItemName());

        TextView mItemPrice = (TextView) listItemView.findViewById(R.id.price_textview);
        mItemPrice.setText(orderedItem.getPrice());

        TextView mQuantity = (TextView) listItemView.findViewById(R.id.quantity_textView);
        mQuantity.setText(orderedItem.getQuantity());

        return listItemView;
    }
}
