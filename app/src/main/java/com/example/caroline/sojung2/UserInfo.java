package com.example.caroline.sojung2;

/**
 * edited by Caroline on 7/29/15.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by amiller on 6/3/2015.
 * For use with Pni logging library
 */
public class UserInfo implements Parcelable {

    private String Weight;
    private String Gender;
    private String Activity;
    private String Name;
    private String Orientation;
    private String Age;
    private String HeightFt;
    private String HeightIn;
    private String LogFiles;
    private String LastLogFile;
    private String Email;

    private ArrayList<String> extraItems = null;

    public static final String GENDER_MALE_STRING_VALUE = "Male";
    public static final String GENDER_FEMALE_STRING_VALUE = "Female";

    @Override
    public int describeContents() {
        return 0;
    }

    //constructor
    public UserInfo() {
        setActivity("none");
        setAge("none");
        setGender("none");
        setHeightFt("none");
        setName("none");
        setOrientation("none");
        setWeight("none");
        setHeightFt("none");
        setHeightIn("none");
        setLogFiles("Downloads:/");
        setEmail("");
    }

    /**
     * Retrieving UserInfo data from Parcel object
     * This constructor is invoked by the method
     * createFromParcel(Parcel source) of the object CREATOR
     */
    private UserInfo(Parcel in) {
        this.Weight = in.readString();
        this.Gender = in.readString();
        this.Activity = in.readString();
        this.Name = in.readString();
        this.Orientation = in.readString();
        this.Age = in.readString();
        this.HeightFt = in.readString();
        this.HeightIn = in.readString();
        this.LogFiles = in.readString();
        this.LastLogFile = in.readString();
        this.Email = in.readString();

    }

    public UserInfo(UserInfo UI) {

        this.setActivity(UI.getActivity());
        this.setName(UI.getName());
        this.setOrientation(UI.getOrientation());
        this.setAge(UI.getAge());
        this.setHeightFt(UI.getHeightFt());
        this.setWeight(UI.getWeight());
        this.setGender(UI.getGender());
        this.setHeightIn(UI.getHeightIn());
        this.setLastLogFile(UI.getLastLogFile());
        this.setLogFiles(UI.getLogFiles());
        //this.setEmail(UI.getEmail());
        if (extraItems != null) {
            for (String s : UI.getExtras()) {
                this.addExtra(s);
            }
        }
    }

    /**
     * storing UserInfo data to Parcel object
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Gender);
        dest.writeString(Age);
        dest.writeString(Weight);
        dest.writeString(Activity);
        dest.writeString(Orientation);
        dest.writeString(HeightFt);
        dest.writeString(HeightIn);
        dest.writeString(LogFiles);
        dest.writeString(LastLogFile);
        dest.writeString(Email);
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {

        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };


    //Height (inches) getter and setter
    public String getHeightIn() {
        return HeightIn;
    }

    public void setHeightIn(String heightIn) {
        HeightIn = heightIn;
    }

    //height (feet) getter and setter
    public String getHeightFt() {
        return HeightFt;
    }

    public void setHeightFt(String heightFt) {
        HeightFt = heightFt;
    }

    //Activity getter and setter
    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    //name getter and setter
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    //orientation getter and setter
    public String getOrientation() {
        return Orientation;
    }

    public void setOrientation(String orientation) {
        Orientation = orientation;
    }

    //age getter and setter
    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    //weight getter and setter
    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    //gender getter and setter
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    //logFile getter and setter
    public String getLogFiles() {
        return LogFiles;
    }

    public void setLogFiles(String logFiles) {
        LogFiles = logFiles;
    }

    public String getLastLogFile() {
        return LastLogFile;
    }

    public void setLastLogFile(String lastLogFile) {

        if(!LogFiles.contains(lastLogFile))
        {
            appendLogFiles(lastLogFile);
        }

        LastLogFile = lastLogFile;
    }

    //email getter and setter
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String appendLogFiles(String newFile)
    {
        if(LogFiles.isEmpty())
        {
            LogFiles=newFile+",";
        }else{
            LogFiles += newFile+",";
        }
        return LogFiles;
    }


    public ArrayList<String> toArrayList(){
        ArrayList<String> items = new ArrayList<String>();

        items.add ( getActivity());
        items.add ( getName());
        items.add ( getOrientation());
        items.add ( getAge());
        items.add ( getHeightFt());
        items.add ( getWeight());
        items.add ( getGender());
        items.add ( getHeightIn());
        items.add ( getLastLogFile());
        items.add ( getLogFiles());
        //items.add ( getEmail());
        if(extraItems!=null)
        {

            items.addAll(extraItems);
        }

        return items;
    }

    public void addExtra(String newField) throws NullPointerException
    {

        if (extraItems == null) {
            extraItems = new ArrayList<String>();
        }

        extraItems.add(newField);
    }

    public ArrayList<String> getExtras()
    {
        if(extraItems==null)
        {
            return null;
        }
        return new ArrayList<>(extraItems);
    }

    public int size()
    {
        return toArrayList().size();
    }

    public boolean copyItems(ArrayList<String> input){

        if(input == null)
        {
            return false;
        }
        input.add ( getActivity());
        input.add ( getName());
        input.add ( getOrientation());
        input.add ( getAge());
        input.add ( getHeightFt());
        input.add ( getWeight());
        input.add ( getGender());
        input.add ( getHeightIn());
        input.add ( getLastLogFile());
        input.add ( getLogFiles());
        //input.add ( getEmail());
        if(extraItems!=null)
        {

            input.addAll(extraItems);
        }

        return true;
    }
}
