package com.example.gradesapp;

import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Spinner;

public class Classes extends Observable {
	
	private ArrayList<Class> clsArray;
	private String curClass;
	
	public Classes()
	{
		clsArray = new ArrayList<Class>();
		
	}
	
	public void addClass(SharedPreferences sharedPref)
	{
		Class cls = new Class(sharedPref.getInt("crHrs", 0), sharedPref.getBoolean("passFail", false), sharedPref.getString("name", null));
		clsArray.add(cls);
		
		notifyObservers();
	}
	
	public String[] getNameArray()
	{
		String[] classes = new String[clsArray.size()];
		for (int i = 0; i < clsArray.size(); i++)
		{
			classes[i] = clsArray.get(i).getName();
		}
		
		return classes;
	}
	
	public void setCurClass(String className)
	{
		curClass = className;
	}
	
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
