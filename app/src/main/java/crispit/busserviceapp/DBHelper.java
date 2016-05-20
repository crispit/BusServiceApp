package crispit.busserviceapp;

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

    //Ofˆr‰nderliga str‰ngar som anv‰nds till skapande av databasen
    public static final String TABLE_NAME = "ErrorReport";
    public static final String COLUMN_NAME_ENTRYID = "ErrorID";
    public static final String COLUMN_NAME_SYMPTOM = "Symptom";
    public static final String COLUMN_NAME_BUSID = "BusID";
    public static final String COLUMN_NAME_DATE = "Date";
    public static final String COLUMN_NAME_COMMENT = "Comment";
    public static final String COLUMN_NAME_GRADE = "Grade";
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

    //Str‰ng fˆr att droppa vÂr databas i SQL
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" +
            TABLE_NAME;

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Database.db";

    //konstruktor
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Metod som skapar en databas
     * @param db
     */
    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Metod fˆr upgradering av databasen
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Metod fˆr ins‰ttning av felrapporter i databasen
     * @param errorID
     * @param symptom
     * @param busID
     * @param date
     * @param comment
     * @param grade
     * @return
     */
    public boolean insertErrorReport(String errorID, String symptom, String comment, String busID, String date, int grade, String status,
                                     String accelerator_Pedal_Position, String ambient_Temperature, String at_Stop, String cooling_Air_Conditioning,
                                     String driver_Cabin_Temperature, String fms_Sw_Version_Supported, String gps, String gps2,
                                     String gps_nmea, String journey_Info, String mobile_Network_Cell_Info, String mobile_Network_Signal_Strength,
                                     String next_Stop, String offroute, String online_Users, String opendoor, String position_Of_Doors,
                                     String pram_Request, String ramp_Wheel_Chair_Lift, String status_2_Of_Doors, String stop_Pressed, String stop_Request,
                                     String total_Vehicle_Distance, String turn_Signals, String wlan_Connectivity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ErrorID", errorID);
        contentValues.put("Symptom", symptom);
        contentValues.put("Comment", comment);
        contentValues.put("BusID", busID);
        contentValues.put("Date", date);
        contentValues.put("Grade", grade);
        contentValues.put("Status", status);
        contentValues.put("Accelerator_Pedal_Position", accelerator_Pedal_Position);
        contentValues.put("Ambient_Temperature", ambient_Temperature);
        contentValues.put("At_Stop", at_Stop);
        contentValues.put("Cooling_Air_Conditioning", cooling_Air_Conditioning);
        contentValues.put("Driver_Cabin_Temperature", driver_Cabin_Temperature);
        contentValues.put("Fms_Sw_Version_Supported", fms_Sw_Version_Supported);
        contentValues.put("GPS", gps);
        contentValues.put("GPS2", gps2);
        contentValues.put("GPS_NMEA", gps_nmea);
        contentValues.put("Journey_Info", journey_Info);
        contentValues.put("Mobile_Network_Cell_Info", mobile_Network_Cell_Info);
        contentValues.put("Mobile_Network_Signal_Strength", mobile_Network_Signal_Strength);
        contentValues.put("Next_Stop", next_Stop);
        contentValues.put("Offroute", offroute);
        contentValues.put("Online_Users", online_Users);
        contentValues.put("Opendoor", opendoor);
        contentValues.put("Position_Of_Doors", position_Of_Doors);
        contentValues.put("Pram_Request", pram_Request);
        contentValues.put("Ramp_Wheel_Chair_Lift", ramp_Wheel_Chair_Lift);
        contentValues.put("Status_2_Of_Doors", status_2_Of_Doors);
        contentValues.put("Stop_Pressed", stop_Pressed);
        contentValues.put("Stop_Request", stop_Request);
        contentValues.put("Total_Vehicle_Distance", total_Vehicle_Distance);
        contentValues.put("Turn_Signals", turn_Signals);
        contentValues.put("Wlan_Connectivity", wlan_Connectivity);

        db.insert("ErrorReport", null, contentValues);
        return true;
    }

    /**
     * Metod fˆr att hitta en felrapport med ett specifikt errorId
     * @param id
     * @return en cursor
     */
    public Cursor getData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from ErrorReport where ErrorID="+id+"", null );
        return res;
    }

    /**
     * Metod som returnerar en arraylist med alla rapporter i databasen
     */
    public ArrayList<String> getAllReports() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from ErrorReport", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_ENTRYID)));
            res.moveToNext();
        }
        return array_list;
    }

    /**
     * Metod fˆr att hitta alla felraporten fˆr en specifik buss
     * @param busID id fˆr att identifiera en specifik buss
     */
    public ArrayList<ErrorReport> getBusReports(String busID) {
        ArrayList<ErrorReport> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from ErrorReport WHERE BusID = ?", new String[]{busID});
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
     * Metod fˆr att radera en felrapport med ett specifikt errorId
     * @param errorId unique ID for the error report which to update
     * @param grade the updated urgency of the error
     * @param symptom the updated symptom of the error
     * @param symptom the updated comment of the error
     */
    public void updateErrorReport(String errorId, String grade, String symptom, String comment, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Grade", grade);
        contentValues.put("Symptom", symptom);
        contentValues.put("Comment", comment);
        contentValues.put("Status", status);

        db.update("ErrorReport", contentValues, "errorId = ? ", new String[]{errorId});

    }

    /**
     * Metod fˆr att uppdatera status i en felrapport med ett specifikt errorId
     * @param errorId unique ID for the error report which to update
     */
    public void updateStatus(String errorId, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status", status);

        db.update("ErrorReport", contentValues, "errorId = ? ", new String[]{errorId});

    }


    /**
     * Metod fˆr att returnera alla unika bussar (med fel)
     * @return arraylist med bussar som har felrapporter
     */
    public ArrayList<String> getAllBuses() {
        ArrayList<String> array_list = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select DISTINCT BusID from ErrorReport", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_BUSID)));
            res.moveToNext();
        }
        return array_list;
    }

    public String getNewErrorId(){

        int nextId=0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select ErrorID from ErrorReport", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            int currentId = Integer.parseInt(res.getString(res.getColumnIndex(COLUMN_NAME_ENTRYID)));
            if(currentId>nextId)
                nextId=currentId;
            res.moveToNext();
        }

        return Integer.toString(nextId+1);
    }

    public ArrayList<ErrorReport> getAllReportsDetailed() {
        ArrayList<ErrorReport> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from ErrorReport"/* where NOT Status = 'fixed'"*/, null);
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
