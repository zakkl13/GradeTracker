package com.example.gradesapp;

import android.content.SharedPreferences.Editor;
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
public class Classes extends Observable {

	private ArrayList<Class> clsArray;
	private String curClass;

	/**
	 * Creates the classes Object
	 */
	public Classes()
	{
		clsArray = new ArrayList<Class>();
	}

	/**
	 * Adds a class to the array from its sharedPreference (created in the add class activity)
	 * @param sharedPref
	 */
	public void addClass(SharedPreferences sharedPref)
	{
		Class cls = new Class(sharedPref.getInt("crHrs", 0), sharedPref.getBoolean("passFail", false), sharedPref.getString("name", null));
		clsArray.add(cls);

		notifyObservers();
	}

	public void deleteClass(SharedPreferences classesPref, SharedPreferences deleteClassPref)
	{
		String name = deleteClassPref.getString("name", null);
		Editor classEditor = classesPref.edit();
		classEditor.remove(name);
		classEditor.putInt("size", classesPref.getInt("size", 0) - 1);

		deleteClassPref.edit().clear().commit();

		for (int i = 0; i < clsArray.size(); i++)
		{
			if (clsArray.get(i).getName() == name)
			{
				clsArray.remove(i);
			}
		}

		notifyObservers();

	}

	/**
	 * Returns a string array of the names of each class for use in the spinner
	 * @return a string array of class names
	 */
	public String[] getNameArray()
	{
		String[] classes = new String[clsArray.size()];
		for (int i = 0; i < clsArray.size(); i++)
		{
			classes[i] = clsArray.get(i).getName();
		}

		return classes;
	}

	/**
	 * Sets the current selected class
	 * @param className
	 */
	public void setCurClass(String className)
	{
		curClass = className;
	}

	/**
	 * returns the current class object the corresponds to the name
	 * @return a class object
	 */
	public Class getCurClass()
	{
		for (int i = 0; i < clsArray.size(); i++)
		{
			if (clsArray.get(i).getName() == curClass)
			{
				return clsArray.get(i);
			}
		}

		return null;
	}

}
