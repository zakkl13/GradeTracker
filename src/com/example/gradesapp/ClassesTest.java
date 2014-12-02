package com.example.gradesapp;

import junit.framework.TestCase;

public class ClassesTest extends TestCase {
	
	private Classes clss;	
	private Class clas1;
	private Class clas2;
	private Class clas3;
	
	public void setUp()
	{
		clss = new Classes();
		
		clas1 = new Class(0, true, "clas1");
		clas2 = new Class(0, true, "clas2");
		clas3 = new Class(0, true, "clas3");
		
		clss.addClass(clas1);
		clss.addClass(clas2);
		clss.addClass(clas3);
	}
	
	public void testGetCurClass()
	{
		clss.setCurClass("clas2");
		
		assertEquals(clss.getCurClass(), clas2);
	}
	
	public void testGetCurClassNull()
	{
		clss.setCurClass("clas10");
		
		assertNull(clss.getCurClass());
	}
	
	public void testRemoveClass()
	{
		clss.setCurClass("clas2");
		clss.deleteClass();
		
		assertEquals(clss.getClsArray().get(1), clas3);
		assertEquals(clss.getClsArray().size(), 2);
	}
}
