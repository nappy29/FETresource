package com.example.fetapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DataBase {
//	private Context context;
//	private SQLiteDatabase database;
//	private Helper ourHelper;
//	
//	public DataBase(Context c){
//		context = c;
//	}
//	
//	public DataBase open(){
//		ourHelper = new Helper(context);
//		database = ourHelper.getWritableDatabase();
//		return this;
//		}
//	
//	public void close(){
//		ourHelper.close();
//		 ourHelper.close();
//		}
//	
//	public void insertData(String first, String second, String third, String fourth, String fifth, String sixth) {
//
//		  ContentValues cv = new ContentValues();
//		  cv.put(Helper.Time7, first);
//		  cv.put(Helper.Time9, second);
//		  cv.put(Helper.Time11, third);
//		  cv.put(Helper.Time13, fourth);
//		  cv.put(Helper.Time15, fifth);
//		  cv.put(Helper.Time17, sixth);
//		  database.insert(Helper.Table, null, cv);
//
//		 }
//	
//	public Cursor retreiveData(){
//		String[] all = new String[]{Helper.Time7,Helper.Time9, Helper.Time11, Helper.Time13,Helper.Time15,Helper.Time17};
//		 Cursor c = database.query(Helper.Table, all, null, null, null, null, null);
//		 return c;
//	}

}
