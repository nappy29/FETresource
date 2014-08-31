package com.example.fetapp;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CoursePage extends Activity {
	private ArrayList<String> li,li1;
	private ListView list1, list2;
	TextView text;
	
	private String[] year1sem1com={"Analysis(CEF201)", "Basic electronics(CEF217)", "Boolean Algebra(CEF211)", "Linear algebra(CEF203)",
			                "Discrete maths(CEF209)", "Introduction to computing(CEF205)","C programming(CEF208)", "Use of english(ENG201)"};
	
	private String[] status1 = new String[9];
	private String[] status2 = new String[9];
	private String[] cv1 = new String[9];
	private String[] cv2 = new String[9];
	
	
	private String[] year1sem2com={"Numerical Analysis(CEF206)", "Computer Achitecture(CEF202)", "Accounting(CEF316)", "IS(CEF208)", 
			               "electronics practicals(CEF214)", "Datastructures & agorithms(CEF212)","Circuit analysis(CEF210)","Civics and ethics(CVE100)", "Sports(SPT100)", };
	
	private String[] year2sem1com={"Probability(CEF301)", "Operating Systems(CEF311)","DBMS(CEF309)", "Logic control & practice(CEF312)", "Sequence control(CEF315)",
                                "Digital electroniccs(CEF313)", "Systems programming(CEF305)", "Computer Networks and Protocols(CEF307)", "French(FRE101)"};
	
	private String[] year2sem2com={ "Digital electronics II(CEF312)", "Internet programming(CEF308)","System Engineering(CEF316)",
			                    "Object oriented programming(CEF306)", "Law(CEF314)","Digital signal processing(CEF310)","Sequential control lab(EEF312)", "Postgresql(CEF304)",
			                    "French(FRE102)"}; 
	
	private String[] year1semelec={""};
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coursepage);
		
		
		Arrays.fill(cv1, 0, 7, "4");
		cv1[7]=cv1[8]="2";
		Arrays.fill(cv2, 0, 8, "4");
		cv2[8]="2";
		Arrays.fill(status2, 0, 8, "compulsory");
		Arrays.fill(status2,7,9,"Ub requirement");
		Arrays.fill(status1, 0,8,"compulsory");
		status1[8]="UB requirment";
		text = (TextView)findViewById(R.id.title1);
		
		Bundle extra = getIntent().getExtras();
		String var1=extra.getString("spinner1");
		String var2=extra.getString("spinner11");
		 if(extra!=null){
		if(var1.equals("Computer engineering")){
			if(var2.equals("200")){
				text.append(" Computer Engineering Level 200");
				carryOn(year1sem1com,year1sem2com);
			
			}
			else if(var2.equals("300")){
				text.append(" Computer Engineering Level 300");
				carryOn(year2sem1com,year2sem2com);
				
			}
			
			
		}
		if(var1.equals("electrical engineering")){
			if(var2.equals("200")){
				
			}
			if(var2.equals("300")){
				
			}
		}
	  }	
	}

	
	public void carryOn(String[] course, String[] course1){
		li = new ArrayList<String>();
		li1=new ArrayList<String>();
		list1 = (ListView) findViewById(R.id.listcourses1);
		list2 = (ListView) findViewById(R.id.listcourses2);
	    for (int i = 0; i < course.length; ++i) {
	      li.add(course[i]);
	    }

	    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        R.layout.mylist, li);
	    list1.setAdapter(adapter);
	    list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final String title = list1.getItemAtPosition(position).toString();
				   final AlertDialog.Builder b=new AlertDialog.Builder(CoursePage.this);
				   b.setIcon(R.drawable.star);
				b.setTitle(list1.getItemAtPosition(position).toString());
				b.setMessage("Credit value: " + cv1[position] + "\n" + "Status: " + status1[position]  + "\n" + "Recommended Book: " + "\n" + "Pending..");
				b.setPositiveButton("seen", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Toast.makeText(CoursePage.this, title,
						Toast.LENGTH_SHORT).show();

					}
				}).show();
				return false;
			}
		});
	    
	    for (int i = 0; i < course1.length; ++i) {
		      li1.add(course1[i]);
		    }
	     
		    final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
		        R.layout.mylist, li1);
		    list2.setAdapter(adapter1);
		    list2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					final String title = list2.getItemAtPosition(position).toString();
					final AlertDialog.Builder b=new AlertDialog.Builder(CoursePage.this);
					b.setIcon(R.drawable.star);
					b.setTitle(title);
					b.setMessage("Credit value: " + cv2[position] + "\n" + "Status: " + status2[position] +"\n"+ "Recommended book: " + "\n" + "Pending..");
					b.setPositiveButton("seen", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							Toast.makeText(CoursePage.this, title,
							Toast.LENGTH_SHORT).show();

						}
					}).show();
					return true;
				}
			});  
	    
	   
		
	}
}
