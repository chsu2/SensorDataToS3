package com.example.caroline.sojung2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/** opening activity
 *
 * allows the user to input their personal information
 *
 */
public class EnterInfo extends AppCompatActivity {

    //instance variables
    private EditText name;
    private Spinner gender;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Spinner activity;

    protected UserInfo userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        userInput = new UserInfo();

        //name
        name = (EditText)findViewById(R.id.enterName);

        //gender spinner
        gender = (Spinner)findViewById(R.id.genderSelect);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender_array,
                android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        //age input
        age = (EditText)findViewById(R.id.ageSelect);

        //height input
        feet = (EditText)findViewById(R.id.feet);
        inches = (EditText)findViewById(R.id.inches);

        //Weight input
        weight = (EditText)findViewById(R.id.weightSelect);

        //activity spinner
        activity = (Spinner)findViewById(R.id.activitySelect);
        ArrayAdapter<CharSequence> activityAdapter = ArrayAdapter.createFromResource(this, R.array.activity_array,
                android.R.layout.simple_spinner_item);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.setAdapter(activityAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_info, menu);
        return true;
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

    //called when the "enter" button is pressed
    public void sensorChoice(View view){

        /*fills the userInput object with the information the`
        user filled out */
        userInput.setName(name.getText().toString());
        userInput.setGender(gender.getSelectedItem().toString());
        userInput.setAge(age.getText().toString());
        userInput.setHeightIn(inches.getText().toString());
        userInput.setHeightFt(feet.getText().toString());
        userInput.setWeight(weight.getText().toString());
        userInput.setActivity(activity.getSelectedItem().toString());

        Intent intent = new Intent(this, MainActivity.class);

        //transferring the userInfo object to the MainActivity class
        intent.putExtra("userInput", userInput);

        startActivity(intent);

    }
}
