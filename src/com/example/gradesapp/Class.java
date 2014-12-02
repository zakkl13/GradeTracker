package com.example.gradesapp;

import java.util.ArrayList;

import br.com.kots.mob.complex.preferences.ComplexPreferences;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

//-------------------------------------------------------------------------
/**
 *  Creates a new class object.
 *
 *  @author Zakk Lefkowitz (zakkl13)
 *  @author Jason Barrett (jason95)
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.30
 */
public class Class implements Parcelable {

	private int numCrHrs;
	private boolean passFail;
	private String name;
	private ArrayList<Category> categories;
	private double grade;

	// ----------------------------------------------------------
	/**
	 * Create a new Class object.
	 * @param numCrHrs  The number of credit hours
	 * @param passFail  If the class is pass/fail or not
	 * @param name      The name of the class
	 */
	public Class(int numCrHrs, boolean passFail, String name)
	{
		this.numCrHrs = numCrHrs;
		this.passFail = passFail;
		this.name = name;
		this.categories = new ArrayList<Category>();
		grade = 0.00;
	}

	public Class(Class cls, Context appContext)
	{
		ComplexPreferences cp = ComplexPreferences.getComplexPreferences(
		    appContext, "Classes", Context.MODE_PRIVATE);

		this.numCrHrs = cls.getNumCrHrs();
		this.passFail = cls.isPassFail();
		this.name = cls.getName();
		this.grade = cls.getGrade();
		this.categories = new ArrayList<Category>();
		for (Category cat: cls.getCats())
		{
			categories.add(new Category(cp.getObject(name + cat.getName(),
			    Category.class), appContext));
			Log.d("setCat", cat.getName());
		}

	}

	// ----------------------------------------------------------
	/**
	 * Setup for adding assignments.
	 * @param assgn An assignment object
	 */
	public void addCategory(Category cat)
	{
	    categories.add(cat);
	}

	public void removeCategory(String catName)
	{
		categories.remove(getCategory(catName));

	}

	public Category getCategory(String catName)
	{
		for (int i = 0; i < categories.size(); i++)
		{
			if (categories.get(i).getName().equals(catName))
			{
				return categories.get(i);
			}
		}

		return null;
	}

	public String[] getCatNameArray()
	{
		String[] cats = new String[categories.size()];
		for (int i = (categories.size() - 1); i >= 0; i--)
		{
			cats[i] = categories.get(i).getName();
		}

		return cats;
	}

	public void saveClass(Context appContext)
	{
		ComplexPreferences cp = ComplexPreferences.getComplexPreferences(
		    appContext, "Classes", Context.MODE_PRIVATE);
		for (Category cat: categories)
		{
			cat.saveCategory(appContext);
		    cp.putObject(name + cat.getName(), cat);
		    Log.d("saveCat", cat.getName());
		}
		cp.commit();
	}


	//Getters and Setters
	// ----------------------------------------------------------
	/**
	 * Get the number of credit hours.
	 * @return Returns the number of credit hours
	 */
	public int getNumCrHrs() {
		return numCrHrs;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the number of credit hours.
	 * @param numCrHrs The value of the number of credit hours
	 */
	public void setNumCrHrs(int numCrHrs) {
		this.numCrHrs = numCrHrs;
	}

	// ----------------------------------------------------------
	/**
	 * Determines if the class is pass/fail or not.
	 * @return Returns true when the class is pass/fail, otherwise false
	 */
	public boolean isPassFail() {
		return passFail;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the class to be pass/fail.
	 * @param passFail True if the class is pass/fail
	 */
	public void setPassFail(boolean passFail) {
		this.passFail = passFail;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the name of the class.
	 * @return Returns the name of the class
	 */
	public String getName() {
		return name;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the name of the class.
	 * @param name The value of the class's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	// ----------------------------------------------------------
	/**
	 * Gets a list of all the assignments that have been input.
	 * @return Returns a list of assignments
	 */
	public ArrayList<Category> getCats() {
		return categories;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the value of the grade in the class.
	 * @param grade The grade in the class
	 */
	public void setGrade(double grade)
	{
		this.grade = grade;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the value of the grade in the class.
	 * @return Returns the grade in the class
	 */
	public double getGrade()
	{
    	double totalGrade = 0.0;

    	for (Category c : categories)
        {
            c.setGrade();
            Log.d("grade", c.getGrade() + "");
            totalGrade += c.getGrade() * c.getWeight();
            Log.d("totalGrade", totalGrade + "");

        }

    	return totalGrade;
	}

    protected Class(Parcel in) {
        numCrHrs = in.readInt();
        passFail = in.readByte() != 0x00;
        name = in.readString();
        if (in.readByte() == 0x01) {
            categories = new ArrayList<Category>();
            in.readList(categories, Category.class.getClassLoader());
        } else {
            categories = null;
        }
        grade = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numCrHrs);
        dest.writeByte((byte) (passFail ? 0x01 : 0x00));
        dest.writeString(name);
        if (categories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(categories);
        }
        dest.writeDouble(grade);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Class> CREATOR = new
    Parcelable.Creator<Class>() {
        @Override
        public Class createFromParcel(Parcel in) {
            return new Class(in);
        }

        @Override
        public Class[] newArray(int size) {
            return new Class[size];
        }
    };
}