package com.mybaby.android_final_project.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mybaby.android_final_project.model.BloodType;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class PediatricControlDatabaseHelper extends SQLiteOpenHelper
{
    private final static String CREATE_CONTROL_TABLE = "CREATE TABLE CONTROL (id_control INTEGER PRIMARY KEY AUTOINCREMENT, date_control TEXT,id_patient INTEGER, weight NUMERIC, height NUMERIC, head_circumference  NUMERIC, teeth_amount INTEGER, pediatrician TEXT, notes TEXT , mood TEXT,date_audit NUMERIC )";
    private final static String CREATE_PATIENT_TABLE = "CREATE TABLE PATIENT(id_patient INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL UNIQUE, birth_date TEXT,id INTEGER UNIQUE, genre TEXT, id_blood_type INTEGER, date_audit NUMERIC)";
    private final static String CREATE_BLOOD_TYPE_TABLE = "CREATE TABLE BLOOD_TYPE (id_blood_type INTEGER PRIMARY KEY AUTOINCREMENT, group_factor TEXT UNIQUE )";

    private final static String DATABASE_NAME = "PediatricControlDatabase";
    private final static String TABLE_DROP_STATEMENT = "DROP TABLE IF EXISTS ";

    private static final String PATIENT_TABLE = "PATIENT" ;
    private static final String BLOOD_TYPE_TABLE = "BLOOD_TYPE";
    private static final String CONTROL_TABLE = "CONTROL";

    private Context context;
    private static PediatricControlDatabaseHelper databaseInstance;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static PediatricControlDatabaseHelper getDatabaseInstance(Context context)
    {
    	if(databaseInstance == null)
    	{
    		databaseInstance = new PediatricControlDatabaseHelper(context.getApplicationContext());
    	}
    	return databaseInstance;
    }

    public PediatricControlDatabaseHelper(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.context = context;
    }

    private PediatricControlDatabaseHelper(Context context)
    {
    	super(context, DATABASE_NAME, null, 1);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) 
    {
        Log.d("Create Database: ", DATABASE_NAME);
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_BLOOD_TYPE_TABLE);
        db.execSQL(CREATE_CONTROL_TABLE);
        insertDefaultData(db);
    }


    //Database created. Used just for testing
    public void onInitializeDB()
    {

        Log.d("Create Database: ", DATABASE_NAME);
        this.getWritableDatabase().execSQL(CREATE_PATIENT_TABLE);
        this.getWritableDatabase().execSQL(CREATE_BLOOD_TYPE_TABLE);
        this.getWritableDatabase().execSQL(CREATE_CONTROL_TABLE);
        insertDefaultData(this.getWritableDatabase());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
    {
        db.execSQL(DATABASE_NAME);
    }    

    
	public long insertControl(String date_control,int id_patient, float weight , float height , float head_circumference  , int teeth_amount, String pediatrician , String notes, String mood)
    {	
    	ContentValues values = new ContentValues();
    	
    	values.put("date_control", date_control);
    	values.put("id_patient", id_patient);
		values.put("weight", weight);
		values.put("height", height);
		values.put("head_circumference", head_circumference );
		values.put("teeth_amount", teeth_amount);
		values.put("notes", notes);
		values.put("date_audit", new Date().getTime());
		values.put("pediatrician", pediatrician);
        values.put("mood", mood);

        long index = getWritableDatabase().insert(CONTROL_TABLE, null, values);
    	this.close();
        return index;
    }
    
	public long insertPatient(Patient patient)
	{
        Log.d("Insert into Table: ", PATIENT_TABLE);
        ContentValues values = new ContentValues();
        values.put("name", patient.getName());
        values.put("id_blood_type", patient.getIdBloodGroup());
        values.put("genre", patient.getGenre());
        values.put("id", patient.getid());
        values.put("birth_date", convertCalendarToString(patient.getBirthDate()));
        values.put("date_audit", new Date().getTime());

        long index = getWritableDatabase().insert(PATIENT_TABLE, null, values);
    	this.close();
        return index;
    }

    public int updatePatient(Patient patient) {
        Log.d("Update into Table: ", PATIENT_TABLE);

        ContentValues values = new ContentValues();
        values.put("name", patient.getName());
        values.put("id_blood_type", patient.getIdBloodGroup());
        values.put("genre", patient.getGenre());
        values.put("id", patient.getid());
        values.put("birth_date", convertCalendarToString(patient.getBirthDate()));

        int rowUpdated = getWritableDatabase().update(PATIENT_TABLE, values, "id_patient = " + patient.getIdPatient(), null);
        this.close();
        return rowUpdated;
    }

    public int deletePatient(int idPatient) {
        Log.d("Delete into Table: ", PATIENT_TABLE);

        int rowDeleted = getWritableDatabase().delete(PATIENT_TABLE, "id_patient = " + idPatient, null);
        return rowDeleted;
    }

    public boolean existAPatient() {

        String SELECT_QUERY = "SELECT id_patient FROM " + PATIENT_TABLE;

        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);

        boolean exist = (c != null && c.getCount()> 0);

        this.close();

        return exist;
    }

    public Patient getPatient(int id_patient) {
        String SELECT_QUERY = "SELECT pac.name, pac.genre, pac.id, pac.birth_date, pac.id_patient , pac.id_blood_type "
                +" FROM " + PATIENT_TABLE + " pac "
                + " INNER JOIN " + BLOOD_TYPE_TABLE + " gs ON pac.id_blood_type = gs.id_blood_type "
                + " WHERE pac.id_patient=" + id_patient;

        Patient data = null;
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        if (c != null && c.getCount()> 0) {
            if (c.moveToFirst()) {
                data = new Patient(c.getInt(c.getColumnIndex("id_patient")),
                        c.getString(c.getColumnIndex("name")),
                        convertStringToCalendar(c.getString(c.getColumnIndex("birth_date"))),
                        c.getInt(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("genre")),
                        c.getInt(c.getColumnIndex("id_blood_type")));
            }
        }
        this.close();
        return data;

    }


    public List<Control> getAllControl()
    {
        return getAllControl("desc");
    }

    public List<Control> getAllControl(String order)
    {
        List<Control> controlList = new ArrayList<Control>();

        String SELECT_QUERY = "SELECT control.*, pac.name FROM "+ CONTROL_TABLE +" control INNER JOIN "+ PATIENT_TABLE +" pac ON control.id_patient = pac.id_patient "
                +" Order By control.date_control " + order;
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            Control data = createControl(c);
            controlList.add(data);
        }

        this.close();
        return controlList;
    }

    public Control getControl(int id_control) {
        String SELECT_QUERY = "SELECT * FROM " + CONTROL_TABLE
                +" WHERE id_control= " + id_control;

        Control control = null;
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        if (c != null && c.getCount() > 0) {
            if (c.moveToFirst()) {
                control = createControl(c);
            }
        }
        this.close();
        return control;
    }
    public Control getLastControl() {
        String SELECT_QUERY = "SELECT id_control FROM " + CONTROL_TABLE
                +" ORDER BY date_control desc limit 1 ";

        Control control = null;
        Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                control = getControl(cursor.getInt(cursor.getColumnIndex("id_control")));
            }
        }
        this.close();
        return control;
    }

    private void insertBloodType(SQLiteDatabase db)
    {
        Log.d("Populate Table: ", BLOOD_TYPE_TABLE);
        String[] group_factor={"A+","A-","B+","B-","AB+","AB-","0+","0-"};
        ContentValues values = new ContentValues();
        for(String value: group_factor)
        {
            values.put("group_factor", value);
            db.insert(BLOOD_TYPE_TABLE, null, values);
        }
    }


    public List<BloodType> getAllBloodType() {
        List<BloodType> data = new ArrayList<>();
        Cursor cursor =  getReadableDatabase().query(BLOOD_TYPE_TABLE, null, null, null, null, null, null);

        while(cursor.moveToNext())
        {
            Log.d("Select group_factor: ", cursor.getString(cursor.getColumnIndex("group_factor")));
            BloodType bloodType = new BloodType(cursor.getInt(cursor.getColumnIndex("id_blood_type")),cursor.getString(cursor.getColumnIndex("group_factor")));
            data.add(bloodType);
        }

        this.close();
        return data;
    }

    private Control createControl(Cursor c) {
        Control data = new Control(c.getInt(c.getColumnIndex("id_control")),
                c.getInt(c.getColumnIndex("id_patient")),
                c.getInt(c.getColumnIndex("teeth_amount")),
                convertStringToCalendar(c.getString(c.getColumnIndex("date_control"))),
                c.getFloat(c.getColumnIndex("weight")),
                c.getFloat(c.getColumnIndex("height")),
                c.getFloat(c.getColumnIndex("head_circumference")),
                c.getString(c.getColumnIndex("pediatrician")),
                c.getString(c.getColumnIndex("notes")),
                c.getString(c.getColumnIndex("mood")));
        return data;
    }

    public int deleteControl(int idControl) {
        int rowsDeleted = getWritableDatabase().delete(CONTROL_TABLE, "id_Control = "+idControl,null );
        return rowsDeleted;
    }


    //fecha de nac -> cuantos meses, controles: peso cm , longitud cm


    private void insertDefaultData(SQLiteDatabase db){
        insertBloodType(db);
    }

    public void deleteTables(){
        Log.d("Delete Tables: ", DATABASE_NAME);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + PATIENT_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + BLOOD_TYPE_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + CONTROL_TABLE);
    }


    public Calendar convertStringToCalendar(String date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Argentina/Cordoba"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Argentina/Cordoba"));
        try {
            cal.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public String convertCalendarToString(Calendar calendar) {
        return dateFormat.format(calendar.getTime());
    }

}
