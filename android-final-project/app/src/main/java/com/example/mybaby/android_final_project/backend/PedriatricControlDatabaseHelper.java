package com.example.mybaby.android_final_project.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mybaby.android_final_project.model.Control;
import com.example.mybaby.android_final_project.model.Mood;
import com.example.mybaby.android_final_project.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PedriatricControlDatabaseHelper extends SQLiteOpenHelper
{
    private final static String CREATE_CONTROL_TABLE = "CREATE TABLE CONTROL (id_control INTEGER PRIMARY KEY AUTOINCREMENT, date_control TEXT,id_patient INTEGER, weight NUMERIC, height NUMERIC, head_circumference  NUMERIC, teeth_amount INTEGER, pediatrician TEXT, notes TEXT , id_mood INTEGER,date_audit NUMERIC )";
    private final static String CREATE_PATIENT_TABLE = "CREATE TABLE PACIENTE(id_patient INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL UNIQUE, birth_date TEXT,id INTEGER UNIQUE, genre TEXT, id_blood_type INTEGER, date_audit NUMERIC)";
    private final static String CREATE_BLOOD_TYPE_TABLE = "CREATE TABLE BLOOD_TYPE (id_blood_type INTEGER PRIMARY KEY AUTOINCREMENT, group_factor TEXT UNIQUE )";
    private final static String CREATE_MOOD_TABLE = "CREATE TABLE MOOD (id_mood INTEGER PRIMARY KEY AUTOINCREMENT, mood TEXT UNIQUE)";

    private final static String DATABASE_NAME = "PedriatricControlDatabase";
    private final static String TABLE_DROP_STATEMENT = "DROP TABLE IF EXISTS ";

    private static final String PATIENT_TABLE = "PATIENT" ;
    private static final String BLOOD_TYPE_TABLE = "BLOOD_TYPE";
    private static final String CONTROL_TABLE = "CONTROL";
    private static final String MOOD_TABLE = "MOOD";

    private Context context;
    private static PedriatricControlDatabaseHelper databaseInstance;


    public static PedriatricControlDatabaseHelper getDatabaseInstance(Context context)
    {
    	if(databaseInstance == null)
    	{
    		databaseInstance = new PedriatricControlDatabaseHelper(context.getApplicationContext());
    	}
    	return databaseInstance;
    }

    public PedriatricControlDatabaseHelper(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.context = context;
    }

    private PedriatricControlDatabaseHelper(Context context)
    {
    	super(context, DATABASE_NAME, null, 1);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) 
    {
        Log.d("Create Database: ", DATABASE_NAME);
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_MOOD_TABLE);
        db.execSQL(CREATE_BLOOD_TYPE_TABLE);
        db.execSQL(CREATE_CONTROL_TABLE);
        insertDefaultData();
    }


    public void onInitializeDB()
    {

        Log.d("Create Database: ", DATABASE_NAME);
        this.getWritableDatabase().execSQL(CREATE_PATIENT_TABLE);
        this.getWritableDatabase().execSQL(CREATE_MOOD_TABLE);
        this.getWritableDatabase().execSQL(CREATE_BLOOD_TYPE_TABLE);
        this.getWritableDatabase().execSQL(CREATE_CONTROL_TABLE);
        insertDefaultData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
    {
        db.execSQL(DATABASE_NAME);
    }    

    
	public long insertControl(String date_control,int id_patient, float weight , float height , float head_circumference  , int teeth_amount, String pediatrician , String notes, int id_mood)
    {	
    	ContentValues values = new ContentValues();
    	
    	values.put("date_control", date_control);
    	values.put("id_patient", id_patient);
		values.put("weight", weight);
		values.put("height", height);
		values.put("head_circumference ", head_circumference );
		values.put("teeth_amount", teeth_amount);
		values.put("notes", notes);
		values.put("date_audit",  new Date().getTime());
		values.put("pediatrician", pediatrician);
        values.put("id_mood", id_mood);

        long index = getWritableDatabase().insert(CONTROL_TABLE, null, values);
    	this.close();
        return index;
    }
    
	public long insertPatient(String name , String birth_date, int id, String sex, int id_blood_type )
	{
        Log.d("Insert Table: ", PATIENT_TABLE);
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("id_blood_type", id_blood_type);
        values.put("sex", sex);
        values.put("id", id);
        values.put("birth_date", birth_date);
        values.put("date_audit", new Date().getTime());

        long index = getWritableDatabase().insert(PATIENT_TABLE, null, values);
    	this.close();
        return index;
    }

    public Patient getPatient(String name) {
        String SELECT_QUERY = "SELECT pac.name, pac.sex, pac.id, pac.birth_date, pac.id_patient , pac.id_blood_type "
                +" FROM " + PATIENT_TABLE + " pac "
                + "INNER JOIN " + BLOOD_TYPE_TABLE + " gs ON pac.id_blood_type = gs.id_blood_type "
                + " WHERE pac.name='" + name + "'";

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
        c.close();
        return data;

    }

    public List<Control> getAllControl()
    {
        List<Control> result = new ArrayList<Control>();

        String SELECT_QUERY = "SELECT control.*, pac.name FROM "+ CONTROL_TABLE +" control INNER JOIN "+ PATIENT_TABLE +" pac ON control.id_patient = pac.id_patient "
                             +" Order By control.date_control desc";
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            Control data = new Control(c.getInt(c.getColumnIndex("id_control")),
                    c.getInt(c.getColumnIndex("id_patient")),
                    c.getInt(c.getColumnIndex("teeth_amount")),
                    convertStringToCalendar(c.getString(c.getColumnIndex("date_control"))),
                    c.getFloat(c.getColumnIndex("weight")),
                    c.getFloat(c.getColumnIndex("height")),
                    c.getFloat(c.getColumnIndex("head_circumference ")),
                    c.getString(c.getColumnIndex("pediatrician")),
                    c.getString(c.getColumnIndex("notes")));
            data.setMood(new Mood(c.getInt(c.getColumnIndex("id_mood")),
                    c.getString(c.getColumnIndex("mood"))));
            result.add(data);
        }

        c.close();
        return result;
    }

    public String[] getControl(int id_control) {
        String SELECT_QUERY = "SELECT * FROM " + CONTROL_TABLE + " WHERE id_control= " + id_control;

        String[] data = null;
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        if (c != null && c.getCount() > 0) {
            if (c.moveToFirst()) {
                data = new String[]{
                        c.getString(c.getColumnIndex("date_control")),
                        c.getString(c.getColumnIndex("weight")),
                        c.getString(c.getColumnIndex("height")),
                        c.getString(c.getColumnIndex("head_circumference ")),
                        c.getString(c.getColumnIndex("teeth_amount")),
                        c.getString(c.getColumnIndex("notes")),
                        c.getString(c.getColumnIndex("pediatrician")),
                        c.getString(c.getColumnIndex("id_control"))};
            }
        }
        this.close();
        return data;
    }

	private void insertMood()
	{
        Log.d("Populate Table: ", MOOD_TABLE);

        String[] mood={"Contento","Enojado","Lloron","Indiferente"};
    	ContentValues values = new ContentValues();

    	for(String value: mood)
		{
            values.put("mood", value);
            getWritableDatabase().insert(MOOD_TABLE, null, values);
        }

    	this.close();
    }

    private void insertBloodType()
    {
        Log.d("Populate Table: ", BLOOD_TYPE_TABLE);
        String[] group_factor={"A+","A-","B+","B-","AB+","AB-","0+","0-"};
        ContentValues values = new ContentValues();
        for(String value: group_factor)
        {
            values.put("group_factor", value);
            getWritableDatabase().insert(BLOOD_TYPE_TABLE, null, values);
        }
        this.close();
    }

	/*
    public boolean checkUser(String username, String password)
    {
    	String[] args = new String[] {username, password};
		Cursor c = getReadableDatabase().query(DATABASE_NAME_NEWS_TABLE, null, "username=? AND password=?", args, null, null, null); //Select null from users where username = ? and password =?
        this.close();

    	if (c.getCount()> 0){
            return true;
        }
        return false;
    }*/

    public List<Mood> getAllMood()
    {
        List<Mood> data = new ArrayList<>();
        Cursor cursor =  getReadableDatabase().query(MOOD_TABLE, null, null, null, null, null, null);
        while(cursor.moveToNext())
        {
            Log.d("Select mood: ", cursor.getString(cursor.getColumnIndex("mood")));
            Mood mood= new Mood(cursor.getInt(cursor.getColumnIndex("id_mood")),cursor.getString(cursor.getColumnIndex("mood")));
            data.add(mood);
        }

        cursor.close();
        return data;
    }

    public Mood getMoodByName(String name) {
        Mood mood = null;
        Cursor c = getReadableDatabase().query(MOOD_TABLE, new String[]{"mood"}, null, null, null, null, null);
        if (c.getCount() > 0) {
            c.moveToNext();
            Log.d("mood: ", c.getString(c.getColumnIndex("mood")));
            mood = new Mood(c.getInt(c.getColumnIndex("id_mood")), c.getString(c.getColumnIndex("mood")));
        }

        this.close();
        return mood;
    }

   private void insertDefaultData(){
       insertBloodType();
       insertMood();
   }
    public void deleteTables(){
        Log.d("Delete Tables: ", DATABASE_NAME);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + PATIENT_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + MOOD_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + BLOOD_TYPE_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + CONTROL_TABLE);
    }

    public Calendar convertStringToCalendar(String date){
        Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
	}
}
