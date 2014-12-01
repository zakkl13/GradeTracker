package com.example.gradesapp;

import br.com.kots.mob.complex.preferences.ComplexPreferences;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

// -------------------------------------------------------------------------
/**
 *  Description of class.
 *
 *  @author Zakk Lefkowitz
 *  @author Jason Barrett
 *  @author Tanner Hudson
 *  @version 2014.11.30
 */
/**
 */
public class AddClassActivity extends ActionBarActivity {
	private RadioGroup RadioGroup;
	Classes clss;

	/**
	 * Description of onCreate method.
	 * @param savedInstanceState The saved state of the instance
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_class);
		Intent inte = getIntent();
		Bundle b = inte.getExtras();
		if (b != null)
		{
			clss = (Classes) b.getParcelable("Classes");
		}
	}

	/**
	 * Description of onCreateOptionsMenu method.
	 * @param menu The menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present
		getMenuInflater().inflate(R.menu.add_class, menu);
		return true;
	}

	/**
	 * Description of onOptionsItemSelected method.
	 * @param item The individual item in the menu
	 */
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

	/**
	 * Description of the clsAdd method.
	 * @param v The view
	 */
	public void clsAdd(View v)
	{
		//This code block checks which radio button is selected in the radio
	    //group
		RadioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		int selectedId = RadioGroup.getCheckedRadioButtonId();
		boolean passFail = false;
		if (selectedId == 0)
		{
			passFail = true;
		}

		//Get references to edit text fields
		EditText crHours = (EditText) findViewById(R.id.percent);
		EditText className = (EditText) findViewById(R.id.categoryName);

		//Create a class object with the information from the editText fields
		Class cls = new Class(Integer.parseInt(crHours.getText().toString()),
		    passFail, className.getText().toString());

		clss.addClass(cls);

	    ComplexPreferences complexPreferences = ComplexPreferences.
	        getComplexPreferences(this, "Classes", MODE_PRIVATE);
	    complexPreferences.putObject("Model", clss);
	    complexPreferences.commit();

		//Return to main activity menu
		Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

	}
}

