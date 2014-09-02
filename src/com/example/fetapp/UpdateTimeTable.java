package com.example.fetapp;


import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class UpdateTimeTable extends Activity{
	ProgressDialog pDialog ;
	//DataBase database;
	//Helper helper;
	Button save;
	TextView[][] text =null;
	private EditText edit1;
	private EditText edit2;
	private EditText edit3;
	private EditText edit4;
	private EditText edit5;
	private EditText edit6;
	private EditText edit11;
	private EditText edit13;
	private EditText edit12;
	private EditText edit15;
	private EditText edit14;
	private EditText edit16;
	private EditText edit21;
	private EditText edit22;
	private EditText edit23;
	private EditText edit24;
	private EditText edit25;
	private EditText edit26;
	private EditText edit31;
	private EditText edit32;
	private EditText edit34;
	private EditText edit33;
	private EditText edit35;
	private EditText edit36;
	private EditText edit41;
	private EditText edit42;
	private EditText edit43;
	private EditText edit45;
	private EditText edit44;
	private EditText edit46;
	private EditText edit52;
	private EditText edit51;
	private EditText edit53;
	private EditText edit54;
	private EditText edit55;
	private EditText edit56;
	
	DbControl insert;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatetimetable);
	
		//helper = new Helper(UpdateTimeTable.this);	
		insert = new DbControl(this);
		//helper.open();
	/*	text = new TextView[6][6];
		text[0][0] = (TextView) findViewById(R.id.row1col1);
		text[0][1] = (TextView) findViewById(R.id.row1col2);
		text[0][2] = (TextView) findViewById(R.id.row1col3);
		text[0][3] = (TextView) findViewById(R.id.row1col4);
		text[0][4] = (TextView) findViewById(R.id.row1col5);
		text[0][5] = (TextView) findViewById(R.id.row1col6);
		
		text[1][0] = (TextView) findViewById(R.id.row2col1);
		text[1][1] = (TextView) findViewById(R.id.row2col2);
		text[1][2] = (TextView) findViewById(R.id.row2col3);
		text[1][3] = (TextView) findViewById(R.id.row2col4);
		text[1][4] = (TextView) findViewById(R.id.row2col5);
		text[1][5] = (TextView) findViewById(R.id.row2col6);
		
		text[2][0] = (TextView) findViewById(R.id.row3col1);
		text[2][1] = (TextView) findViewById(R.id.row3col2);
		text[2][2] = (TextView) findViewById(R.id.row3col3);
		text[2][3] = (TextView) findViewById(R.id.row3col4);
		text[2][4] = (TextView) findViewById(R.id.row3col5);
		text[2][5] = (TextView) findViewById(R.id.row3col6);
		
		text[3][0] = (TextView) findViewById(R.id.row4col1);
		text[3][1] = (TextView) findViewById(R.id.row4col2);
		text[3][2] = (TextView) findViewById(R.id.row4col3);
		text[3][3] = (TextView) findViewById(R.id.row4col4);
		text[3][4] = (TextView) findViewById(R.id.row4col5);
		text[3][5] = (TextView) findViewById(R.id.row4col6);
		
		text[4][0] = (TextView) findViewById(R.id.row5col1);
		text[4][1] = (TextView) findViewById(R.id.row5col2);
		text[4][2] = (TextView) findViewById(R.id.row5col3);
		text[4][3] = (TextView) findViewById(R.id.row5col4);
		text[4][4] = (TextView) findViewById(R.id.row5col5);
		text[4][5] = (TextView) findViewById(R.id.row5col6);
		
		text[5][0] = (TextView) findViewById(R.id.row6col1);
		text[5][1] = (TextView) findViewById(R.id.row6col2);
		text[5][2] = (TextView) findViewById(R.id.row6col3);
		text[5][3] = (TextView) findViewById(R.id.row6col4);
		text[5][4] = (TextView) findViewById(R.id.row6col5);
		text[5][5] = (TextView) findViewById(R.id.row6col6);     */
		
		edit1 = (EditText)findViewById(R.id.edit1Text1);
		edit2 = (EditText)findViewById(R.id.edit1Text2);
		edit3 = (EditText)findViewById(R.id.edit1Text3);
		edit4 = (EditText)findViewById(R.id.edit1Text4);
		edit5 = (EditText)findViewById(R.id.edit1Text5);
		edit6 = (EditText)findViewById(R.id.edit1Text6);
		
		edit11 = (EditText)findViewById(R.id.edit2Text1);
		edit12 = (EditText)findViewById(R.id.edit2Text2);
		edit13 = (EditText)findViewById(R.id.edit2Text3);
		edit14 = (EditText)findViewById(R.id.edit2Text4);
		edit15 = (EditText)findViewById(R.id.edit2Text5);
		edit16 = (EditText)findViewById(R.id.edit2Text6);
		
		edit21 = (EditText)findViewById(R.id.edit3Text1);
		edit22 = (EditText)findViewById(R.id.edit3Text2);
		edit23 = (EditText)findViewById(R.id.edit3Text3);
		edit24 = (EditText)findViewById(R.id.edit3Text4);
		edit25 = (EditText)findViewById(R.id.edit3Text5);
		edit26 = (EditText)findViewById(R.id.edit3Text6);
		
		edit31 = (EditText)findViewById(R.id.edit4Text1);
		edit32 = (EditText)findViewById(R.id.edit4Text2);
		edit33 = (EditText)findViewById(R.id.edit4Text3);
		edit34 = (EditText)findViewById(R.id.edit4Text4);
		edit35 = (EditText)findViewById(R.id.edit4Text5);
		edit36 = (EditText)findViewById(R.id.edit4Text6);
		
		edit41 = (EditText)findViewById(R.id.edit5Text1);
		edit42 = (EditText)findViewById(R.id.edit5Text2);
		edit43 = (EditText)findViewById(R.id.edit5Text3);
		edit44 = (EditText)findViewById(R.id.edit5Text4);
		edit45 = (EditText)findViewById(R.id.edit5Text5);
		edit46 = (EditText)findViewById(R.id.edit5Text6);
		
		edit51 = (EditText)findViewById(R.id.edit6Text1);
		edit52 = (EditText)findViewById(R.id.edit6Text2);
		edit53 = (EditText)findViewById(R.id.edit6Text3);
		edit54 = (EditText)findViewById(R.id.edit6Text4);
		edit55 = (EditText)findViewById(R.id.edit6Text5);
		edit56 = (EditText)findViewById(R.id.edit6Text6);
		
		
		Button save = (Button)findViewById(R.id.savebut);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				insert.droptable();
				runOnUiThread(new Runnable() {
	        	     @Override
	        	     public void run() {
                       new justGo().execute();
	     				mytable();

	        	    }
	        	});
				startActivity(new Intent(UpdateTimeTable.this, TimeTablePage.class));
			}	
	        	 
		});
	}
	
	public void mytable(){
		TableLayout tablelayout;
       Log.d("Work beginning", "THE main thread here");
       
       insert.open();
		Cursor c = insert.retreiveData();
   	 
   	 try{
   		 if(c.getCount()!=0){
   			 if(c.moveToFirst()){
   				 tablelayout = (TableLayout)findViewById(R.id.timetablelayout);
   				 int rows = c.getCount();
   	        		 
   	        		 do{
   	        			TableRow row = new TableRow(UpdateTimeTable.this);
      	        		 row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 
      	        				 TableLayout.LayoutParams.WRAP_CONTENT));
           	        	 int cols = c.getColumnCount();
           	        	 for (int j = 0; j < cols; j++){
           	        		 TextView tv = new TextView(UpdateTimeTable.this);
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
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    if (pDialog != null) {
	        pDialog.dismiss();
	        pDialog = null;
	    }
	}

private class justGo extends AsyncTask<Void, Void, Void> {
    	
    	
		//private ViewGroup tablelayout = (ViewGroup)findViewById(R.id.timetablelayout);

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Show the progress in load
            pDialog = new ProgressDialog(UpdateTimeTable.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
    	
        @Override
        protected Void doInBackground(Void... any) {
        	
        	String Edit1 = edit1.getText().toString();
        	String Edit2 = edit2.getText().toString();
        	String Edit3 = edit3.getText().toString();
        	String Edit4 = edit4.getText().toString();
        	String Edit5 = edit5.getText().toString();
        	String Edit6 = edit6.getText().toString();
        	    
        	
        	String Edit11 = edit11.getText().toString();
        	String Edit12 = edit12.getText().toString();
        	String Edit13 = edit13.getText().toString();
        	String Edit14 = edit14.getText().toString();
        	String Edit15 = edit15.getText().toString();
        	String Edit16 = edit16.getText().toString();
        	  
        	
        	String Edit21 = edit21.getText().toString();
        	String Edit22 = edit22.getText().toString();
        	String Edit23 = edit23.getText().toString();
        	String Edit24 = edit24.getText().toString();
        	String Edit25 = edit25.getText().toString();
        	String Edit26 = edit26.getText().toString();
        	
        	String Edit31 = edit31.getText().toString();
        	String Edit32 = edit32.getText().toString();
        	String Edit33 = edit33.getText().toString();
        	String Edit34 = edit34.getText().toString();
        	String Edit35 = edit35.getText().toString();
        	String Edit36 = edit36.getText().toString();
        	
        	String Edit41 = edit41.getText().toString();
        	String Edit42 = edit42.getText().toString();
        	String Edit43 = edit43.getText().toString();
        	String Edit44 = edit44.getText().toString();
        	String Edit45 = edit45.getText().toString();
        	String Edit46 = edit46.getText().toString();  	
        	String Edit51 = edit51.getText().toString();
        	String Edit52 = edit52.getText().toString();
        	String Edit53 = edit53.getText().toString();
        	String Edit54 = edit54.getText().toString();
        	String Edit55 = edit55.getText().toString();
        	String Edit56 = edit56.getText().toString();
        	
        	
        	insert.open();
        	insert.insertData(Edit1, Edit2, Edit3, Edit4, Edit5, Edit6);
       	    insert.insertData(Edit11, Edit12, Edit13, Edit14, Edit15, Edit16);
       	    insert.insertData(Edit21, Edit22, Edit23, Edit24, Edit25, Edit26);
       	    insert.insertData(Edit31, Edit32, Edit33, Edit34, Edit35, Edit36);
       	    insert.insertData(Edit41, Edit42, Edit43, Edit44, Edit45, Edit46);
       	    insert.insertData(Edit41, Edit42, Edit43, Edit44, Edit45, Edit46);       	    
        	 insert.insertData(Edit51, Edit52, Edit53, Edit54, Edit55, Edit56);
        	
        	 
        	 
        	 
        	 
        	  
      	
			return null;
        }
        
        @Override
        protected void onPostExecute(Void nap) {
        	super.onPostExecute(null);
        	// Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();     
            
//      	 startActivity(new Intent(UpdateTimeTable.this, TimeTablePage.class));

       }
    }
}
