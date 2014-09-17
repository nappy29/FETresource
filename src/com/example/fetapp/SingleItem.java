package com.example.fetapp;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusOneButton;
import com.google.android.gms.plus.PlusShare;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SingleItem extends Activity{
	private String URL;
	private PlusOneButton mPlusOneButton;
	private boolean mIntentInProgress;
	private GoogleApiClient mGoogleApiClient;
	private static final int RC_SIGN_IN = 0;
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);    	
    	setContentView(R.layout.singleitem);
//		mPlusOneButton = (PlusOneButton) findViewById(R.id.plus_one_button11);
//		URL = "https://play.google.com/store/apps/details?id=com.example.fetapp";
//		ImageButton mShareButton = (ImageButton) findViewById(R.id.share_button);
//		Button feedbak= (Button) findViewById(R.id.feedbak);        
    	Intent in = getIntent();
    	
		String var1=in.getStringExtra("title");
		String var2=in.getStringExtra("content");
		
		TextView tit = (TextView)findViewById(R.id.tit);
    	TextView con = (TextView)findViewById(R.id.cont);		
		Log.d("var1", var2);
		tit.setText(var1);
		con.setText(var2);
		
//		mGoogleApiClient = new GoogleApiClient.Builder(this)
//        .addConnectionCallbacks(this)
//        .addOnConnectionFailedListener(this)
//        .addApi(Plus.API)
//        .addScope(Plus.SCOPE_PLUS_LOGIN)
//        .build();
    }
    
    /*
    
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
    
    public void btnFeedbackOnClick(View v) {
	    final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
	    _Intent.setType("text/html");
	    _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
	    _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
	    _Intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
	    startActivity(Intent.createChooser(_Intent, getString(R.string.title_send_feedback)));
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
	}  */
}
