package com.example.gradesapp;

import java.util.Observable;

import android.content.SharedPreferences;

/**
 * Model for the class display activity
 * 
 * @author Zakk
 *
 */
public class ClassDisplay extends Observable {
	private Class thisClass;
	private SharedPreferences classPref;

	public ClassDisplay (SharedPreferences classPref)
	{
		this.classPref = classPref;
		thisClass = new Class(classPref.getInt("crHrs", 0), classPref.getBoolean("passFail", false), classPref.getString("name", null));
		thisClass.setGrade(classPref.getFloat("grade", 0));
	}
	
	public Class rtrnClass()
	{
		return thisClass;
	}
	
	
	
	
}
