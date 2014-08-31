package com.example.fetapp;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Home extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);
		
				
		Button b = (Button) findViewById(R.id.gobutton);
		b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(Home.this, MainPage.class));

				
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
