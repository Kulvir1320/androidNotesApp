package com.example.myapplication;

import java.util.ArrayList;

public class CategoryModel {
    private static String categoryName;
    private static String NotesName;

    public CategoryModel(String categoryName) {

        this.categoryName = categoryName;
    }


    public static String getCategoryName() {
        return categoryName;
    }

    public static ArrayList<CategoryModel> Categorylist = new ArrayList<>();
}
