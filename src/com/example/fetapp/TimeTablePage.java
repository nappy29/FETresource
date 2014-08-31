package com.example.fetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TimeTablePage extends Activity{
	TextView text;
	private Intent inten;
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
	/*	 row1col1 = (TextView) findViewById(R.id.row1col1);
		 row1col2 = (TextView) findViewById(R.id.row1col2);
		 row1col3 = (TextView) findViewById(R.id.row1col3);
		 row1col4 = (TextView) findViewById(R.id.row1col4);
		 row1col5 = (TextView) findViewById(R.id.row1col5);
		 row1col6 = (TextView) findViewById(R.id.row1col6);
		 row2col1 = (TextView) findViewById(R.id.row2col1);
		 row2col2 = (TextView) findViewById(R.id.row2col2);
		 row2col3 = (TextView) findViewById(R.id.row2col3);
		 row2col4 = (TextView) findViewById(R.id.row2col4);
		 row2col5 = (TextView) findViewById(R.id.row2col5);
		 row2col6 = (TextView) findViewById(R.id.row2col6);
		 row3col1 = (TextView) findViewById(R.id.row3col1);
		 row3col2 = (TextView) findViewById(R.id.row3col2);
		 row3col3 = (TextView) findViewById(R.id.row3col3);
		 row3col4 = (TextView) findViewById(R.id.row3col4);
		 row3col5 = (TextView) findViewById(R.id.row3col5);
		 row3col6 = (TextView) findViewById(R.id.row3col6);
		 row4col1 = (TextView) findViewById(R.id.row4col1);
		 row4col2 = (TextView) findViewById(R.id.row4col2);
		 row4col3 = (TextView) findViewById(R.id.row4col3);
		 row4col4 = (TextView) findViewById(R.id.row4col4);
		 row4col5 = (TextView) findViewById(R.id.row4col5);
		 row4col6 = (TextView) findViewById(R.id.row4col6);
		 row5col1 = (TextView) findViewById(R.id.row5col1);
		 row5col2 = (TextView) findViewById(R.id.row5col2);
		 row5col3 = (TextView) findViewById(R.id.row5col3);
		 row5col4 = (TextView) findViewById(R.id.row5col4);
		 row5col5 = (TextView) findViewById(R.id.row5col5);
		 row5col6 = (TextView) findViewById(R.id.row5col6);
		 row6col1 = (TextView) findViewById(R.id.row6col1);
		 row6col2 = (TextView) findViewById(R.id.row6col2);
		 row6col3 = (TextView) findViewById(R.id.row6col3);
		 row6col4 = (TextView) findViewById(R.id.row6col4);
		 row6col5 = (TextView) findViewById(R.id.row6col5);
		 row6col6 = (TextView) findViewById(R.id.row6col6);
	*/	
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
				startActivity(inten);
			}
		});
	}
		/*	Bundle extra = getIntent().getExtras();
		String var1=extra.getString("spinner1");
		String var2=extra.getString("spinner11");
		if(extra!=null){
			if(var1.equals("Computer engineering")){
				if(var2.equals("200")){
					text.append(" Computer Engineering Level 200");
					inten.putExtra("spinner1", "Computer engineering");
					inten.putExtra("spinner11", "200");
					
					row1col1.setText("CEF201 CRB II 50 b");
					row1col2.setText("CEF203");
					row1col5.setText("CEF211(amphi 600)");
					
					row2col1.setText("CEF209");
					row2col2.setText("CEF207");
					row2col3.setText("CEF207");
					row2col4.setText("CEF217");
					row2col6.setText("CEF205(Ublock D)");
					
					row3col3.setText("CEF211");
					row3col5.setText("CEF211");
					
					row4col5.setText("CEF201");
					
					row5col3.setText("CEF211");
					row5col5.setText("CEF205");
					
					row6col1.setText("CEF201");
					row6col2.setText("CEF209");					
				}  
				
				if(var2.equals("300")){
					text.append(" Computer Engineering Level 300");
					inten.putExtra("spinner1", "Computer engineering");
					inten.putExtra("spinner11", "300");
					
					row1col4.setText("CEF201");
					row1col1.setText("CEF203");
					row1col5.setText("CEF211");
					
					row2col1.setText("CEF209");
					row2col2.setText("CEF207");
					row2col4.setText("CEF207");
					row2col5.setText("CEF217");
					row2col6.setText("CEF205");
					
					row3col2.setText("CEF211");
					row3col5.setText("CEF211");
					
					row4col5.setText("CEF201");
					
					row5col3.setText("CEF211");
					row5col6.setText("CEF205");
					row5col1.setText("CEF201");
					
					row6col2.setText("CEF209");
				}
			}
		}
		
	}  */
}
