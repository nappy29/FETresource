package com.example.fetapp;

import android.app.Activity;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainPage extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstaceState){
		
		super.onCreate(savedInstaceState);    //called first when Activity called
		setContentView(R.layout.mainpage);
		Button info= (Button) findViewById(R.id.info);
		Button timetables = (Button) findViewById(R.id.resource);
		Button gist = (Button) findViewById(R.id.gist);
		Button course = (Button) findViewById(R.id.courses);
		Button files = (Button) findViewById(R.id.files);
		Button past  = (Button) findViewById(R.id.pastquest);
		Button result = (Button)findViewById(R.id.results);
		
		result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainPage.this, Results.class));
				
			}
		});
		
		past.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainPage.this, QuestionClass.class));
				
			}
		});
		info.setOnClickListener(new OnClickListener() {  //action when info button clicked
			
			public void onClick(View v) {
				
				startActivity(new Intent(MainPage.this, Ubinfo.class));
			}
		});
		
		gist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainPage.this, Gist.class));
				
			}
		});
		
		course.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(MainPage.this, Courses.class));
				
			}
		});
		
		timetables.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
                startActivity(new Intent(MainPage.this, TimeTablePage.class));
				
			}
		});
		
		files.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainPage.this, UsefullLink.class));
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	//Relinquish the menu
	inflater.inflate(R.menu.mainmenu, menu);
	//
	return true; //show up the menu when clicked
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	//
	case R.id.details:
	startActivity(new Intent(this, Pref.class)); //
	break;
	}
      return true;
	}    


}
