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
    private String curClass;
    /**
     * Description of onCreate method.
     * @param savedInstanceState The saved state of the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment_weight);
        Intent inte = getIntent();
        Bundle b = inte.getExtras();
        if (b != null)
        {
            curClass = (String) b.get("name");
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
        Category cat = new Category(Integer.parseInt(percent.getText().toString()), catName.getText().toString());

        //Edit the "Classes" shared preferences, holds a "size" key with the number of classes
        //And a list of classes where the key is a number
        SharedPreferences curClassCategories = getSharedPreferences("Category" + curClass, Context.MODE_PRIVATE);
        Editor editorCP = curClassCategories.edit();
        editorCP.putInt("size", curClassCategories.getInt("size", 0) + 1); //increases size by 1
        editorCP.putString(Integer.toString(curClassCategories.getInt("size", 0)), cat.getName()); //adds a new key with the class name
        editorCP.commit();

        //Creates a new SharedPreference with the name being the class name
        //Used to hold the classes attributes
        SharedPreferences thisCatPref = getSharedPreferences(cat.getName(), Context.MODE_PRIVATE);
        Editor editorTCP = thisCatPref.edit();
        editorTCP.putInt("percent", cat.getWeight());
        editorTCP.putString("name", cat.getName());
        editorTCP.commit();

        //Return to main activity menu
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("name", curClass);
        startActivity(intent);

    }

    /**
     * Description of onCreateOptionsMenu method.
     * @param menu The menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
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
