package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    Context context;

    private  static  final int DATABASE_VERSION=1;
    private static  final String DATABASE_NAME="contactsManager";
    private static final String TABLE_CONTACTS="contacts";

    //table columns
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_NUMBER="phone_number";





    public DatabaseHandler(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE="CREATE TABLE "+TABLE_CONTACTS+"("+KEY_ID +" INTEGER PRIMARY KEY,"+KEY_NAME+" text,"+KEY_NUMBER+" TEXT)";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    public void insertContact(Contact contact)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_NUMBER,contact.getNumber());
        db.insert(TABLE_CONTACTS,null,values);
        db.close();

    }

    public List<Contact> getAllContacts()
    {
        List<Contact> contactList=new ArrayList<Contact>();
        SQLiteDatabase db= getWritableDatabase();
        String selectQuery="SELECT * FROM "+TABLE_CONTACTS;
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do {
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setNumber(cursor.getString(2));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }


    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
        Toast.makeText(context,"contact deleted",Toast.LENGTH_SHORT).show();
    }

    public void updateContact(int id,String name,String number)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_NUMBER,number);

        // updating row
        db.update(TABLE_CONTACTS, values, KEY_ID + "="+id, null);
        db.close();
        Toast.makeText(context,"updated",Toast.LENGTH_SHORT).show();
    }
}
