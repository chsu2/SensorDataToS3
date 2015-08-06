package com.example.caroline.sojung2;

//can hit option return on a mac to import all missing classes
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.caroline.sojung2.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_choice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_activity_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //original**
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //original**
        if (id == R.id.action_settings) {
        //original**
        return true;
        //original**
        }

        //original**
        //
        return super.onOptionsItemSelected(item);
        //original **
        }

        //handle presses on the action bar items
        //switch (item.getItemId()) {

           // case R.id.action_search:
             //   openSearch();
             //   return true;
           // case R.id.action_settings:
           //     openSettings();
           //     return true;
         //   default:
            //    return super.onOptionsItemSelected(item);
      //  }
 //   }


    public void accelerometer(View view){

        Intent intent = new Intent(this, Accelerometer2.class);

        startActivity(intent);

    }

    public void gyroscope(View view){

        Intent intent = new Intent(this, Gyroscope.class);
        startActivity(intent);
    }

   // public void light(View view){

    //    Intent intent = new Intent(this, Light.class);
      //  startActivity(intent);
    //}

    public void magField(View view){

        Intent intent = new Intent(this, MagField.class);
        startActivity(intent);
    }

   // public void temperature(View view){

    //    Intent intent = new Intent(this, Temperature.class);
      //  startActivity(intent);
    //}

    public void stepCounter(View view){

        Intent intent = new Intent(this, StepCounter.class);
        startActivity(intent);

    }

    public void viewAll(View view){

        Intent intent = new Intent(this, ViewAll.class);
        startActivity(intent);
    }
}
