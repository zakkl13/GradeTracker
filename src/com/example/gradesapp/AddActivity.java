package com.example.gradesapp;

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

// -------------------------------------------------------------------------
/**
 *  Description of class.
 *
 *  @author Zakk Lefkowitz
 *  @author Jason Barrett
 *  @author Tanner Hudson (tannerh4)
 *  @version 2014.11.30
 */
public class AddActivity
    extends ActionBarActivity implements Observer
{
    private Class curClass;
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
            curClass = (String) b.get("name");
        }
        SharedPreferences classCategoriesPref = this.getSharedPreferences(
            "Categories" + curClass, Context.MODE_PRIVATE);
        cat = new Categories();
        cat.addObserver(this);

        //Get a reference to the number of categories
        int numClasses = classCategoriesPref.getInt("size", 0);

        //runs through the sharedpreferences and adds each class to the model's
        //array
        for (int i = 0; i < numClasses; i++)
        {
            cat.addCategory(this.getSharedPreferences(classCategoriesPref.
                getString(Integer.toString(i), null), Context.MODE_PRIVATE));
        }

        //Sets the spinner to display the string array of the names of the
        //classes
        Spinner spinner = (Spinner) findViewById(R.id.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.
            R.layout.simple_spinner_item, cat.getNameArray());
        adapter.setDropDownViewResource(android.R.layout.
            simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    // ----------------------------------------------------------
    /**
     * Description of button2 method.
     * @param view The view
     */
    public void button2(View view)
    {
        Intent intent = new Intent(this, GradeCalculatorActivity.class);
        startActivity(intent);
    }
    // ----------------------------------------------------------
    /**
     * Description of addAnother method.
     * @param view The view
     */
    public void addAnother(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
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
        intent.putExtra("name", curClass);
        startActivity(intent);
    }

    /**
     * Description of the onCreateOptionsMenu method.
     * @param menu The menu of the app
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present
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
}
