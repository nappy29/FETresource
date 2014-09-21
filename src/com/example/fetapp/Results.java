package com.example.fetapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends Activity {
	private EditText email, pass;
	private TextView error;
	private Intent inten;
   @Override
   public void onCreate(Bundle savedInstanceState){
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.result);
	     email = (EditText)findViewById(R.id.email);
	     pass  = (EditText)findViewById(R.id.pass);
	    error = (TextView) findViewById(R.id.error);
	     inten = new Intent(Results.this, ShowResults.class);
	   Button login = (Button)findViewById(R.id.login);
	   
	   login.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			new Login().execute();
	
		}
	});
   }
   
   @Override
	public void onBackPressed()
	{
	    startActivity(new Intent(getApplicationContext(), MainPage.class));
	    finish();
	}
     
   public class Login extends AsyncTask<Void, Void, Void>{
	   
	   private ProgressDialog pDialog;

	@Override
       protected void onPreExecute() {
           super.onPreExecute();
           // Show the progress in load
           pDialog = new ProgressDialog(Results.this);
           pDialog.setMessage("loggin in...");
           pDialog.setCancelable(false);
           pDialog.show();

       }

	@Override
	protected Void doInBackground(Void... params) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("https://ubresources.com/login.json");
        Log.d("Enter", "This thread is now working");
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("username", email.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("password", pass.getText().toString()));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);	 
	        String strResponse = "";
	        HttpEntity resEntity = response.getEntity();
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                resEntity.getContent(), "UTF-8"));
	        String line;
	        while (((line = br.readLine()) != null)) {
	            strResponse = strResponse + line + "\n";
	        }
	        
	        try {
				JSONObject resp = new JSONObject(strResponse);
				JSONObject user = resp.getJSONObject("user");
				String emailadd = user.getString("recovery_email");
				JSONObject profile = resp.getJSONObject("profile");
				String name = profile.getString("name");
				String level = profile.getString("level");
				Md5Hashing hash = new Md5Hashing();
				String hashed = hash.md5Hex(emailadd);
				
				inten.putExtra("results", strResponse);
				inten.putExtra("name", name);
				inten.putExtra("level", level);
				inten.putExtra("email", emailadd);
				inten.putExtra("hashed", hashed);
				
			} catch (JSONException e) {
					final AlertDialog.Builder b=new AlertDialog.Builder(Results.this);
					   b.setIcon(R.drawable.star);
					b.setTitle("Connection Problem");
					b.setMessage("Connection to server failed " );
					b.setNegativeButton("tryagain", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
                          new Login().execute();
                          
						}
					}).show();
				
				e.printStackTrace();
			}
	        
//			if(strResponse.equalsIgnoreCase("")){
//				error.setText("Invalid username or password");
//				startActivity(new Intent(Results.this, Results.class));
//			}
//			if(!strResponse.equalsIgnoreCase("")){
//				Md5Hashing hash = new Md5Hashing();
//				Log.d("hash", hash.md5Hex("ewaneenombe@yahoo.com"));
//				String hashed = hash.md5Hex(email.getText().toString());
//				inten.putExtra("hash", hashed);
//			}
	        Log.d("Get here first"," strResponse:  " + strResponse);
	        Log.d("Login", response.toString());
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
		return null;
	}
	
	 @Override
     protected void onPostExecute(Void nap) {
     	super.onPostExecute(null);
             if (pDialog.isShowing())
             pDialog.dismiss();     
         startActivity(inten);
         Toast.makeText(Results.this, "You are logged in", Toast.LENGTH_SHORT).show();
    }
	   
   }
}
