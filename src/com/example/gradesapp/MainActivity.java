package com.example.gradesapp;
//edited by zakk
//also edited by tanner
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity
    extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void class1Clicked()
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
    public void class2Clicked()
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }

    public void class3Clicked()
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }

    public void class4Clicked()
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
    public void newClass1Clicked()
    {
        Intent intent = new Intent(this, Class1Activity.class);
        startActivity(intent);

    }
    public void newClass2Clicked()
    {
        Intent intent = new Intent(this, Class2Activity.class);
        startActivity(intent);

    }

    public void newClass3Clicked()
    {
        Intent intent = new Intent(this, Class3Activity.class);
        startActivity(intent);

    }

    public void newClass4Clicked()
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
