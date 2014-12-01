package com.example.gradesapp;

import android.os.Parcel;
import android.os.Parcelable;

// -------------------------------------------------------------------------
/**
 *  Creates a new Assignment.
 *
 *  @author Zakk Lefkowitz
 *  @author Jason Barrett
 *  @author Tanner Hudson
 *  @version 2014.11.30
 */
public class Assignment implements Parcelable {

	private int weight;
	private String name;
	private int totPts;
	private int ptsRecieved;

	// ----------------------------------------------------------
	/**
	 * Create a new Assignment object.
	 * @param weight      The weight of the assignment
	 * @param name        The name of the assignment
	 * @param totPts      The total points the assignment is worth
	 * @param ptsRecieved The points received on the assignment
	 */
	public Assignment(int weight, String name, int totPts, int ptsRecieved)
	{
		this.weight = weight;
		this.name = name;
		this.totPts = totPts;
		this.ptsRecieved = ptsRecieved;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the weights of the assignment.
	 * @return Returns the value of the weight
	 */
	public int getWeight() {
		return weight;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the weight of the assignment.
	 * @param weight The assignment's weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
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
	
    protected Assignment(Parcel in) {
        weight = in.readInt();
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
        dest.writeInt(weight);
        dest.writeString(name);
        dest.writeInt(totPts);
        dest.writeInt(ptsRecieved);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Assignment> CREATOR = new Parcelable.Creator<Assignment>() {
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