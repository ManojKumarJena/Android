package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactList extends AppCompatActivity {
    ListView listView;
    List<Contact> list;
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        listView=(ListView)findViewById(R.id.listView);

        DatabaseHandler db=new DatabaseHandler(this);
        list=db.getAllContacts();
        adapter=new MyListAdapter(this,R.layout.list_row,list);
        listView.setAdapter(adapter);
    }







    public class MyListAdapter extends BaseAdapter {

        Context context;
        int layout;
        List<Contact> list;


        public MyListAdapter(Context context, int layout, List<Contact> list) {
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
            TextView textView1=row.findViewById(R.id.id);
            final TextView textView2=row.findViewById(R.id.name);
            TextView textView3=row.findViewById(R.id.phone);

            ImageButton edit=row.findViewById(R.id.edit);
            ImageButton delete=row.findViewById(R.id.delete);
            final DatabaseHandler db=new DatabaseHandler(context);
            final Contact contact=list.get(i);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ContactList.this,EditContact.class);
                    intent.putExtra("name",contact.getName());
                    intent.putExtra("number",contact.getNumber());
                    intent.putExtra("id",String.valueOf(contact.getId()));
                    overridePendingTransition(0,0);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteContact(contact);
                    list.remove(contact);
                    adapter.notifyDataSetChanged();
                }
            });


           // textView1.setText(String.valueOf(contact.getId()));
            textView1.setText(String.valueOf(i+1));
            textView2.setText(contact.getName());
            textView3.setText(contact.getNumber());


            return row;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent2=new Intent(ContactList.this,MainActivity.class);
        overridePendingTransition(0,0);
        startActivity(intent2);
        overridePendingTransition(0,0);
        finish();
    }
}
