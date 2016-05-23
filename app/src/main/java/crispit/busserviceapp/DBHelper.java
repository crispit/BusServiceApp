package crispit.busserviceapp;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by fredrikhansson on 4/18/16.
 */
public class DBHelper extends SQLiteOpenHelper{

    //Unchangeable strings which is used when the database is created
    public static final String TABLE_NAME = "ErrorReport";
    public static final String COLUMN_NAME_ENTRYID = "FelrapportsID";
    public static final String COLUMN_NAME_SYMPTOM = "Felkategori";
    public static final String COLUMN_NAME_BUSID = "BussID";
    public static final String COLUMN_NAME_DATE = "Datum";
    public static final String COLUMN_NAME_COMMENT = "Kommentar";
    public static final String COLUMN_NAME_GRADE = "Gradering";
    public static final String COLUMN_NAME_STATUS = "Status";

    //Busspecifik data
    public static final String COLUMN_NAME_Accelerator_Pedal_Position = "Accelerator_Pedal_Position";
    public static final String COLUMN_NAME_Ambient_Temperature = "Ambient_Temperature";
    public static final String COLUMN_NAME_At_Stop = "At_Stop";
    public static final String COLUMN_NAME_Cooling_Air_Conditioning = "Cooling_Air_Conditioning";
    public static final String COLUMN_NAME_Driver_Cabin_Temperature = "Driver_Cabin_Temperature";
    public static final String COLUMN_NAME_Fms_Sw_Version_Supported = "Fms_Sw_Version_Supported";
    public static final String COLUMN_NAME_GPS = "GPS";
    public static final String COLUMN_NAME_GPS2 = "GPS2";
    public static final String COLUMN_NAME_GPS_NMEA = "GPS_NMEA";
    public static final String COLUMN_NAME_Journey_Info = "Journey_Info";
    public static final String COLUMN_NAME_Mobile_Network_Cell_Info = "Mobile_Network_Cell_Info";
    public static final String COLUMN_NAME_Mobile_Network_Signal_Strength = "Mobile_Network_Signal_Strength";
    public static final String COLUMN_NAME_Next_Stop = "Next_Stop";
    public static final String COLUMN_NAME_Offroute = "Offroute";
    public static final String COLUMN_NAME_Online_Users = "Online_Users";
    public static final String COLUMN_NAME_Opendoor = "Opendoor";
    public static final String COLUMN_NAME_Position_Of_Doors = "Position_Of_Doors";
    public static final String COLUMN_NAME_Pram_Request = "Pram_Request";
    public static final String COLUMN_NAME_Ramp_Wheel_Chair_Lift = "Ramp_Wheel_Chair_Lift";
    public static final String COLUMN_NAME_Status_2_Of_Doors = "Status_2_Of_Doors";
    public static final String COLUMN_NAME_Stop_Pressed = "Stop_Pressed";
    public static final String COLUMN_NAME_Stop_Request = "Stop_Request";
    public static final String COLUMN_NAME_Total_Vehicle_Distance = "Total_Vehicle_Distance";
    public static final String COLUMN_NAME_Turn_Signals = "Turn_Signals";
    public static final String COLUMN_NAME_Wlan_Connectivity = "Wlan_Connectivity";

    //Helpstrings for queries
    private static final String TEXT_TYPE = " TEXT ";
    private static final String COMMA_SEP = ",";
    private static final String NUMBER_TYPE = " INTEGER";

    //String to create table in DB
    private static final String SQL_CREATE_ENTRIES = " CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_NAME_ENTRYID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME_SYMPTOM + TEXT_TYPE +  COMMA_SEP +
            COLUMN_NAME_COMMENT + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_BUSID + TEXT_TYPE  + COMMA_SEP +
            COLUMN_NAME_DATE + TEXT_TYPE  + COMMA_SEP +
            COLUMN_NAME_GRADE + NUMBER_TYPE + COMMA_SEP +
            COLUMN_NAME_STATUS + NUMBER_TYPE + COMMA_SEP +
            COLUMN_NAME_Accelerator_Pedal_Position + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Ambient_Temperature + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_At_Stop + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Cooling_Air_Conditioning + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Driver_Cabin_Temperature + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Fms_Sw_Version_Supported + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_GPS + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_GPS2 + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_GPS_NMEA + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Journey_Info + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Mobile_Network_Cell_Info + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Mobile_Network_Signal_Strength + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Next_Stop + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Offroute + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Online_Users + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Opendoor + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Position_Of_Doors + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Pram_Request + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Ramp_Wheel_Chair_Lift + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Status_2_Of_Doors + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Stop_Pressed + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Stop_Request + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Total_Vehicle_Distance + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Turn_Signals + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_Wlan_Connectivity + TEXT_TYPE + ")"  ;

