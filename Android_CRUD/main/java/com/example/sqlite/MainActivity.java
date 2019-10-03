package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
    }

    public void addContact(View view) {

        String name=editText1.getText().toString();
        String number=editText2.getText().toString();

        DatabaseHandler db=new DatabaseHandler(this);
        db.insertContact(new Contact(name,number));
        Toast.makeText(getApplicationContext(),"contact added",Toast.LENGTH_SHORT).show();
        editText1.setText("");
        editText2.setText("");
    }

    public void getContacts(View view) {
        Intent intent=new Intent(MainActivity.this,ContactList.class);
        overridePendingTransition(0,0);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}
