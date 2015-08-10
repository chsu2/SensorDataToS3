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

/** allows the user to input their personal information
 *
 */
public class EnterInfo extends AppCompatActivity {

    //instance variables
    private EditText name;
    private Spinner gender;
    private Spinner age;
    private Spinner feet;
    private Spinner inches;
    private Spinner weight;
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

        //age array
        String[] ageValues = new String[99];
        for (int i = 0; i < 99; i++) ageValues[i] = Integer.toString(i + 1);


        //age spinners
        age = (Spinner)findViewById(R.id.ageSelect);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ageValues);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(ageAdapter);


        //height spinners
        feet = (Spinner)findViewById(R.id.feet);
        inches = (Spinner)findViewById(R.id.inches);

        ArrayAdapter<CharSequence> feetAdapter = ArrayAdapter.createFromResource(this, R.array.feet_array,
                android.R.layout.simple_spinner_item);
        feetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feet.setAdapter(feetAdapter);

        ArrayAdapter<CharSequence> inchAdapter = ArrayAdapter.createFromResource(this, R.array.inch_array,
                android.R.layout.simple_spinner_item);
        inchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inches.setAdapter(inchAdapter);

        //weight array
        String[] weightValues = new String[250];
        for (int i = 0; i < 250; i++) weightValues[i] = Integer.toString(i + 50);


        //Weight spinner
        weight = (Spinner)findViewById(R.id.weightSelect);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, weightValues);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight.setAdapter(weightAdapter);

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

    public void sensorChoice(View view){

        /*fills the userInput object with the information the
        user filled out */
        userInput.setName(name.getText().toString());
        userInput.setGender(gender.getSelectedItem().toString());
        userInput.setAge(age.getSelectedItem().toString());
        userInput.setHeightIn(inches.getSelectedItem().toString());
        userInput.setHeightFt(feet.getSelectedItem().toString());
        userInput.setWeight(weight.getSelectedItem().toString());
        userInput.setActivity(activity.getSelectedItem().toString());

        Intent intent = new Intent(this, MainActivity.class);

        //transferring the userInfo object to the MainActivity class
        intent.putExtra("userInput", userInput);

       /* intent.putExtra("name", name.getText().toString());
        intent.putExtra("gender", gender.getSelectedItem().toString());
        intent.putExtra("age", age.getSelectedItem().toString());
        intent.putExtra("inches", inches.getSelectedItem().toString());
        intent.putExtra("feet", feet.getSelectedItem().toString());
        intent.putExtra("weight", weight.getSelectedItem().toString());
        intent.putExtra("activity", activity.getSelectedItem().toString());*/

        startActivity(intent);

    }
}
