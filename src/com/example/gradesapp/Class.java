package com.example.gradesapp;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  Creates a new Class.
 *
 *  @author Zakk Lefkowitz
 *  @author Jason Barrett
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.14
 */
public class Class {

	private int numCrHrs;
	private boolean passFail;
	private String name;
	private ArrayList<Assignment> assignments;
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
		assignments = new ArrayList<Assignment>();
	}

	// ----------------------------------------------------------
	/**
	 * Setup for adding assignments.
	 * @param assgn An assignment object
	 */
	public void addAssignment(Assignment assgn)
	{
	    //Intentionally blank
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
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the list of assignments.
	 * @param assignments The list of assignments
	 */
	public void setAssignments(ArrayList<Assignment> assignments) {
		this.assignments = assignments;
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

}
