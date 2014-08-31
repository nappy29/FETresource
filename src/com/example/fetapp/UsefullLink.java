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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UsefullLink extends Activity {

		
	ArrayList<HashMap<String, String>> articles = null;
	 ProgressDialog pDialog;
	 ListView list1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uselinks);
		articles = new ArrayList<HashMap<String,String>>();
		TextView cont = (TextView) findViewById(R.id.usecont);
		
		
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
}