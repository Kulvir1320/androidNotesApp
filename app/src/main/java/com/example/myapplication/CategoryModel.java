package com.example.myapplication;

import java.util.ArrayList;

public class CategoryModel {


     int id;

     String title,description,date;

    public CategoryModel(int id, String title, String description, String date) {
        this.id = id;
//        this.categoryName = categoryName;
        this.title = title;
        this.description = description;
        this.date = date;
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
