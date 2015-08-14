package com.example.caroline.sojung2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/** shows the sensor data of all the sensors
 * can send the data to the S3 bucket
 *
 */
public class ViewAll extends Activity implements SensorEventListener {

    private SensorManager sensorManager;

    private Sensor accel;
    private TextView accelDataText;

    private Sensor gyro;
    private TextView gyroDataText;

    private Sensor mag;
    private TextView magDataText;

    private Sensor step;
    private TextView stepText;

    private ToggleButton dataRecordButton;
    private SensorLoggerFile loggerFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        //allows you to find the sensor you would like and the state it is in
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //finding the toggle button and text in order to manipulate later
        accelDataText = (TextView)findViewById(R.id.accelData);
        gyroDataText = (TextView)findViewById(R.id.gyroData);
        magDataText = (TextView)findViewById(R.id.magData);
        stepText = (TextView)findViewById(R.id.stepData);
        dataRecordButton = (ToggleButton)findViewById(R.id.recordData);

        //getting the user input information from the first activity
        UserInfo user = getIntent().getParcelableExtra("user");

        //allows logging of data
        loggerFile = new SensorLoggerFile(this, user);

        //saving the sensors to local variables
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            accelDataText.setText("Accelerometer is not present!");
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        } else {
            gyroDataText.setText("Gyroscope is not present!");
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            mag = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        } else {
            magDataText.setText("Magnetic Field Sensor is not present!");
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            step = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        } else {
            stepText.setText("Step counter is not present!");
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_all, menu);
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

        //interact with anything that wants to get sensor data
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            getAccelerometer(event);


        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
            getGyroscope(event);


        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            getMag(event);


        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER)
            getStep(event);



    }


    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;

        // Movement
        String x = "X coordinate: " + Float.toString(values[0]) + "\n";
        String y = "Y coordinate: " + Float.toString(values[1]) + "\n";
        String z = "Z coordinate: " + Float.toString(values[2]) + "\n";

        String coordinates = x + y + z;

        accelDataText.setText(coordinates);


        if (loggerFile.getmLogger()) loggerFile.tryLogging(event);

        //defining what happens when the toggleButton is enabled and disabled
        dataRecordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    //enable logging
                    loggerFile.enableLogging();

                } else {

                    //stop logging
                    loggerFile.disableLogging();


                }

            }
        });

    }

    private void getGyroscope(SensorEvent event) {
        float[] values = event.values;

        // Movement
        String x = "X coordinate: " + Float.toString(values[0]) + "\n";
        String y = "Y coordinate: " + Float.toString(values[1]) + "\n";
        String z = "Z coordinate: " + Float.toString(values[2]) + "\n";

        String coordinates = x + y + z;

        gyroDataText.setText(coordinates);

        if (loggerFile.getmLogger()) loggerFile.tryLogging(event);

        //defining what happens when the toggleButton is enabled and disabled
        dataRecordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    loggerFile.enableLogging();

                } else {
                    loggerFile.disableLogging();


                }

            }
        });


    }

    private void getMag(SensorEvent event) {
        float[] values = event.values;

        // Movement
        String x = "X coordinate: " + Float.toString(values[0]) + "\n";
        String y = "Y coordinate: " + Float.toString(values[1]) + "\n";
        String z = "Z coordinate: " + Float.toString(values[2]) + "\n";

        String coordinates = x + y + z;

        magDataText.setText(coordinates);

        if (loggerFile.getmLogger()) loggerFile.tryLogging(event);

        //defining what happens when the toggleButton is enabled and disabled
        dataRecordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    loggerFile.enableLogging();

                } else {
                    loggerFile.disableLogging();


                }

            }
        });


    }

    private void getStep(SensorEvent event) {
        float[] values = event.values;

        // Movement
        String steps = "Number of Steps: " + Float.toString(values[0]) + "\n";

        String numSteps = "Step Counter Data:\n" + steps;

        stepText.setText(numSteps);

        if (loggerFile.getmLogger()) loggerFile.tryLogging(event);

        //defining what happens when the toggleButton is enabled and disabled
        dataRecordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    loggerFile.enableLogging();

                } else {
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
        sensorManager.registerListener(this, accel,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyro,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
