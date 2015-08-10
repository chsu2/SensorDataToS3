package com.example.caroline.sojung2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.Intent;


/**Displays the data from the accelerometer and allows
 * the user to record the data to a file. The file then
 * saves locally in the "Downloads" folder of the phone
 * and also sends a copy to an S3 bucket.
 */
public class Accelerometer2 extends Activity implements SensorEventListener {



    private Sensor activeAccel;
    private SensorManager sensorManager;
    private TextView dataText;
    private ToggleButton dataRecordButton;
    private SensorLoggerFile loggerFile;
    //private Intent intent;


    /** Called when the activity i first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer2);
        dataText = (TextView)findViewById(R.id.textView);
        dataRecordButton = (ToggleButton)findViewById(R.id.recordData);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //saving the accel to a local variable
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            activeAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            dataText.setText("Accelerometer" + " is not present!");
            findViewById(R.id.recordData).setVisibility(View.GONE);

        }

        UserInfo user = getIntent().getParcelableExtra("user");
        loggerFile = new SensorLoggerFile(this, user);
        //intent = getIntent();





        //check to see what sensors are enabled after declaring sensorManager
        //keep local copy of sensor trying to use. use getDefaultSensor method that will retrieve a copy of sensor class to access members
        //registerListener to get data from sensors

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_all, menu);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //interact with anything that wants to get sensor data
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        } else {
            dataText.setText("No Accelerometer present!");
            findViewById(R.id.recordData).setVisibility(View.GONE);
        }

    }

    private void getAccelerometer(final SensorEvent event) {
        float[] values = event.values;

        // Movement
        String x = "X coordinate: " + Float.toString(values[0]) + "\n";
        String y = "Y coordinate: " + Float.toString(values[1]) + "\n";
        String z = "Z coordinate: " + Float.toString(values[2]) + "\n";

        String coordinates = x + y + z;

        dataText.setText(coordinates);


        //can potentially move this line into the button clicking event
        if (loggerFile.getmLogger()) loggerFile.tryLogging(event);

        dataRecordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    loggerFile.enableLogging();

                    Context context = getApplicationContext();
                    CharSequence text = "I'm logging data!!!! Logging data";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
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

        sensorManager.registerListener(this, activeAccel, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);

    }


}
