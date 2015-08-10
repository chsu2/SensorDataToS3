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
    private UserInfo user2;

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

        getMenuInflater().inflate(R.menu.menu_enter_info, menu);

        //Intent i = getIntent();
        //user1 = i.getParcelableExtra("userInput");

        /*user1 = new UserInfo();
        user1.setName(i.getStringExtra("name"));
        user1.setGender(i.getStringExtra("gender"));
        user1.setAge(i.getStringExtra("age"));
        user1.setHeightFt(i.getStringExtra("feet"));
        user1.setHeightIn(i.getStringExtra("inches"));
        user1.setWeight(i.getStringExtra("weight"));
        user1.setActivity(i.getStringExtra("activity"));*/

        //get the user object from enterInfo class
        user1 = getIntent().getParcelableExtra("userInput");
        //user2 = new UserInfo(user1);
/*
        user2 = new UserInfo();
        user2.setActivity(user1.getActivity());
        user2.setName(user1.getName());
        user2.setOrientation(user1.getOrientation());
        user2.setAge(user1.getAge());
        user2.setHeightFt(user1.getHeightFt());
        user2.setWeight(user1.getWeight());
        user2.setGender(user1.getGender());
        user2.setHeightIn(user1.getHeightIn());
        user2.setLastLogFile(user1.getLastLogFile());
        user2.setLogFiles(user1.getLogFiles());
        user2.setEmail(user1.getEmail());
       */

        //if (user != null)  Toast.makeText(this, "User data written", Toast.LENGTH_SHORT).show();

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


    public void accelerometer(View view){

        Intent intent = new Intent(this, Accelerometer2.class);
        intent.putExtra("user", user1);

        startActivity(intent);

    }

    public void gyroscope(View view){

        Intent intent = new Intent(this, Gyroscope.class);
        //intent.putExtra("user", user);

        startActivity(intent);
    }


    public void magField(View view){

        Intent intent = new Intent(this, MagField.class);
        //intent.putExtra("user", user);

        startActivity(intent);
    }


    public void stepCounter(View view){

        Intent intent = new Intent(this, StepCounter.class);
        //intent.putExtra("user", user);

        startActivity(intent);

    }

    public void viewAll(View view){

        Intent intent = new Intent(this, ViewAll.class);
        //intent.putExtra("user", user);

        startActivity(intent);
    }
}
