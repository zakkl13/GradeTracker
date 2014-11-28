package com.example.gradesapp;

public class Assignment {

	int weight;
	String name;
	int totPts;
	int ptsRecieved;
	
	public Assignment(int weight, String name, int totPts, int ptsRecieved)
	{
		this.weight = weight;
		this.name = name;
		this.totPts = totPts;
		this.ptsRecieved = ptsRecieved;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotPts() {
		return totPts;
	}

	public void setTotPts(int totPts) {
		this.totPts = totPts;
	}

	public int getPtsRecieved() {
		return ptsRecieved;
	}

	public void setPtsRecieved(int ptsRecieved) {
		this.ptsRecieved = ptsRecieved;
	}
	
	
	
}
