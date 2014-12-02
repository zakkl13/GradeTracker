package com.example.gradesapp;

import android.util.Log;
import br.com.kots.mob.complex.preferences.ComplexPreferences;
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
 *  This class is used in order to add activities to the categories menu.
 *
 *  @author Zakk Lefkowitz (zakkl13)
 *  @author Jason Barrett (jason95)
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.30
 */
public class AddCategoryActivity
    extends ActionBarActivity
{
    private Classes clss;
    /**
     * This method runs when an activity is created. It sets the content view
     * and also gets the intent; this is all done using the saved instance
     * state.
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
			clss = (Classes) b.getParcelable("Classes");
		}

    }

    /**
     * This method is used in order to add new categories to the menu.
     * @param v The view for the app
     */
    public void catAdd(View v)
    {
        //crHours
        //className
        //Get references to edit text fields
        EditText percent = (EditText) findViewById(R.id.percent);
        EditText catName = (EditText) findViewById(R.id.ptsRcv);

        //Create a class object with the information from the editText fields
        Category cat = new Category(Integer.parseInt(percent.getText().
            toString()), catName.getText().toString());

        clss.getCurClass().addCategory(cat);
        clss.saveModel(getApplicationContext());

//        ComplexPreferences complexPreferences = ComplexPreferences.
//    	        getComplexPreferences(this, "Classes", MODE_PRIVATE);
//    	    complexPreferences.putObject("Model", clss);
//    	    complexPreferences.commit();

        //Return to main activity menu
        Intent intent = new Intent(this, AddGradesActivity.class);
        intent.putExtra("Classes", clss);
        startActivity(intent);

    }

    /**
     * This method handles the menu for when the options button is pressed.
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
     * This method handles when items on the action bar are clicked.
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
