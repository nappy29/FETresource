package com.example.fetapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;


public class Helper extends SQLiteOpenHelper{
	public static final String DB_NAME = "courses";
	public static final int DB_VERSION = 1;
	public static final String Table= "course";
	public static final String ClassId = "id";
	public static final String Time7 = "first";
	public static final String Time9 = "second";
	public static final String Time11 = "third";
	public static final String Time13 = "fouth";
	public static final String Time15 = "fifth";
	public static final String Time17 = "sixth";
//	private Context context;
//	private DataBase ourHelper;
	
	private SQLiteDatabase ourDatabase;	
	TextView[][] textviews  = new TextView[6][6];
	
	public Helper(Context c){
		super(c, DB_NAME, null, DB_VERSION);
	}
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			 String sql = "   create table " + Helper.Table + " (" + Helper.ClassId 
			          + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			          + Helper.Time7 + " TEXT," 
			          + Helper.Time9 + " TEXT," 
			          + Helper.Time11 +" TEXT," 
			          + Helper.Time13 +" TEXT," 
			          + Helper.Time15 +" TEXT," 
			          + Helper.Time17 +" TEXT)";
			
			db.execSQL("   create table " + Helper.Table + " (" + Helper.ClassId 
			          + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			          + Helper.Time7 + " TEXT," 
			          + Helper.Time9 + " TEXT," 
			          + Helper.Time11 +" TEXT," 
			          + Helper.Time13 +" TEXT," 
			          + Helper.Time15 +" TEXT," 
			          + Helper.Time17 +" TEXT)");
			Log.d("DB", "onCreated sql: " + sql);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + Table); // drops the old database
			Log.d("DB", "onUpdated");
			onCreate(db);

			
		}		
		
		public void insertData(String first, String second, String third, String fourth, String fifth, String sixth) {
			SQLiteDatabase db = this.getWritableDatabase();
			  ContentValues cv = new ContentValues();
			  cv.put(Helper.Time7, first);
			  cv.put(Helper.Time9, second);
			  cv.put(Helper.Time11, third);
			  cv.put(Helper.Time13, fourth);
			  cv.put(Helper.Time15, fifth);
			  cv.put(Helper.Time17, sixth);
			  db.insert(Helper.Table, null, cv);

			 }
		
		public Cursor retreiveData(){
		    SQLiteDatabase db = this.getReadableDatabase();
			String[] all = new String[]{Helper.Time7,Helper.Time9, Helper.Time11, Helper.Time13,Helper.Time15,Helper.Time17};
			 Cursor c = db.query(Helper.Table, all, null, null, null, null, null);
			 return c;
		}

	}
	
	
	


