package com.example.caroline.sojung2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.amazonaws.*;
import android.os.Environment;


import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferType;

import com.amazonaws.services.s3.internal.Constants;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.widget.ToggleButton;



public class Accelerometer2 extends Activity implements SensorEventListener {



    private Sensor activeAccel;
    private SensorManager sensorManager;
    private TextView dataText;
    private ToggleButton dataRecordButton;
    private SensorLoggerFile loggerFile;

    //<editor-fold desc="Logging Functions">
    // data logger

    /**
    / The SimpleAdapter adapts the data about transfers to rows in the UI
    private SimpleAdapter simpleAdapter;

    // A List of all transfers
    private List<TransferObserver> observers;

     */

    /**
     * This map is used to provide data to the SimpleAdapter above. See the
     * fillMap() function for how it relates observers to rows in the displayed
     * activity.

    private ArrayList<HashMap<String, Object>> transferRecordMaps;


*/
    /** Called when the activity i first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer2);
        dataText = (TextView)findViewById(R.id.textView);
        dataRecordButton = (ToggleButton)findViewById(R.id.recordData);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //saving the accel to a local variable
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
            activeAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        loggerFile = new SensorLoggerFile(this);

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
            findViewById(R.id.collectData).setVisibility(View.GONE);
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
                    uploadActivity();


                }

            }
        });
    }

    public void uploadActivity(){

        Intent intent = new Intent(this, UploadActivity.class);

        startActivity(intent);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
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
