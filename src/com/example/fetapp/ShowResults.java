package com.example.fetapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowResults extends Activity {
	
	private RelativeLayout rela;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finalresults);
		rela =(RelativeLayout) findViewById(R.id.relay);
		WebView pic = (WebView)findViewById(R.id.webimage);
		pic.setWebViewClient(new MyWebViewClient());
		TextView name = (TextView)findViewById(R.id.usern);
		TextView mail = (TextView)findViewById(R.id.email);
		TextView leve = (TextView)findViewById(R.id.leve);
		Bundle extra = getIntent().getExtras();

		String username = extra.getString("name");
		String email = extra.getString("email");
		String level = extra.getString("level");
		String hashed = extra.getString("hashed");
	    String ur= "http://www.gravatar.com/avatar/";
	    String URL = ur + hashed + "?s=200";
	    String results = extra.getString("results");
	    Log.d("Results", results);
	    
	    if(!isConnected()){
			final AlertDialog.Builder b=new AlertDialog.Builder(ShowResults.this);
			   b.setIcon(R.drawable.star);
			b.setTitle("Connection Problem");
			b.setMessage("Connection to server failed " );
			b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			}).show();
		}
	    
		new Result().execute(results);
	    pic.loadUrl(URL);
		if(extra != null){
		name.append(username);
		mail.append(email);
		leve.append(level);
		}
		
	}
	
	public boolean isConnected(){
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()) 
    	    	return true;
    	    else
    	    	return false;	
    }
	
	public void logout(View v){
		startActivity(new Intent(getApplicationContext(), MainPage.class));
		Toast.makeText(ShowResults.this, "You are looged out", Toast.LENGTH_SHORT).show();
	}
	
	private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
	
	private class Result extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... results) {
			
			return results[0];
		}
		
		  @Override
	        protected void onPostExecute(final String result) {
			  super.onPostExecute(result);
			  try {
				  LinearLayout lL = (LinearLayout)findViewById(R.id.linre);
				
					JSONObject myresults = new JSONObject(result);
					JSONArray arra = myresults.getJSONArray("results");
					Log.d("executing", arra.toString());
					if(arra.length()==0){
                       TextView valueTV;
                       ScrollView scroll4results = (ScrollView)findViewById(R.id.scroll4results);
                       android.widget.RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                       lp.addRule(RelativeLayout.BELOW, R.id.leve);
						//add textView
						valueTV = new TextView(ShowResults.this);
						lp.setMargins(0, 35, 0, 0);
						valueTV.setBackgroundColor(Color.parseColor("#15BFA9"));
						valueTV.setText("There are No Results Currently, Please check again Later");
						valueTV.setId(5);
						rela.removeView(scroll4results);
						rela.addView(valueTV,lp);
						
					}
					if(arra.length()!=0){
						for(int i = 0; i<arra.length(); i++){
														
							TextView valueTV = new TextView(ShowResults.this);
							valueTV.setId(i);
							valueTV.setTextColor(Color.WHITE);
							android.widget.RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
							lp.addRule(RelativeLayout.BELOW, i-1);
							//lp.setMargins(0, 7, 0, 0);
						//valueTV.setBackgroundColor(Color.parseColor("#15BFA9"));
							JSONObject obj = arra.getJSONObject(i);
							valueTV.setText(obj.getString("course_id") +  "    " +  "    " +  obj.getInt("ca") +  "   " +  "    " + obj.getInt("exam") + "   " +
									"    " + obj.getString("grade"));
							Log.d("grade", obj.getString("grade"));
							lL.addView(valueTV, lp);
						}
						//rela.addView(lL);
					}
				} catch (JSONException e) {
					final AlertDialog.Builder b=new AlertDialog.Builder(ShowResults.this);
					   b.setIcon(R.drawable.star);
					b.setTitle("Connection Problem");
					b.setMessage("Unable to pull data from server " );
					b.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							new Result().execute(result);
						}
					});
					b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					}).show();
				}
				
				
			}
		  }
		
	}
