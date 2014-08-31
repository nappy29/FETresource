package com.example.fetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class QuestionClass extends Activity implements OnItemSelectedListener{
	
	private Spinner spinner1, spinner11;
	private String[] department = {"Computer engineering", "electrical engineering"};
	private String[] level ={"200", "300"};
	String url;
	private ArrayAdapter<String> adapter1;
	private Intent inten;
	
	@Override
	public void onCreate(Bundle savedInstaceState){
		super.onCreate(savedInstaceState);
		
		setContentView(R.layout.question);
		
		
		
		spinner1= (Spinner) findViewById(R.id.numba1);
		adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,department);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);
		spinner1.setOnItemSelectedListener(this);
		
		spinner11 = (Spinner) findViewById(R.id.numba2);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,level);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner11.setAdapter(adapter2);
		spinner11.setOnItemSelectedListener(this);
		Button next = (Button) findViewById(R.id.next);
		inten = new Intent(QuestionClass.this, Questions.class);
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(inten);
				
			}
		});
	}
	
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Spinner spinner1=(Spinner)parent;
		Spinner spinner11=(Spinner)parent;
		if(spinner1.getId()== R.id.numba1){

			if(position==0){			
				inten.putExtra("spinner1", spinner1.getSelectedItem().toString());
				Toast.makeText(this, "Computer selected", Toast.LENGTH_SHORT).show();

			}
		   if(position == 1){
				inten.putExtra("spinner1", spinner1.getSelectedItem().toString());
				Toast.makeText(this, "Electrical selected", Toast.LENGTH_SHORT).show();

			}
		}
		
		if(spinner11.getId()==R.id.numba2){
			if(position==0){
				inten.putExtra("spinner11", spinner11.getSelectedItem().toString());
			}
			 if(position==1){
				 inten.putExtra("spinner11", spinner11.getSelectedItem().toString());
			 }
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}

