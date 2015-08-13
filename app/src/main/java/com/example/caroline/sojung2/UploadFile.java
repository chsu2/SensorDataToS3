package com.example.caroline.sojung2;

import android.content.Context;
import android.widget.Toast;
import java.io.File;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferType;


/**
 * Created by Caroline on 8/4/15.
 */

//uploads the file to the s3 bucket specified
public class UploadFile {

    private TransferUtility transferUtility;
    private Context context;

    public UploadFile( Context context){

        this.context = context;

        //initialize transferUtility -- needed to transfer the file to the cloud -- no longer user transferManager
        transferUtility = Util.getTransferUtility(context);

    }

    /*
        * Begins to upload the file specified by the file path.
        */
    public void beginUpload(String filePath) {
        if (filePath == null) {
            Toast.makeText(context, "Could not find the filepath of the selected file",
                    Toast.LENGTH_LONG).show();
            return;
        }
        File file = new File(filePath);
        TransferObserver observer = transferUtility.upload(Constants.BUCKET_NAME, file.getName(),
                file);

    }
}
