package com.example.gradesapp;
//edited by zakk
//also edited by Tanner
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity
    extends ActionBarActivity
{
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Class1(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
    public void Class2(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }

    public void Class3(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }

    public void Class4(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
    public void newClass1(View view)
    {
        Intent intent = new Intent(this, Class1Activity.class);
        startActivity(intent);

    }
    public void newClass2(View view)
    {
        Intent intent = new Intent(this, Class2Activity.class);
        startActivity(intent);

    }

    public void newClass3(View view)
    {
        Intent intent = new Intent(this, Class3Activity.class);
        startActivity(intent);

    }

    public void newClass4(View view)
    {
        Intent intent = new Intent(this, Class4Activity.class);
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
}
