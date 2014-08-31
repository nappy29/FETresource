package com.example.fetapp;

import java.util.Calendar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class ServiceAlert extends Service {
	Calendar cal;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		super.onStartCommand(intent, flags, startId);
		
		Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
		Log.d("app", "this service is working");
		CreateNotification();
		return START_STICKY;
	}
	
	public void CreateNotification() {
		  // Create Intent for notification
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		  Intent intent = new Intent(this, TimeTable.class);
		  PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
		    PendingIntent.FLAG_UPDATE_CURRENT);

		  // Defining notification
		  NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(
		    this).setSmallIcon(R.drawable.luanc)
		    .setContentTitle("You have a class soon")
		    .setContentText("Check your timetable").setContentIntent(pi).setSound(soundUri)
		    .addAction(R.drawable.luanc, "View", pi);

		  // Allows notification to be cancelled when user clicks it
		  nBuilder.setAutoCancel(true);

		  // Issuing notification

		  int notificationId = 01;
		  NotificationManager notifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		  notifyMgr.notify(notificationId, nBuilder.build());

		 }

}
