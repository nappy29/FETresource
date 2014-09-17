package com.example.fetapp;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class Pref extends PreferenceActivity {
	SharedPreferences pref;
	//PendingIntent pendingIntent;
	Calendar calendar;
	private CheckBoxPreference mCheckBoxPreference;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
		
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		Boolean value = pref.getBoolean("allow", true);		
		 mCheckBoxPreference = (CheckBoxPreference) getPreferenceScreen().findPreference("allow");		 
		 CheckBoxPreference checkboxPref = (CheckBoxPreference)getPreferenceManager().findPreference("allow");
		   checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {            

		   public boolean onPreferenceChange(Preference preference, Object newValue) {

		          boolean myValue = (Boolean) newValue;

		          if(myValue){
		        	  calendar = Calendar.getInstance();	          		     
			          calendar.set(Calendar.HOUR_OF_DAY, 15);
			          calendar.set(Calendar.MINUTE, 0);
			          calendar.set(Calendar.SECOND, 0);
		        	  
		               	Intent myIntent = new Intent(Pref.this, MyReceiver.class);
				        PendingIntent  pendingIntent = PendingIntent.getBroadcast(Pref.this, 0, myIntent,0);
				          AlarmManager  alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		                 alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_HOUR*2, pendingIntent);
		                 // startService(new Intent(Pref.this, ServiceAlert.class));
		        	 
		          }
		          else{
		        	 
			      	  Intent myIntent = new Intent(Pref.this, MyReceiver.class);
			          PendingIntent  pendingIntent = PendingIntent.getBroadcast(Pref.this, 0, myIntent,0);
			          AlarmManager  alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
			          alarmManager.cancel(pendingIntent);
			          Log.d("Service", "stop this service");
		                  //stopService(new Intent(Pref.this, ServiceAlert.class));
		          }       
		          

		         return true;
		    }
		}); 
		
	}
	
/*	@Override
	  protected void onResume() {
	    super.onResume();
	    getPreferenceScreen().getSharedPreferences()
	        .registerOnSharedPreferenceChangeListener(this);
	  }
	
	@SuppressWarnings("deprecation")
	@Override
	  protected void onPause() {
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences()
	        .registerOnSharedPreferenceChangeListener(this);
	   }
	
	@SuppressWarnings("deprecation")
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		//updatePreferences(findPreference(key));
		
		
	}
	
	 private void updatePreferences(Preference p) {
	    if (p instanceof CheckBoxPreference) {
	      CheckBoxPreference checkbox = (CheckBoxPreference) p;
	      String key = p.getKey();
	      if(key.equals("allow")){
	    	  if(checkbox.isChecked())
	    		  //startService(new Intent(this, ServiceAlert.class));
	    		  Log.d("Service", "Thanks for this ");
	      }
	    }
	  }   
*/
}
