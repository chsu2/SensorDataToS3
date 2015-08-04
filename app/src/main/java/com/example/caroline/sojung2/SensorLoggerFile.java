package com.example.caroline.sojung2;

import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

/**
 * Created by Caroline on 8/4/15.
 */
public class SensorLoggerFile {

    //<editor-fold desc="Logging Functions">
    // data logger
    private boolean mLoggingEnabled;
    private boolean mAlgoithmLastPointLogged;

    private String latestFilename;
    /**
     * Prefix for data log files
     */
    protected static final String DATA_LOG_PREFIX = "tt_";

    /**
     * Extension for data log files
     */
    protected static final String DATA_LOG_EXT = ".csv";

    /**
     * Delimiter used to separate values within the data log
     */
    protected static final String DATA_LOG_DELIMITER = ",";
    /**
     * Data logger instance
     */
    protected static final SensorEventLogger mDataLogger = new SensorEventLogger(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            DATA_LOG_DELIMITER);

    protected UserInfo mUserProfile;

    public SensorLoggerFile(){

        //assign values to the booleans
        mLoggingEnabled = false;
        mAlgoithmLastPointLogged = false;
    }
    /**
     * Enables the data logger, opens a log file
     */
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

                Toast.makeText(getApplicationContext(), "Logging Enabled", Toast.LENGTH_SHORT).show();

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
     */
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
                Toast.makeText(this, "Error Logging: " + s +" \r\n"+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
