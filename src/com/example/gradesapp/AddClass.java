package com.example.gradesapp;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddClass extends ActionBarActivity {
	RadioGroup RadioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_class, menu);
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
	
	public void clsAdd(View v)
	{
//		RadioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
//		int selectedId = RadioGroup.getCheckedRadioButtonId();
		boolean passFail = false;
//		if (selectedId == 0)
//		{
//			passFail = true;
//		}
		
		EditText crHours = (EditText) findViewById(R.id.crHours);
		EditText className = (EditText) findViewById(R.id.className);
		
		Class cls = new Class(Integer.parseInt(crHours.getText().toString()), passFail, className.getText().toString());
		
		SharedPreferences classesPref = getSharedPreferences("Classes", Context.MODE_PRIVATE);
		Editor editorCP = classesPref.edit();
		editorCP.putInt("size", classesPref.getInt("size", 0) + 1);
		editorCP.putString(Integer.toString(classesPref.getInt("size", 0)), cls.getName());
		editorCP.commit();
		
		SharedPreferences thisClassPref = getSharedPreferences(cls.getName(), Context.MODE_PRIVATE);
		Editor editorTCP = thisClassPref.edit();
		editorTCP.putString("name", cls.getName());
		editorTCP.putBoolean("passFail", passFail);
		editorTCP.putInt("crHrs", cls.getNumCrHrs());
		editorTCP.commit();
		
		Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

	}
}
