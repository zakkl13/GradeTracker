package com.example.gradesapp;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
public class AddCategoryActivity
    extends ActionBarActivity
{
    private Class thisClass;
    /**
     * Description of onCreate method.
     * @param savedInstanceState The saved state of the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Intent inte = getIntent();
		Bundle b = inte.getExtras();
		if (b != null)
		{
			thisClass = (Class) b.getParcelable("class");
		}
    }
    public void catAdd(View v)
    {
        //crHours
        //className
        //Get references to edit text fields
        EditText percent = (EditText) findViewById(R.id.percent);
        EditText catName = (EditText) findViewById(R.id.categoryName);

        //Create a class object with the information from the editText fields
        Category cat = new Category(Integer.parseInt(percent.getText().
            toString()), catName.getText().toString());

        thisClass.addCategory(cat);

        //Return to main activity menu
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("class", thisClass);
        startActivity(intent);

    }

    /**
     * Description of onCreateOptionsMenu method.
     * @param menu The menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.add_assignment_weight, menu);
        return true;
    }

    /**
     * Description of onOptionsItemSelected method.
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
}
