package com.example.gradesapp;

import android.util.Log;
import java.util.ArrayList;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import br.com.kots.mob.complex.preferences.ComplexPreferences;

//-------------------------------------------------------------------------
/**
 *  Description of class.
 *
 *  @author Zakk Lefkowitz (zakkl13)
 *  @author Jason Barrett (jason95)
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.30
 */
public class Category implements Parcelable {

private String name;
private ArrayList<Assignment> assmt;
private int weight;
private Double grade;


// ---------------------------------------------------------
/**
* Create a new Assignment object.
* @param weight      The weight of the category
* @param name        The name of the category
*
*/
public Category(int weight, String name)
{
   this.weight = weight;
   this.name = name;
   assmt = new ArrayList<Assignment>();
   grade = 0.00;

}

public Category(Category cat, Context appContext)
{
	ComplexPreferences cp = ComplexPreferences.getComplexPreferences(appContext,
	    "Classes", Context.MODE_PRIVATE);

	this.weight = cat.getWeight();
	this.name = cat.getName();
	this.grade = cat.getGrade();
	assmt = new ArrayList<Assignment>();
	for (Assignment asgn: cat.getAssmts())
	{
		assmt.add(cp.getObject(name + asgn.getName(), Assignment.class));
	}
}

public void saveCategory(Context appContext)
{
	ComplexPreferences cp = ComplexPreferences.getComplexPreferences(appContext,
	    "Classes", Context.MODE_PRIVATE);

	for (Assignment asgn: assmt)
	{
		cp.putObject(name + asgn.getName(), asgn);
	}
	cp.commit();
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

/**
* gets the grade
*/
public Double getGrade() {
   return grade;
}

/**
* sets the grade
*/
public void setGrade() {
   int totPtsRcvd = 0;
   int totPtsGiven = 0;
   for (Assignment a : assmt)
   {
       totPtsRcvd += a.getPtsRecieved();
       totPtsGiven += a.getTotPts();
   }
   if (totPtsRcvd == 0 || totPtsGiven == 0)
   {
	   grade = 0.00;
   }
   else
   {
	   grade =  ((double)totPtsRcvd / (double)totPtsGiven);
   }
}

  /**
   * gets the assignment arrayList
   */
  public ArrayList<Assignment> getAssmts() {
      return assmt;
  }
  /**
   * add assignment
   */
  public void addAssmt(Assignment grade) {
      assmt.add(grade);
  }

  public void clearAssmt() {
	  assmt.clear();
	  grade = 0.00;
  }

    protected Category(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0x01) {
            assmt = new ArrayList<Assignment>();
            in.readList(assmt, Assignment.class.getClassLoader());
        } else {
            assmt = null;
        }
        weight = in.readInt();
        grade = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (assmt == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(assmt);
        }
        dest.writeInt(weight);
        if (grade == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(grade);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Category> CREATOR = new
    Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}