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

    //<editor-fold desc="Logging Functions">
    // data logger

    /**
    private boolean mLoggingEnabled =false;
    private boolean mAlgoithmLastPointLogged =false;

    private String latestFilename;
    /**
     * Prefix for data log files

    protected static final String DATA_LOG_PREFIX = "tt_";

    /**
     * Extension for data log files

    protected static final String DATA_LOG_EXT = ".csv";

    /**
     * Delimiter used to separate values within the data log

    protected static final String DATA_LOG_DELIMITER = ",";
    /**
     * Data logger instance

    protected static final SensorEventLogger mDataLogger = new SensorEventLogger(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            DATA_LOG_DELIMITER);

    protected UserInfo mUserProfile;


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


        //check to see what sensors are enabled after declaring sensorManager
        //keep local copy of sensor trying to use. use getDefaultSensor method that will retrieve a copy of sensor class to access members
        //registerListener to get data from sensors

    }


    /**
     * Enables the data logger, opens a log file

    protected void enableLogging() {

        if (mDataLogger != null) {

            //if the mDataLogger and mLoggingEnabled is true, then disable the logging
            if(mLoggingEnabled) {
                disableLogging();
            }
            try {

                //create the mUserProfile if not already made
                SimpleDateFormat dateF = new SimpleDateFormat("MMdd-HHmm-ss");
                Date date = new Date();
                final String filename = DATA_LOG_PREFIX + String.valueOf(dateF.format(date)) + DATA_LOG_EXT;
                latestFilename = filename;
                mDataLogger.open(filename, false);
                String activityString;

                activityString = "Pedometer Calorie and Distance Counter";
                String header = "Header1234";

                //set all the strings to null
                String userGender = null;
                String userHeight = null;
                String userWeight = null;
                String userAge = null;
                String userActivity = null;
                String userPosition = null;
                String userTruthStepCount = null;

                if(mUserProfile != null)
                {

                    //if the user isn't null then get the input values
                    try {
                        int height =
                                Integer.parseInt(mUserProfile.getHeightFt())*12
                                        + Integer.parseInt(mUserProfile.getHeightIn());

                        userGender = mUserProfile.getGender();
                        userHeight = String.format("%d",height);
                        userWeight = mUserProfile.getWeight();
                        userAge = mUserProfile.getAge();
                        userActivity = mUserProfile.getActivity();
                        userPosition = mUserProfile.getOrientation();
                        userTruthStepCount = "Check Last Entry";
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                header += "; Gender = "+ userGender +"\n";
                header += "; Height = "+ userHeight +"\n";
                header += "; Weight = "+ userWeight +"\n";
                header += "; Age = "+ userAge +"\n";
                header += "; Activity = "+userActivity+"\n";
                header += "; Position = "+userPosition+"\n";
                header += "; TruthStepCount = "+ userTruthStepCount +"\n";
                header += "; currentActivity = " + activityString + "\n";
                header += "; System.currentTimeMillis, event.timestamp, type, accuracy, values \n";

                mDataLogger.setHeader(header);

                // set enabled flag
                mLoggingEnabled = true;

                  Toast.makeText(this,"Logging Enabled",Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Toast.makeText(this, "Error enabling logging " + e.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Disables the data logger, closes the file and notifies the system that a
     * new file is available
     *
     * put the amazon web service stuff in this function after the file is written

    protected void disableLogging() {
        File f1 = null;

        if (mLoggingEnabled && (mDataLogger != null)) {
            try {
                f1 = mDataLogger.getFile();
                // notify media scanner
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(mDataLogger.getFile())));

                // close logger
                mDataLogger.close();

                Toast.makeText(this, "Logging Disabled", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Toast.makeText(this, "Error disabling logging " + e.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        }

        final File f2 = f1;

        String dir =
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS
                ).getAbsolutePath();
        MediaScannerConnection.scanFile(this, new String[]{dir + "/" + latestFilename}, null, null);


        Toast.makeText(this, "File written and saved bitch", Toast.LENGTH_SHORT).show();

        //figure out what the file that is saved to the phone is called
        //try to upload the file just saved to the phone

        // set disabled flag
        mLoggingEnabled = false;
    }

    //logging the sensor data outputs
    public void tryLogging(SensorEvent event) {
        if (mLoggingEnabled) {
            try {
                mDataLogger.log(event);
            } catch (IOException e) {
                Toast.makeText(
                        this, "Error logging "+ e.getMessage(),Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }


    public void tryLogging(Location event) {
        if (mLoggingEnabled) {
            try {
                mDataLogger.log(event);
            } catch (IOException e) {
                Toast.makeText(
                        this, "Error logging "+ e.getMessage(),Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }

    //logs the timestamp, step count, calories, and distance of the data
    private void tryLogging(long timestamp, float stepCounts, float calories, float distance) {
        if(mLoggingEnabled)         //log if available
        {
            float[] vals = {
                    stepCounts,
                    calories,
                    distance
            };

            try {
                mDataLogger.log(timestamp,SensorEventLogger.SENSOR_TYPE_ALGORITHM,0,vals);
            } catch (IOException e) {
                Toast.makeText(this,
                        "Error Logging: " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void tryLogging(String s, int truth) {
        if(mLoggingEnabled)         //log if available
        {
            try {
                mDataLogger.log(0L,-1,truth,new float[0]);
            } catch (IOException e) {
                Toast.makeText(this,
                        "Error Logging: " + s +" \r\n"+ e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

*/


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

        /**if (mLoggingEnabled) tryLogging(event);

        dataRecordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    enableLogging();

                    Context context = getApplicationContext();
                    CharSequence text = "I'm logging data!!!! Logging data";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    disableLogging();
                }

            }
        });

        //TransferObserver observer = transferUtility.upload("chsu2","accelData", "accelData.txt" );

*/
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

        // register this class as a listener for the orientation and
        // accelerometer sensors
        //sensorManager.registerListener(this, activeAccel, SensorManager.SENSOR_DELAY_NORMAL);
        /*Context context = getApplicationContext();
        CharSequence text = "I'M BACK ON";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);

        // writer.close();
        //sensorManager.unregisterListener(this);

        /*Context context = getApplicationContext();
        CharSequence text = "ONPAUSE";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        //disableLogging();*/
    }


}
