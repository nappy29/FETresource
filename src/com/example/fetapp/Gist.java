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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Gist extends Activity {
	
	ArrayList<HashMap<String, String>> articles = null;
	 ProgressDialog pDialog;
	private ListView list1;
	TextView text;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gist);
		text = (TextView)findViewById(R.id.gistcontent);
		articles = new ArrayList<HashMap<String,String>>();
		list1 = (ListView)findViewById(R.id.list);
		Button feedbak= (Button) findViewById(R.id.feedbak);
    feedbak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 
		      btnFeedbackOnClick(v);  
				
			}
		});
		
		
		list1.setOnItemClickListener(new OnItemClickListener() {
			 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				 String title = ((TextView) view.findViewById(R.id.gisttitle))
                        .getText().toString();
                String content = ((TextView) view.findViewById(R.id.gistcontent))
                        .getText().toString();
                String description = ((TextView) view.findViewById(R.id.giston))
                        .getText().toString();
				
                Intent in = new Intent(getApplicationContext(),
                        SingleItem.class);
                in.putExtra("title", title);
                in.putExtra("content", content);
                in.putExtra("updated_at", description);
                startActivity(in);
			}
        });
        
		
		if(!isConnected()){
			final AlertDialog.Builder b=new AlertDialog.Builder(Gist.this);
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
		new HttpAsyncTask().execute("https://ubresources.com/gist.json");
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
            pDialog = new ProgressDialog(Gist.this);
            pDialog.setMessage("Getting data...");
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
					String content =Html.fromHtml(obj.getString("content")).toString();
					String cont = content.substring(0, 150);
					String giston = obj.getString("updated_at");
					
					
				
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("title", title);
					map.put("content", content);
					map.put("update_at", giston);
					articles.add(map);
				}
				//etResponse.setText(data.toString(1));
			} catch (JSONException e) {
				final AlertDialog.Builder b=new AlertDialog.Builder(Gist.this);
				   b.setIcon(R.drawable.star);
				b.setTitle("Connection Problem");
				b.setMessage("Unable to pull data from server " );
				b.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						new HttpAsyncTask().execute("https://ubresources.com/gist.json");
					}
				});
				b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).show();

			}
        	
        	SimpleAdapter adapter = new SimpleAdapter(
                    Gist.this, articles,
                    R.layout.gistlist, new String[] { "title", "content","updated_at"}, new int[] { R.id.gisttitle,
                            R.id.gistcontent, R.id.giston});
        	 //list1 = (ListView)findViewById(R.id.list);
        	list1.setAdapter(adapter);
        	
       }
    }
}