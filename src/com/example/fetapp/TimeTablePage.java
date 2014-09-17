package com.example.fetapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TimeTablePage extends Activity{
	TextView text;
	private Intent inten;
	DbControl insert;
	TextView row1col1,row1col2,row1col3,row1col4,row1col5,row1col6,row2col1,row2col2,row2col3,row2col4,
	         row2col5,row2col6,row3col1,row3col2,row3col3,row3col4,row3col5,row3col6,row4col1,row4col2,row4col3,
	         row4col4,row4col5,row4col6,row5col1,row5col2,row5col3,row5col4,row5col5,row5col6,row6col1,row6col2,
	         row6col3,row6col4,row6col5,row6col6;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetablecomyear1);
		Button courseoutline = (Button) findViewById(R.id.courseoutline);
		Button update = (Button)findViewById(R.id.update);
		text = (TextView)findViewById(R.id.timetitle);
		insert = new DbControl(this);
		
	//	insert.open();
		
		inten = new Intent(TimeTablePage.this, CoursePage.class);
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TimeTablePage.this, UpdateTimeTable.class));
				
			}
		});
		courseoutline.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TimeTablePage.this, Courses.class));
			}
		});
		mytable();
	}
	
	public void mytable(){
		TableLayout tablelayout;
       Log.d("Work beginning", "THE main thread here");
       
       insert.open();
		Cursor c = insert.retreiveData();
   	 
   	 try{
   		 if(c.getCount()!=0){
   			 if(c.moveToFirst()){
   				 tablelayout = (TableLayout)findViewById(R.id.timet);
   				 int rows = c.getCount();
   	        		 
   	        		 do{
   	        			TableRow row = new TableRow(TimeTablePage.this);
      	        		 row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 
      	        				 TableLayout.LayoutParams.WRAP_CONTENT));
           	        	 int cols = c.getColumnCount();
           	        	 for (int j = 0; j < cols; j++){
           	        		 TextView tv = new TextView(TimeTablePage.this);
                                tv.setLayoutParams(new TableRow.LayoutParams(
                                        TableLayout.LayoutParams.MATCH_PARENT,
                                        TableLayout.LayoutParams.WRAP_CONTENT));
                                tv.setGravity(Gravity.LEFT);
                                tv.setTextSize(10);
                                Log.d("Timetable", c.getString(j));
                                tv.setText(c.getString(j));
                                
                                row.addView(tv);
           	        	 }
           	        	 tablelayout.addView(row);
   	        		 }while(c.moveToNext());
   	        	 
   			 }
   		 }
   	 }catch (SQLException mSQLException) {
   	        throw mSQLException;
   	    }
   	// insert.close();
	}

}
