package com.example.gradesapp;

import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Spinner;

/**
 *
 * @author Zakk
 *
 */
public class Categories extends Observable {

    private ArrayList<Category> catArray;
    private String curCat;

    /**
     * Creates the classes Object
     */
    public Categories()
    {
        catArray = new ArrayList<Category>();
    }

    /**
     * Adds a class to the array from its sharedPreference (created in the add class activity)
     * @param sharedPref
     */
    public void addClass(SharedPreferences sharedPref)
    {
        Category cat = new Category(sharedPref.getInt("weight", 0), sharedPref.getString("name", null));
        catArray.add(cat);

        notifyObservers();
    }

    /**
     * Returns a string array of the names of each class for use in the spinner
     * @return a string array of class names
     */
    public String[] getNameArray()
    {
        String[] categories = new String[catArray.size()];
        for (int i = 0; i < catArray.size(); i++)
        {
            categories[i] = catArray.get(i).getName();
        }

        return categories;
    }

    /**
     * Sets the current selected class
     * @param className
     */
    public void setCurClass(String catName)
    {
        curCat = catName;
    }

    /**
     * returns the current class object the corresponds to the name
     * @return a class object
     */
    public Category getCurClass()
    {
        for (int i = 0; i < catArray.size(); i++)
        {
            if (catArray.get(i).getName() == curCat)
            {
                return catArray.get(i);
            }
        }

        return null;
    }

}

