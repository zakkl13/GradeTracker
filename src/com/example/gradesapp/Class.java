package com.example.gradesapp;

import java.util.ArrayList;

public class Class {
	
	int numCrHrs;
	boolean passFail;
	String name;
	ArrayList<Assignment> assignments;
	float grade;
	
	public Class(int numCrHrs, boolean passFail, String name)
	{
		this.numCrHrs = numCrHrs;
		this.passFail = passFail;
		this.name = name;
		assignments = new ArrayList<Assignment>();
	}
	
	public void addAssignment(Assignment assgn)
	{
		
	}
	
	
	//Getters and Setters
	public int getNumCrHrs() {
		return numCrHrs;
	}

	public void setNumCrHrs(int numCrHrs) {
		this.numCrHrs = numCrHrs;
	}

	public boolean isPassFail() {
		return passFail;
	}

	public void setPassFail(boolean passFail) {
		this.passFail = passFail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(ArrayList<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	public void setGrade(float grade)
	{
		this.grade = grade;
	}
	
	public float getGrade()
	{
		return grade;
	}

}
