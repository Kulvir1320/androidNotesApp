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

public class NotesActivity extends AppCompatActivity {
    GridView gridView;
    int[] imageIcons = {R.drawable.icon01_01};

    String[] title = {"one"};

    String[] desc = {"one"};

    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        gridView = findViewById(R.id.gridView);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this,DescriptionActivity.class);
                startActivity(intent);
            }
        });

        final IconAdapter iconAdapter = new IconAdapter(this, title, desc, imageIcons);
        gridView.setAdapter(iconAdapter);
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
