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
import android.widget.Toast;


/**The main activity of the app. this allows the user to
 * choose which sensor data they would like to view.
 */
public class MainActivity extends AppCompatActivity {

    private UserInfo user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_choice);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();

        getMenuInflater().inflate(R.menu.menu_enter_info, menu);


        //get the user object from enterInfo class
        user1 = getIntent().getParcelableExtra("userInput");

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }

        return super.onOptionsItemSelected(item);
        }


    //accelerometer button onClick method
    public void accelerometer(View view){

        Intent intent = new Intent(this, Accelerometer2.class);

        //adds the userInfo object to the next activity
        intent.putExtra("user", user1);

        startActivity(intent);

    }

    //gyroscope button onClick method
    public void gyroscope(View view){

        Intent intent = new Intent(this, Gyroscope.class);

        //adds the userInfo object to the next activity
        intent.putExtra("user", user1);

        startActivity(intent);
    }

    //magnetic field button onClick method
    public void magField(View view){

        Intent intent = new Intent(this, MagField.class);

        //adds the userInfo object to the next activity
        intent.putExtra("user", user1);

        startActivity(intent);
    }

    //step counter button onClick method
    public void stepCounter(View view){

        Intent intent = new Intent(this, StepCounter.class);

        //adds the userInfo object to the next activity
        intent.putExtra("user", user1);

        startActivity(intent);

    }

    //viewAll button onClick method
    public void viewAll(View view){

        Intent intent = new Intent(this, ViewAll.class);

        //adds the userInfo object to the next activity
        intent.putExtra("user", user1);

        startActivity(intent);
    }
}
