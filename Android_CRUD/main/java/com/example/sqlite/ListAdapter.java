package com.example.sqlite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Contact> list;


    public ListAdapter(Context context, int layout, List<Contact> list) {
        this.context = context;
        this.list = list;
        this.layout=layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, final ViewGroup viewGroup) {

        View row=view;
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=inflater.inflate(layout,null);
        TextView textView1=row.findViewById(R.id.id);TextView textView2=row.findViewById(R.id.name);
        TextView textView3=row.findViewById(R.id.phone);

        ImageButton edit=row.findViewById(R.id.edit);
        ImageButton delete=row.findViewById(R.id.delete);
        DatabaseHandler db=new DatabaseHandler(context);
        Contact contact=list.get(i);
        textView1.setText(String.valueOf(contact.getId()));
        textView2.setText(contact.getName());
        textView3.setText(contact.getNumber());


        return row;
    }
}
