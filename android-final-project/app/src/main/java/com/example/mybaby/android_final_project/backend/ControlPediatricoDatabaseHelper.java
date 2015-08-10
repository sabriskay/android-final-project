package com.example.mybaby.android_final_project.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class ControlPediatricoDatabaseHelper extends SQLiteOpenHelper
{
    private final static String CREATE_CONTROL_TABLE = "CREATE TABLE CONTROL (id_control INTEGER PRIMARY KEY AUTOINCREMENT, fecha_control TEXT,id_paciente INTEGER, peso NUMERIC, altura NUMERIC, perimetro_cefalico NUMERIC, cantidad_dientes INTEGER, pediatra TEXT, notas TEXT ,fecha_audit NUMERIC )";
    private final static String CREATE_PACIENTE_TABLE = "CREATE TABLE PACIENTE(id_paciente INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL UNIQUE, fecha_nac TEXT,dni INTEGER UNIQUE, sexo TEXT, id_grupo_sanguineo INTEGER, fecha_audit NUMERIC)";
    private final static String CREATE_GRUPO_SANGUINEO_TABLE = "CREATE TABLE GRUPO_SANGUINEO (id_grupo_sanguineo INTEGER PRIMARY KEY AUTOINCREMENT, grupo_factor TEXT UNIQUE )";
    private final static String CREATE_HUMOR_TABLE = "CREATE TABLE HUMOR (id_humor INTEGER PRIMARY KEY AUTOINCREMENT, humor TEXT UNIQUE)";
    private final static String CREATE_CONTROL_HUMOR_TABLE = "CREATE TABLE HUMORXCONTROL(id_humor INTEGER, id_control INTEGER)";


    private final static String DATABASE_NAME = "ControlPediatricoDatabase";
    private final static String TABLE_DROP_STATEMENT = "DROP TABLE IF EXISTS ";

    private static final String PACIENTE_TABLE = "PACIENTE" ;
    private static final String GRUPO_SANGUINEO_TABLE = "GRUPO_SANGUINEO";
    private static final String CONTROL_TABLE = "CONTROL";
    private static final String CONTROL_HUMOR_TABLE= "HUMORXCONTROL";
    private static final String HUMOR_TABLE= "HUMOR";

    private Context context;
    private static ControlPediatricoDatabaseHelper databaseInstance;


    public static ControlPediatricoDatabaseHelper getDatabaseInstance(Context context)
    {
    	if(databaseInstance == null)
    	{
    		databaseInstance = new ControlPediatricoDatabaseHelper(context.getApplicationContext());
    	}
    	return databaseInstance;
    }

    public ControlPediatricoDatabaseHelper(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.context = context;
    }

    private ControlPediatricoDatabaseHelper(Context context)
    {
    	super(context, DATABASE_NAME, null, 1);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) 
    {
        Log.d("Create Database: ", DATABASE_NAME);
        db.execSQL(CREATE_PACIENTE_TABLE);
        db.execSQL(CREATE_HUMOR_TABLE);
        db.execSQL(CREATE_GRUPO_SANGUINEO_TABLE);
        db.execSQL(CREATE_CONTROL_TABLE);
        db.execSQL(CREATE_CONTROL_HUMOR_TABLE);
        insertDefaultData();
    }


    public void onInitializeDB()
    {

        Log.d("Create Database: ", DATABASE_NAME);
        this.getWritableDatabase().execSQL(CREATE_PACIENTE_TABLE);
        this.getWritableDatabase().execSQL(CREATE_HUMOR_TABLE);
        this.getWritableDatabase().execSQL(CREATE_GRUPO_SANGUINEO_TABLE);
        this.getWritableDatabase().execSQL(CREATE_CONTROL_TABLE);
        this.getWritableDatabase().execSQL(CREATE_CONTROL_HUMOR_TABLE);
        insertDefaultData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
    {
        db.execSQL(DATABASE_NAME);
    }    

    
	public long insertControl(String fecha_control,int id_paciente, float peso , float altura , float perimetro_cefalico , int cantidad_dientes, String pediatra , String notas)
    {	
    	ContentValues values = new ContentValues();
    	
    	values.put("fecha_control", fecha_control);
    	values.put("id_paciente", id_paciente);
		values.put("peso", peso);
		values.put("altura", altura);
		values.put("perimetro_cefalico", perimetro_cefalico);
		values.put("cantidad_dientes", cantidad_dientes);
		values.put("notas", notas);
		values.put("fecha_audit",  new Date().getTime());
		values.put("pediatra", pediatra);

        long indice = getWritableDatabase().insert(CONTROL_TABLE, null, values);
    	this.close();
        return indice;
    }
    
	public long insertPaciente(String nombre , String fecha_nac, int DNI, String sexo, int id_grupo_sanguineo )
	{
        Log.d("Insert Table: ", PACIENTE_TABLE);
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("id_grupo_sanguineo", id_grupo_sanguineo);
        values.put("sexo", sexo);
        values.put("dni", DNI);
        values.put("fecha_nac", fecha_nac);
        values.put("fecha_audit", new Date().getTime());

        long indice = getWritableDatabase().insert(PACIENTE_TABLE, null, values);
    	this.close();
        return indice;
    }

    public String[] getPaciente(String nombre) {
        String SELECT_QUERY = "SELECT pac.nombre, pac.sexo, pac.dni, date(pac.fecha_nac) as fecha_nac,pac.id_paciente,  gs.* FROM " + PACIENTE_TABLE + " pac "
                + "INNER JOIN " + GRUPO_SANGUINEO_TABLE + " gs ON pac.id_grupo_sanguineo = gs.id_grupo_sanguineo "
                + " WHERE pac.nombre='" + nombre + "'";

        String[] datos = null;
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        if (c != null && c.getCount()> 0) {
            if (c.moveToFirst()) {
                datos = new String[]{c.getString(c.getColumnIndex("nombre")),
                        c.getString(c.getColumnIndex("sexo")),
                        c.getString(c.getColumnIndex("dni")),
                        c.getString(c.getColumnIndex("fecha_nac")),
                        c.getString(c.getColumnIndex("grupo_factor")),
                        c.getString(c.getColumnIndex("id_paciente")),
                        c.getString(c.getColumnIndex("id_grupo_sanguineo"))};
            }
        }
        c.close();
        return datos;

    }

    public ArrayList<String[]> getAllControl()
    {
        ArrayList<String[]> result = new ArrayList<String[]>();

        String SELECT_QUERY = "SELECT control.*, pac.nombre FROM "+ CONTROL_TABLE +" control INNER JOIN "+ PACIENTE_TABLE +" pac ON control.id_paciente = pac.id_paciente "
                             +" Order By control.fecha_control desc";

        String[] datos;
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            datos = new String[]{c.getString(c.getColumnIndex("fecha_control")),
                    c.getString(c.getColumnIndex("peso")),
                    c.getString(c.getColumnIndex("altura")),
                    c.getString(c.getColumnIndex("perimetro_cefalico")),
                    c.getString(c.getColumnIndex("cantidad_dientes")),
                    c.getString(c.getColumnIndex("notas")),
                    c.getString(c.getColumnIndex("pediatra")),
                    c.getString(c.getColumnIndex("id_control"))};
            result.add(datos);
        }

        c.close();
        return result;
    }

    public String[] getControl(int id_control) {
        String SELECT_QUERY = "SELECT * FROM " + CONTROL_TABLE + " WHERE id_control= " + id_control;

        String[] datos = null;
        Cursor c = this.getReadableDatabase().rawQuery(SELECT_QUERY, null);
        if (c != null && c.getCount() > 0) {
            if (c.moveToFirst()) {
                datos = new String[]{
                        c.getString(c.getColumnIndex("fecha_control")),
                        c.getString(c.getColumnIndex("peso")),
                        c.getString(c.getColumnIndex("altura")),
                        c.getString(c.getColumnIndex("perimetro_cefalico")),
                        c.getString(c.getColumnIndex("cantidad_dientes")),
                        c.getString(c.getColumnIndex("notas")),
                        c.getString(c.getColumnIndex("pediatra")),
                        c.getString(c.getColumnIndex("id_control"))};
            }
        }
        c.close();
        return datos;
    }

    private void insertControlXHumor(int id_control, int id_humor)
	{
    	ContentValues values = new ContentValues();
    	values.put("id_control", id_control);
    	values.put("id_humor", id_humor);
		
    	getWritableDatabase().insert(CONTROL_HUMOR_TABLE, null, values);
    	this.close();
    }

	private void insertHumor()
	{
        Log.d("Populate Table: ", HUMOR_TABLE);

        String[] humor={"Contento","Enojado","Lloron","Indiferente"};
    	ContentValues values = new ContentValues();

    	for(String value: humor)
		{
             values.put("humor", value);
            getWritableDatabase().insert(HUMOR_TABLE, null, values);
        }

    	this.close();
    }

    private void insertTipoSangre()
    {
        Log.d("Populate Table: ", GRUPO_SANGUINEO_TABLE);
        String[] grupo_factor={"A+","A-","B+","B-","AB+","AB-","0+","0-"};
        ContentValues values = new ContentValues();
        for(String value: grupo_factor)
        {
            values.put("grupo_factor", value);
            getWritableDatabase().insert(GRUPO_SANGUINEO_TABLE, null, values);
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

    public String[] getAllHumor()
    {
        String[] datos = new String[10];
        Cursor cursor =  getReadableDatabase().query(HUMOR_TABLE, null, null, null, null, null, null);
        while(cursor.moveToNext())
        {
            Log.d("Select Humor: ", cursor.getString(cursor.getColumnIndex("humor")));
        }
        this.close();
        cursor.close();
        return datos;
    }

    public void getHumorByName(String name)
    {
        Cursor c = getReadableDatabase().query(HUMOR_TABLE, new String[] {"humor"}, null, null, null, null, null);
        while(c.moveToNext())
        {
            Log.d("HUMOR: ", c.getString(c.getColumnIndex("humor")));
        }
        this.close();
    }

   private void insertDefaultData(){
       insertTipoSangre();
       insertHumor();
   }
    public void deleteTables(){
        Log.d("Delete Tables: ", DATABASE_NAME);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + PACIENTE_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + HUMOR_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + GRUPO_SANGUINEO_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + CONTROL_TABLE);
        this.getWritableDatabase().execSQL(TABLE_DROP_STATEMENT + CONTROL_HUMOR_TABLE);
    }

    public String convertLongToDateString(Long date){
		// set the format to sql date time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(new Date(date));
        return stringDate;
	}
}
