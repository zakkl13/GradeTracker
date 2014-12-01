package com.example.gradesapp;
//edited by zakk
//also edited by Tanner
import java.util.Observable;
import java.util.Observer;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity
    extends ActionBarActivity implements Observer
{
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private Classes clss;
    private SharedPreferences classesPref;

    @Override
    /**
     * Populates the spinner with the User's classes and updates the model with the classes
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  
        
        //Retrieve the list of classes
		classesPref = this.getSharedPreferences("Classes", Context.MODE_PRIVATE);
        clss = new Classes();
        clss.addObserver(this);
        
        //Get a reference to the number of classes
        int numClasses = classesPref.getInt("size", 0);
        
        //runs through the sharedpreferences and adds each class to the model's array
        for (int i = 0; i < numClasses; i++)
        {
        	clss.addClass(this.getSharedPreferences(classesPref.getString(Integer.toString(i), null), Context.MODE_PRIVATE));
        }

        //Sets the spinner to display the string array of the names of the classes
        Spinner spinner = (Spinner) findViewById(R.id.classSpinner);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, clss.getNameArray());
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
        
    }
    
    /**
     * Opens the addClass Activity
     * @param view the button
     */
    public void addClass(View view)
    {
    	Intent intent = new Intent(this, AddClassActivity.class);
        startActivity(intent);
    }
    
    /**
     * Gets the selected class and passes it via Intent Extras to the Class Display View 
     * @param view the button
     */
    public void goToClass(View view)
    {
    	Spinner spinner = (Spinner) findViewById(R.id.classSpinner);
    	String curClass = (String) spinner.getSelectedItem();    	
    	
    	Intent intent = new Intent(this, ClassDisplayActivity.class);
    	intent.putExtra("class", curClass);
        startActivity(intent);
    }
    
    public void deleteClass(View view)
    {
    	Spinner spinner = (Spinner) findViewById(R.id.classSpinner);
    	String curClass = (String) spinner.getSelectedItem(); 
    	
		SharedPreferences deleteClassPref = this.getSharedPreferences(curClass, Context.MODE_PRIVATE);
    	
    	clss.deleteClass(classesPref, deleteClassPref);    	
    	
    	Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


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
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
