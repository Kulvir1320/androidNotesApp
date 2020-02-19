package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    GridView gridView;
//    int[] imageIcons = {R.drawable.icon01_01};
//
//    String[] title = {"one"};
//
//    String[] desc = {"one"};

    FloatingActionButton floatingActionButton;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        gridView = findViewById(R.id.gridView);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        dataBaseHelper = new DataBaseHelper(this);

        loadNotes();

        final IconAdapter iconAdapter = new IconAdapter(this, CategoryModel.listNotes);
        gridView.setAdapter(iconAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this,DescriptionActivity.class);
                startActivity(intent);
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NotesActivity.this, DescriptionActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });


    }

    private void loadNotes(){
        Cursor cursor = dataBaseHelper.getAllNotes();
        CategoryModel.listNotes.clear();



        if(cursor.moveToFirst()){
            do{

                CategoryModel.listNotes.add(new CategoryModel(cursor.getInt(0),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getDouble(5),cursor.getDouble(6)));
                System.out.println(cursor.getInt(0));
                System.out.println(cursor.getString(1));
                System.out.println(cursor.getString(2));
                System.out.println(cursor.getString(3));
                System.out.println(cursor.getString(4));
                System.out.println(cursor.getString(5));
                System.out.println(cursor.getString(6));

            }while (cursor.moveToNext());
            cursor.close();
        }
    }


}
