package com.example.caroline.sojung2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class StepCounter extends Activity implements SensorEventListener {

    private Sensor activeStepCounter;
    private SensorManager sensorManager;
    private TextView dataText;
    private ToggleButton dataRecordButton;
    private SensorLoggerFile loggerFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        //finding the toggle button and text in order to manipulate later
        dataText = (TextView)findViewById(R.id.textView);
        dataRecordButton = (ToggleButton)findViewById(R.id.recordData);

        //allows you to find the sensor you would like and the state it is in
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //saving the stepCounter to a local variable
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            activeStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        } else {
            dataText.setText("Step counter is not present!");
            findViewById(R.id.recordData).setVisibility(View.GONE);
        }

        //getting the user input information from the first activity
        UserInfo user = getIntent().getParcelableExtra("user");

        //allows logging of data
        loggerFile = new SensorLoggerFile(this, user);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_step_counter, menu);
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

    @Override
    public void onSensorChanged(SensorEvent event) {

        //retrieves sensor data if the sensor is present
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            getStep(event);
        } else {
        dataText.setText("Step Counter sensor is not present!");
            findViewById(R.id.recordData).setVisibility(View.GONE);
        }

    }

    private void getStep(SensorEvent event) {
        float[] values = event.values;

        // Movement
        String steps = "Number of Steps: " + Float.toString(values[0]) + "\n";

        String numSteps = "Step Counter Data:\n" + steps;

        dataText.setText(numSteps);

        if (loggerFile.getmLogger()) loggerFile.tryLogging(event);

        //defining what happens when the toggleButton is enabled and disabled
        dataRecordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    //start logging
                    loggerFile.enableLogging();

                } else {

                    //stop logging
                    loggerFile.disableLogging();


                }

            }
        });

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this, activeStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
