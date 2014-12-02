package com.example.gradesapp;

import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import br.com.kots.mob.complex.preferences.ComplexPreferences;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

// -------------------------------------------------------------------------
/**
 *  Description of class.
 *
 *  @author Zakk Lefkowitz
 *  @author Jason Barrett
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.30
 */
public class AddGradesActivity
    extends ActionBarActivity implements Observer, OnItemSelectedListener
{
    private Class thisClass;
    private AddGrades adGr;
    private String lastCategory;
    private Classes clss;
    private ArrayList<Category> categories;
    private Category currentCat;
    private Assignment assmt;
    private String gradeName;
    private Integer ptsRcv;
    private Integer ptsTot;
    private Category curCat;

    /**
     * Description of onCreate method.
     * @param savedInstanceState A saved state of the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent inte = getIntent();
        Bundle b = inte.getExtras();
		if (b != null)
		{
			clss = (Classes) b.getParcelable("Classes");
		}
		thisClass = clss.getCurClass();
		
//		ComplexPreferences cp = ComplexPreferences.getComplexPreferences(this,
//				"Classes", MODE_PRIVATE);
//		Class temp = cp.getObject(thisClass.getName(), Class.class);
//		if (temp != null)
//		{
//			thisClass = temp;
//		}

		categories = thisClass.getCats();
        updateSpinner();
        //updateTotalGrade();
        
        Spinner spinner = (Spinner) findViewById(R.id.categories);
        spinner.setOnItemSelectedListener(this);


    }
    // ----------------------------------------------------------
    /**
     * Description of button2 method. CALC BUTTON
     * @param view The view
     */
    public void button2(View view)
    {
        //get the current category chosen
        Spinner spinner = (Spinner) findViewById(R.id.categories);
        String curCategory = (String) spinner.getSelectedItem();
        currentCat = thisClass.getCategory(curCategory);
        
        EditText nameG = (EditText) findViewById(R.id.gradeName); //get values
        EditText ptsRcvd = (EditText) findViewById(R.id.name);
        EditText totPts = (EditText) findViewById(R.id.totPts);

        gradeName = nameG.getText().toString(); //make Assignment obj
        String ptsRcv = ptsRcvd.getText().toString();
        String ptsTot = totPts.getText().toString();
        if (gradeName == null)
        {
        	gradeName = "NONAME";
        }
        
        if (!isInteger(ptsRcv))
        {
        	Toast.makeText(this, "Please enter a value for Points Recieved", Toast.LENGTH_SHORT).show();
        }
        else if (!isInteger(ptsTot))
        {
        	Toast.makeText(this, "Please enter a value for Total Points", Toast.LENGTH_SHORT).show();
        }
        else
        {
            assmt = new Assignment(gradeName, Integer.parseInt(ptsTot), Integer.parseInt(ptsRcv));
            currentCat.addAssmt(assmt);
            clss.saveModel(getApplicationContext());
            
            currentCat.setGrade();
            TextView finalGrade = (TextView) findViewById(R.id.grade);
            finalGrade.setText(String.valueOf(currentCat.getGrade()));
            
            clss.saveModel(getApplicationContext());
            
            Intent intent = new Intent(this, ClassDisplayActivity.class);
            intent.putExtra("Classes", clss);
            startActivity(intent);
        }
        

    }

    private boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }
    
    // ----------------------------------------------------------
    /**
     * Description of addAnother method.
     * @param view The view
     */
    public void addAnother(View view)
    {

        String grade; //hold grade ex: 15/20
        //get the current category chosen
        Spinner spinner = (Spinner) findViewById(R.id.categories);
        String curCategory = (String) spinner.getSelectedItem();
        currentCat = thisClass.getCategory(curCategory);

        EditText nameG = (EditText) findViewById(R.id.gradeName); //get values
        EditText ptsRcvd = (EditText) findViewById(R.id.name);
        EditText totPts = (EditText) findViewById(R.id.totPts);


        gradeName = nameG.getText().toString(); //make Assignment obj
        String ptsRcv = ptsRcvd.getText().toString();
        String ptsTot = totPts.getText().toString();
        if (gradeName == null)
        {
        	gradeName = "NONAME";
        }
        
        if (!isInteger(ptsRcv))
        {
        	Toast.makeText(this, "Please enter a value for Points Recieved", Toast.LENGTH_SHORT).show();
        }
        else if (!isInteger(ptsTot))
        {
        	Toast.makeText(this, "Please enter a value for Total Points", Toast.LENGTH_SHORT).show();
        }
        else
        {
            assmt = new Assignment(gradeName, Integer.parseInt(ptsTot), Integer.parseInt(ptsRcv));
            currentCat.addAssmt(assmt);
            clss.saveModel(getApplicationContext());
            
            currentCat.setGrade();
            TextView finalGrade = (TextView) findViewById(R.id.grade);
            finalGrade.setText(String.valueOf(currentCat.getGrade()));
            
            nameG.setText("");
            ptsRcvd.setText("");//reset them
            totPts.setText("");
        }

    }
    
    // ----------------------------------------------------------
    /**
     * takes you home
     * @param view The view
     */
    public void home(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    // ----------------------------------------------------------

    /**
     * Description of addCat method.
     * @param view The view
     */
    public void addCat(View view)
    {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        intent.putExtra("Classes", clss);
        startActivity(intent);
    }

    public void delete(View view)
    {
    	Spinner spinner = (Spinner) findViewById(R.id.classSpinner);
    	String catName = (String) spinner.getSelectedItem();

    	thisClass.removeCategory(catName);
    	clss.saveModel(getApplicationContext());
    	updateSpinner();

    }
    
    public void clear(View view)
    {
    	for (Category c : categories)
        {
    		c.clearAssmt();
        }
    	
    }

    public void updateSpinner()
    {
		//Sets the spinner to display the string array of the names of the classes
        Spinner spinner = (Spinner) findViewById(R.id.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thisClass.getCatNameArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Description of the onCreateOptionsMenu method.
     * @param menu The menu of the app
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    /**
     * Description of the onOptionsItemSelected method.
     * @param item The individual item in the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void update(Observable arg0, Object arg1)
    {
        // TODO Auto-generated method stub

    }
	@Override
	public void onItemSelected(AdapterView<?> adapt, View v, int pos,
			long arg3) {
		TextView finalGrade = (TextView) findViewById(R.id.grade);
        finalGrade.setText(String.valueOf(categories.get(pos).getGrade()));
        
        
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
