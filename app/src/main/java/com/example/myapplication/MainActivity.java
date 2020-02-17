package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    CategoryModel model;
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] CategoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.category_list_view);
//
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent intent = new Intent(MainActivity.this, NotesActivity.class);
             intent.putExtra("id", position);
             startActivity(intent);

         }
     });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.category_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final String    etStr;


        switch (item.getItemId()){

            case R.id.add:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                 tv = new TextView(this);
                tv.setText(" Category name");
                tv.setPadding(40, 40 ,40,40);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(20);
                EditText et = new EditText(this);
//              etStr = et.getText().toString();
              etStr = et.getEditableText().toString();
            alertDialogBuilder.setView(et);
            alertDialogBuilder.setTitle("alert");
            alertDialogBuilder.setMessage("enter the category name");
            alertDialogBuilder.setCustomTitle(tv);

            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    model = new CategoryModel(etStr);
                    System.out.println("edit value"+ etStr);
                    CategoryModel.Categorylist.add(model);

                    CategoryName = new String[CategoryModel.Categorylist.size()];
                    System.out.println("size of array" + CategoryModel.Categorylist.size());
                    for (int i = 0; i < CategoryModel.Categorylist.size(); i ++){
                        System.out.println(CategoryModel.Categorylist.get(i).getCategoryName());
                        CategoryName[i] = CategoryModel.Categorylist.get(i).getCategoryName();
                    }

                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, CategoryName);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();


                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

}
