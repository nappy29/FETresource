package com.example.fetapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusOneButton;
import com.google.android.gms.plus.PlusShare;

public class UsefullLink extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {

		
	ArrayList<HashMap<String, String>> articles = null;
	 ProgressDialog pDialog;
	 ListView list1;
	 private PlusOneButton mPlusOneButton;
	 private static final int RC_SIGN_IN = 0;
	    private GoogleApiClient mGoogleApiClient;
		private boolean mIntentInProgress;
	    private static final int PLUS_ONE_REQUEST_CODE = 0;
	    private String URL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uselinks);
		articles = new ArrayList<HashMap<String,String>>();
		mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(Plus.API)
        .addScope(Plus.SCOPE_PLUS_LOGIN)
        .build();
		TextView cont = (TextView) findViewById(R.id.usecont);
		Button feedbak = (Button)findViewById(R.id.feedback);
		URL = "https://play.google.com/store/apps/details?id=com.example.fetapp";
		ImageButton mShareButton = (ImageButton) findViewById(R.id.share_button1);
		mPlusOneButton = (PlusOneButton) findViewById(R.id.plus_one_button1);
        mShareButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			      PlusShare.Builder builder = new PlusShare.Builder(UsefullLink.this);

			      // Set call-to-action metadata.
			      builder.addCallToAction(
			          "INVITE", /** call-to-action button label */
			          Uri.parse("https://plus.google.com/"), 
			          "/pages/create");

			      // Set the content url (for desktop use).
			      builder.setContentUrl(Uri.parse("https://plus.google.com/pages/"));

			      // Set the target deep-link ID (for mobile use).
			      builder.setContentDeepLinkId("/pages/",
			              null, null, null);

			      // Set the share text.
			      builder.setText("Create your Google+ Page too!");

			      startActivityForResult(builder.getIntent(), 0);
			    
				
			}
		});
		feedbak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 
		      btnFeedbackOnClick(v);  
				
			}
		});
		
		
		if(!isConnected()){
			final AlertDialog.Builder b=new AlertDialog.Builder(UsefullLink.this);
			   b.setIcon(R.drawable.star);
			b.setTitle("Connection Problem");
			b.setMessage("Connection to server failed " );
			b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			}).show();
		}
		// call AsynTask to perform network operation on separate thread
		new HttpAsyncTask().execute("https://ubresources.com/useful-links.json");
	}
	

public void btnFeedbackOnClick(View v) {
    final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
    _Intent.setType("text/html");
    _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
    _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
    _Intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
    startActivity(Intent.createChooser(_Intent, getString(R.string.title_send_feedback)));
}  


	public static String GetContent(String url){
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Sorry, Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()) 
    	    	return true;
    	    else
    	    	return false;	
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
    	
    	@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(UsefullLink.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
    	
        @Override
        protected String doInBackground(String... urls) {

            return GetContent(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	

        	 super.onPostExecute(result);
             // Dismiss the progress dialog
             if (pDialog.isShowing())
                 pDialog.dismiss();
        	try {
        		
				//JSONObject data = new JSONObject(result);
				JSONArray dat = new JSONArray(result);
				for(int i=0; i<dat.length();i++){
					JSONObject obj = dat.getJSONObject(i);
					String title = obj.getString("title");
					String content = obj.getString("content");

					HashMap<String, String> map = new HashMap<String, String>();
					map.put("title", title);
					map.put("content", content);
					
					articles.add(map);
				}
				//etResponse.setText(data.toString(1));
			} catch (JSONException e) {
				final AlertDialog.Builder b=new AlertDialog.Builder(UsefullLink.this);
				   b.setIcon(R.drawable.star);
				b.setTitle("Connection Problem");
				b.setMessage("Unable to pull data from server " );
				b.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						new HttpAsyncTask().execute("https://ubresources.com/useful-links.json");
					}
				});
				b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).show();
			}
        	
        	SimpleAdapter adapter = new SimpleAdapter(
                    UsefullLink.this, articles,
                    R.layout.uselist, new String[] { "title", "content"}, new int[] { R.id.usetit,
                            R.id.usecont});
        	 list1 = (ListView)findViewById(R.id.uselist);
        	list1.setAdapter(adapter);
        	
        	Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
        	
       }
    }
    
	protected void onStart() {
	    super.onStart();
	    mGoogleApiClient.connect();
	  }

	  protected void onStop() {
	    super.onStop();

	    if (mGoogleApiClient.isConnected()) {
	      mGoogleApiClient.disconnect();
	    }
	  }
	  
	  protected void onResume() {
		    super.onResume();
		    // Refresh the state of the +1 button each time the activity receives focus.
		    mPlusOneButton.initialize(URL, PLUS_ONE_REQUEST_CODE);
		}
    
    @Override
	public void onConnectionFailed(ConnectionResult result) {
		if (!mIntentInProgress && result.hasResolution()) {
		    try {
		      mIntentInProgress = true;
		      startIntentSenderForResult(result.getResolution().getIntentSender(),
		          RC_SIGN_IN, null, 0, 0, 0);
		    } catch (SendIntentException e) {
		      
		      mIntentInProgress = false;
		      mGoogleApiClient.connect();
		    }
		  }
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
      		
	}

	@Override
	public void onConnectionSuspended(int cause) {
		 mGoogleApiClient.connect();		
	}
}