    //String to drop database
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Strings with database info
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Database.db";

    //constructor
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method for creating database
     * @param db the input parameter
     */
    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Method for upgrading database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Method for finding an error report for a specific error ID
     * @param id the error ID
     * @return a cursor with the result
     */
    public Cursor getData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_NAME_ENTRYID + " =" + id, null);
        return res;
    }

    /**
     * Metod som returnerar en arraylist med alla rapporter i databasen
     */
    public ArrayList<String> getAllReports() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_ENTRYID)));
            res.moveToNext();
        }
        return array_list;
    }

    /**
     * Method for finding all error reports for a specific bus
     * @param busID id to identify a specific bus
     * @return an arraylist with all the reports for the specific bus
     */
    public ArrayList<ErrorReport> getSolvedBusReports(String busID) {
        ArrayList<ErrorReport> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COLUMN_NAME_BUSID+" = ?" + " AND Status = 'Löst'", new String[]{busID});
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            ErrorReport er = new ErrorReport(res.getString(res.getColumnIndex(COLUMN_NAME_ENTRYID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_SYMPTOM)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_COMMENT)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_BUSID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_DATE)),
                    res.getInt(res.getColumnIndex(COLUMN_NAME_GRADE)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_STATUS)));
            array_list.add(er);
            res.moveToNext();
        }
        return array_list;
    }

    /**
     * Method for udpating the status in an error report with a specific error id
     * @param errorId unique ID for the error report which to update
     */
    public void updateStatus(String errorId, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_STATUS, status);

        db.update(TABLE_NAME, contentValues, COLUMN_NAME_ENTRYID+" = ? ", new String[]{errorId});

    }

    /**
     * Method for udpating the status in an error report with a specific error id
     * @param errorId unique ID for the error report which to update
     */
    public void updateGrade(String errorId, String grade){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_GRADE, grade);

        db.update(TABLE_NAME, contentValues, COLUMN_NAME_ENTRYID+" = ? ", new String[]{errorId});

    }


    /**
     * Method for getting all the unique bus id's (that has had an error)
     * @return an arraylist with buses that has errer reports
     */
    public ArrayList<String> getAllBuses() {
        ArrayList<String> array_list = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select DISTINCT "+COLUMN_NAME_BUSID+" from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_BUSID)));
            res.moveToNext();
        }
        return array_list;
    }

    //Metod för att visa lista med felrapporter
    public ArrayList<ErrorReport> getAllNonFixedReportsDetailed() {
        ArrayList<ErrorReport> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where NOT "+COLUMN_NAME_STATUS+" = 'Löst'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            ErrorReport er = new ErrorReport(res.getString(res.getColumnIndex(COLUMN_NAME_ENTRYID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_SYMPTOM)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_COMMENT)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_BUSID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_DATE)),
                    res.getInt(res.getColumnIndex(COLUMN_NAME_GRADE)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_STATUS)));
            array_list.add(er);
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<ErrorReport> getAllFixedReportsDetailed() {
        ArrayList<ErrorReport> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COLUMN_NAME_STATUS+" = 'Löst'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            ErrorReport er = new ErrorReport(res.getString(res.getColumnIndex(COLUMN_NAME_ENTRYID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_SYMPTOM)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_COMMENT)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_BUSID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_DATE)),
                    res.getInt(res.getColumnIndex(COLUMN_NAME_GRADE)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_STATUS)));
            array_list.add(er);
            res.moveToNext();
        }
        return array_list;
    }

    /**
     * Method for finding all Unsolved error reports for a specific bus
     * @param busID id to identify a specific bus
     * @return an arraylist with all the reports for the specific bus
     */
    public ArrayList<ErrorReport> getUnsolvedBusReports(String busID) {
        ArrayList<ErrorReport> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COLUMN_NAME_BUSID+" = ?" + " AND NOT "
                + COLUMN_NAME_STATUS + " = 'Löst' ", new String[]{busID});
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            ErrorReport er = new ErrorReport(res.getString(res.getColumnIndex(COLUMN_NAME_ENTRYID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_SYMPTOM)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_COMMENT)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_BUSID)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_DATE)),
                    res.getInt(res.getColumnIndex(COLUMN_NAME_GRADE)),
                    res.getString(res.getColumnIndex(COLUMN_NAME_STATUS)));
            array_list.add(er);
            res.moveToNext();
        }
        return array_list;
    }

}