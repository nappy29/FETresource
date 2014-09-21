package com.example.fetapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Questions extends Activity implements OnItemSelectedListener{
	
	private Spinner sem1, sem2;
	private String[] department = {"Analysis", "Linear Algebra","Introduction to computing", "C Programming", "Discrete Mathematics", "English101"};
	private String[] level =  {"Computer Architecture", "Information Systems", "CVE100", "English102"};
	private ArrayAdapter<String> adapter1;
	private Intent inten;
	private String url;
	
	@Override
	public void onCreate(Bundle savedInstaceState){
		super.onCreate(savedInstaceState);
		
		setContentView(R.layout.quest);
		
		sem1= (Spinner) findViewById(R.id.sem1);
		adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,department);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sem1.setAdapter(adapter1);
		sem1.setOnItemSelectedListener(this);
		
		sem2 = (Spinner) findViewById(R.id.sem2);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,level);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sem2.setAdapter(adapter2);
		sem2.setOnItemSelectedListener(this);
		Button down1 = (Button) findViewById(R.id.down1);
		Button down2 = (Button) findViewById(R.id.down2);
		inten = new Intent(Questions.this, CoursePage.class);
Button feed = (Button) findViewById(R.id.feedbakc);
		
        feed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 
		      btnFeedbackOnClick(v);  
				
			}
		});
		
	/*	Bundle extra = getIntent().getExtras();
		String var1=extra.getString("spinner1");
		String var2=extra.getString("spinner11");
		
		if(extra!=null){
			if(var1.equals("Computer engineering")){
				if(var2.equals("200")){
					
				
				}
				else if(var2.equals("300")){
					
					
					
				}
				
				
			}
			if(var1.equals("electrical engineering")){
				if(var2.equals("200")){
					
				}
				if(var2.equals("300")){
					
				}
			}
		  }
		*/
		down1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
               if(isConnected()){
					download(url);
		        }
				else{
					final AlertDialog.Builder b=new AlertDialog.Builder(Questions.this);
					   b.setIcon(R.drawable.star);
					b.setTitle("Connection Problem");
					b.setMessage("Unable to download File, Check your Internet connections " );
					b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					}).show();
				}
				
			}
		});
		
		down2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isConnected()){
					download(url);
		        }
				else{
					final AlertDialog.Builder b=new AlertDialog.Builder(Questions.this);
					   b.setIcon(R.drawable.star);
					b.setTitle("Connection Problem");
					b.setMessage("Unable to download File, Check your Internet connections " );
					b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					}).show();
				}
				
			}
		});
	}
	public void btnFeedbackOnClick(View v) {
	    final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
	    _Intent.setType("text/html");
	    _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
	    _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
	    _Intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
	    startActivity(Intent.createChooser(_Intent, getString(R.string.title_send_feedback)));
	}
	
	public void download(String url){
		DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
		request.setTitle("file download");
		request.setDescription("file is downloading...");
		request.allowScanningByMediaScanner();
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		
		String nameoffile = URLUtil.guessFileName(url, null,
				         MimeTypeMap.getFileExtensionFromUrl(url));
		
		request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nameoffile);
		DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
		manager.enqueue(request);
	}
	
	public boolean isConnected(){
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()) 
    	    	return true;
    	    else
    	    	return false;	
    }

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Spinner spinner1=(Spinner)parent;
		Spinner spinner11=(Spinner)parent;
		if(spinner1.getId()== R.id.sem1){
			if(position==0){	
				url="https://www.dropbox.com/s/6crtnr6g4t30k71/CEF201-past-quetion.zip?dl=1";
				
			}
		   if(position == 1){
			   url="https://www.dropbox.com/s/2hnuj10qqmy2ls7/CEF203-past-questions.zip?dl=1";
		
			}
		   if(position == 2){
			   url="https://www.dropbox.com/s/wygqi1vrtscg0hw/CEF205-past-questions.zip?dl=1";
	
			}
		   if(position == 3){
			   url="https://www.dropbox.com/s/ft7z7sxrns9e33c/CEF207-past-questions.zip?dl=1";
				
			}
		   if(position == 4){
			   url="https://www.dropbox.com/s/71ky0xz4bwurvqn/CEF209-past-questions.zip?dl=1";
				
			}
		   if(position == 5){
			   url="https://www.dropbox.com/s/g4l29vuona9yb91/ENG101-past-questions.zip?dl=1";
				
			}
		}
		
		if(spinner11.getId()==R.id.sem2){
			if(position==0){
				url="https://www.dropbox.com/s/b5dep4j0we11g08/CEF202-past-questions.zip?dl=1";
			}
			if(position==1){
				url="https://www.dropbox.com/s/fx314rlbneorpa4/CEF208-past-questions.zip?dl=1";
			}
			 if(position==2){
				 url="https://www.dropbox.com/s/1lozu8f9gzmp2xk/CVE100-past-questions.zip?dl=1";
			 }
			 
			 if(position==3){
					url="https://www.dropbox.com/s/u9avhj8ruz7x870/ENG102-past-questions.zip?dl=0";
				}
			
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
