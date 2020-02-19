package com.example.myapplication;

import android.location.Location;

import java.util.ArrayList;

public class CategoryModel {


     int id;

     String title,description,date;
     double noteLat,noteLong;

    public CategoryModel(int id, String title, String description, String date, double noteLat, double noteLong) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.noteLat = noteLat;
        this.noteLong = noteLong;
    }


    public int getId() {
        return id;
    }

//    public String getCategoryName() {
//        return categoryName;
//    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }


    public static ArrayList<CategoryModel> listNotes = new ArrayList<>();

    public double getNoteLat() {
        return noteLat;
    }

    public double getNoteLong() {
        return noteLong;
    }


//    private static String categoryName;
//    private static String NotesName;
//
//    public CategoryModel(String categoryName) {
//
//        this.categoryName = categoryName;
//    }
//
//
//    public static String getCategoryName() {
//        return categoryName;
//    }
//
//    public static ArrayList<CategoryModel> Categorylist = new ArrayList<>();
}
