package com.example.caroline.sojung2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EnterInfo extends AppCompatActivity {

    private Spinner gender;
    private Spinner age;
    private Spinner feet;
    private Spinner inches;

    protected UserInfo userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        userInput = new UserInfo();

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

        //Weight spinner


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

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }
}
