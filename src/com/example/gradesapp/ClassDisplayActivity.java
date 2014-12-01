package com.example.gradesapp;

import android.view.View;
import java.util.Observable;
import java.util.Observer;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

/**
 *
 * @author Zakk
 * @version 2014.11.29
 */
public class ClassDisplayActivity extends ActionBarActivity implements Observer {
	private Class thisClass;
	private String cName = null;

	/**
	 * Gets the current class from getExtras() through the intent
	 * identifies as an observer of the model
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_display);

		Intent inte = getIntent();
		Bundle b = inte.getExtras();
		if (b != null)
		{
			thisClass = (Class) b.getParcelable("class");
		}
		
		updateDisplay();
	}

	/**
	 * Updates the display with the current class
	 */
	public void updateDisplay()
	{
		TextView name = (TextView) findViewById(R.id.clsName);
		name.setText(thisClass.getName());
		cName = (String)name.getText();

		TextView grade = (TextView) findViewById(R.id.curGrade);
		//TODO parse float to String below
		//grade.setText(clsDisp.getClass().getGrade());

		TextView hours = (TextView) findViewById(R.id.hours);
		hours.setText("Credit Hours: " + thisClass.getNumCrHrs());
	}
    /**
     * Opens the addClass Activity
     * @param view the button
     */
    public void addGrades(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("class", thisClass);
        startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}
