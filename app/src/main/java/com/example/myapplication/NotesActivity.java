package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    List<CategoryModel> categoryModels;

    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        gridView = findViewById(R.id.gridView);
        categoryModels = new ArrayList<>();
        floatingActionButton = findViewById(R.id.floatingActionButton);
        final IconAdapter iconAdapter = new IconAdapter(this, categoryModels);
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


}
