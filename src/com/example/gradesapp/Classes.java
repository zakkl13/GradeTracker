package com.example.gradesapp;

import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Spinner;

/**
 *
 * @author Zakk
 *
 */
public class Classes extends Observable implements Parcelable {

	private ArrayList<Class> clsArray;
	private Class curClass;

	/**
	 * Creates the classes Object
	 */
	public Classes()
	{
		clsArray = new ArrayList<Class>();
	}

	/**
	 * Adds a class to the array from its sharedPreference (created in the
	 * add class activity)
	 * @param sharedPref
	 */
	public void addClass(Class cls)
	{
		clsArray.add(cls);

		notifyObservers();
	}

	public void deleteClass()
	{
		clsArray.remove(getCurClass());

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
		for (int i = 0; i < clsArray.size(); i++)
		{
			if (clsArray.get(i).getName() == className)
			{
				curClass = clsArray.get(i);
			}
		}
	}

	/**
	 * returns the current class object the corresponds to the name
	 * @return a class object
	 */
	public Class getCurClass()
	{
		
		return curClass;
	}
	
    protected Classes(Parcel in) {
        if (in.readByte() == 0x01) {
            clsArray = new ArrayList<Class>();
            in.readList(clsArray, Class.class.getClassLoader());
        } else {
            clsArray = null;
        }
        curClass = (Class) in.readValue(Class.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (clsArray == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(clsArray);
        }
        dest.writeValue(curClass);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Classes> CREATOR = new Parcelable.Creator<Classes>() {
        @Override
        public Classes createFromParcel(Parcel in) {
            return new Classes(in);
        }

        @Override
        public Classes[] newArray(int size) {
            return new Classes[size];
        }
    };
}