package com.example.shan.jsoncommunication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shan on 7/27/18.
 */

public class OrdersAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public OrdersAdapter(Context context, String[] values) {
        super(context, 1);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return values.length;
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return values[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.my_simple_list_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];
        //if (s.startsWith("p")) {
        //    imageView.setImageResource(R.drawable.add_alarm);
        //} else {
            imageView.setImageResource(R.drawable.alarm);
        //}

        return rowView;
    }

}
