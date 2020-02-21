package com.example.myapplication;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryModel implements Serializable {


     int id;

     String title,description,date;
     double noteLat,noteLong;
     String audio;

    public CategoryModel(int id, String title, String description, String date, double noteLat, double noteLong, String audio) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.noteLat = noteLat;
        this.noteLong = noteLong;
        this.audio = audio;
    }

//    public CategoryModel(int id, String title, String description, String date, double noteLat, double noteLong) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.date = date;
//        this.noteLat = noteLat;
//        this.noteLong = noteLong;
//    }


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

    public String getAudio() {
        return audio;
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
