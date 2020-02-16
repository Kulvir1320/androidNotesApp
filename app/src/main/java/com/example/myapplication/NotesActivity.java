package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

public class NotesActivity extends AppCompatActivity {
    GridView gridView;
    int[] imageIcons = {R.drawable.icon01_01};
    //    R.drawable.icon01_02, R.drawable.icon01_03, R.drawable.icon01_04,
//            R.drawable.icon01_05, R.drawable.icon01_06, R.drawable.icon01_07, R.drawable.icon01_08, R.drawable.icon01_09,
//            R.drawable.icon01_10};
    String[] title = {"one"};
    //            "one", "two", "three",
//            "four", "five", "six",
//            "seven", "eight", "nine", "ten"
//    };
    String[] desc = {"one"};
//            "one", "two", "three",
//            "four", "five", "six",
//            "seven", "eight", "nine", "ten"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        gridView = findViewById(R.id.gridView);
        final IconAdapter iconAdapter = new IconAdapter(this, title, desc, imageIcons);
        gridView.setAdapter(iconAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.category_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.add:

        return true;

    }
        return true;
    }

}
