package com.cs442.sbasappa_a3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sahana on 10/04/2015.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fooditem_row, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.textView1);
        TextView name = (TextView) convertView.findViewById(R.id.textView2);
        TextView price = (TextView) convertView.findViewById(R.id.textView3);
        // Populate the data into the template view using the data object
        id.setText(Integer.toString(item.getId())+".");
        name.setText(item.getName());
        price.setText(item.getPrice());

        // Return the completed view to render on screen
        return convertView;
    }
}
