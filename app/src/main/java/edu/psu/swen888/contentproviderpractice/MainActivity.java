package edu.psu.swen888.contentproviderpractice;


import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private RecyclerView recyclerView;
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.contacts_list);
        checkSelfPermission(android.Manifest.permission.READ_CONTACTS);
        if (checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            fetchContacts();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQUEST_READ_CONTACTS);
        }
    }

    private void fetchContacts(){
        ContactAdapter adapter = new ContactAdapter(contacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = null;
        String [] selectionArgs = null;
        String sortOrder=null;
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);

        while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact newContact = new Contact(name, number);
                contacts.add(newContact);
        }
        adapter.notifyDataSetChanged();
    }
}