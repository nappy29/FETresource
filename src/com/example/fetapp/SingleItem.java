package com.example.fetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SingleItem extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);    	
    	setContentView(R.layout.singleitem);
    	Intent in = getIntent();
    	
		String var1=in.getStringExtra("title");
		String var2=in.getStringExtra("content");
		
		TextView tit = (TextView)findViewById(R.id.tit);
    	TextView con = (TextView)findViewById(R.id.cont);
		
		Log.d("var1", var2);
		tit.setText(var1);
		con.setText(var2);
    }
}
