package com.example.caroline.sojung2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//fix writing into file
public class MagField extends Activity implements SensorEventListener {

    private Sensor activeMag;
    private SensorManager sensorManager;
    private TextView dataText;
    private ToggleButton dataRecordButton;
    private SensorLoggerFile loggerFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mag_field);

        //finding the toggle button and text in order to manipulate later
        dataText = (TextView)findViewById(R.id.magData);
        dataRecordButton = (ToggleButton)findViewById(R.id.recordData);

        //allows you to find the sensor you would like and the state it is in
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //saving the magfield to a local variable
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            activeMag = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        } else {
            dataText.setText("Magnetic field sensor is not present!");
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
        getMenuInflater().inflate(R.menu.menu_mag_field, menu);
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
       if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            getMag(event);
        }else {
            dataText.setText("Magnetic Field sensor is not present!");
            findViewById(R.id.recordData).setVisibility(View.GONE);
        }

    }

    private void getMag(final SensorEvent event) {
        float[] values = event.values;

        // Movement
        String x = "X coordinate: " + Float.toString(values[0]) + "\n";
        String y = "Y coordinate: " + Float.toString(values[1]) + "\n";
        String z = "Z coordinate: " + Float.toString(values[2]) + "\n";

        String coordinates = x + y + z;

        dataText.setText(coordinates);

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
        sensorManager.registerListener(this, activeMag, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
