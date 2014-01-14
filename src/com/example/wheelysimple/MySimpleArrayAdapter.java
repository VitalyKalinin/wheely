package com.example.wheelysimple;

import java.util.ArrayList;

import com.example.wheelysimple.dummy.DummyContent;
import com.example.wheelysimple.dummy.DummyContent.DummyItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<DummyItem> {
    private final Context context;
    private final ArrayList<DummyItem> values;

    public MySimpleArrayAdapter(Context context, ArrayList<DummyContent.DummyItem> values) {
        super(context, R.layout.row_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView titleView = (TextView) rowView.findViewById(R.id.title);
        TextView textView = (TextView) rowView.findViewById(R.id.text);
        
        titleView.setText(values.get(position).title);
        textView.setText(values.get(position).text);
        
        return rowView;
    }
}