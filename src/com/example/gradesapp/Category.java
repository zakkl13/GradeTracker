package com.example.gradesapp;

//-------------------------------------------------------------------------
/**
*  Description of the Category class.
*
*  @author Zakk Lefkowitz
*  @author Jason Barrett
*  @author Tanner Hudson
*  @version 2014.11.30
*/
public class Category {

 private int weight;
 private String name;


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

}
