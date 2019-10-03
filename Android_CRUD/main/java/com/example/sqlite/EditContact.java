package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {
    EditText editText1,editText2;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        editText1=(EditText)findViewById(R.id.UpeditText1);
        editText2=(EditText)findViewById(R.id.UpeditText2);
        update=(Button)findViewById(R.id.update);

        Intent intent=getIntent();
        editText1.setText(intent.getStringExtra("name"));
        editText2.setText(intent.getStringExtra("number"));
        final String id=intent.getStringExtra("id");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db=new DatabaseHandler(EditContact.this);
                db.updateContact(Integer.parseInt(id),editText1.getText().toString(),editText2.getText().toString());
                Intent intent1=new Intent(EditContact.this,ContactList.class);
                overridePendingTransition(0,0);
                startActivity(intent1);
                overridePendingTransition(0,0);
                finish();
            }
        });

    }
}
