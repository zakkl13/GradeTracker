package com.example.gradesapp;

import android.os.Parcel;
import android.os.Parcelable;

//-------------------------------------------------------------------------
/**
 *  This class handles the creation of a new assignment object.
 *
 *  @author Zakk Lefkowitz (zakkl13)
 *  @author Jason Barrett (jason95)
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.30
 */
public class Assignment implements Parcelable {

	private String name;
	private int totPts;
	private int ptsRecieved;

	// ----------------------------------------------------------
	/**
	 * Create a new Assignment object.
	 * @param name        The name of the assignment
	 * @param totPts      The total points the assignment is worth
	 * @param ptsRecieved The points received on the assignment
	 */
	public Assignment( String name, int totPts, int ptsRecieved)
	{
		this.name = name;
		this.totPts = totPts;
		this.ptsRecieved = ptsRecieved;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the name of the assignment.
	 * @return Returns the value of the name
	 */
	public String getName() {
		return name;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the name of the assignment.
	 * @param name The name of the assignment
	 */
	public void setName(String name) {
		this.name = name;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the total points the assignment is worth.
	 * @return Returns the value of the total points
	 */
	public int getTotPts() {
		return totPts;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the total points the assignment is worth.
	 * @param totPts The total points the assignment is worth
	 */
	public void setTotPts(int totPts) {
		this.totPts = totPts;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the points received on the assignment.
	 * @return Returns the value of the points received on the assignment
	 */
	public int getPtsRecieved() {
		return ptsRecieved;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the points received on the assignment.
	 * @param ptsRecieved The value of the points received on the assignment.
	 */
	public void setPtsRecieved(int ptsRecieved) {
		this.ptsRecieved = ptsRecieved;
	}

	public String toString()
	{
	    String str = name + ": " +
            String.valueOf(ptsRecieved)
            + " / " + String.valueOf(totPts);

	        return str;
	}

/**
 * This protected method protects the values in assignment.
 * @param in The parcel
 */
 protected Assignment(Parcel in) {
     name = in.readString();
     totPts = in.readInt();
     ptsRecieved = in.readInt();
 }

 @Override
 public int describeContents() {
     return 0;
 }

 @Override
 public void writeToParcel(Parcel dest, int flags) {
     dest.writeString(name);
     dest.writeInt(totPts);
     dest.writeInt(ptsRecieved);
 }

 /**
 * Not sure what this is, someone else edit?
 */
public static final Parcelable.Creator<Assignment> CREATOR = new
 Parcelable.Creator<Assignment>() {
     @Override
     public Assignment createFromParcel(Parcel in) {
         return new Assignment(in);
     }

     @Override
     public Assignment[] newArray(int size) {
         return new Assignment[size];
     }
 };
}