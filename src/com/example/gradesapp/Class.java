package com.example.gradesapp;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

// -------------------------------------------------------------------------
/**
 *  Creates a new Class.
 *
 *  @author Zakk Lefkowitz
 *  @author Jason Barrett
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.14
 */
public class Class implements Parcelable {

	private int numCrHrs;
	private boolean passFail;
	private String name;
	private ArrayList<Category> categories;
	private float grade;

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
	public void setGrade(float grade)
	{
		this.grade = grade;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the value of the grade in the class.
	 * @return Returns the grade in the class
	 */
	public float getGrade()
	{
		return grade;
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
        grade = in.readFloat();
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
        dest.writeFloat(grade);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Class> CREATOR = new Parcelable.Creator<Class>() {
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