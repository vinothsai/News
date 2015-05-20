package com.vy.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vy.news.R.menu;
import com.vy.news.adapter.CommentAdapter;
import com.vy.news.adapter.CommentDataBase;
import com.vy.news.adapter.LoginDataBaseAdapter;
import com.vy.news.model.CommentDrawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends Activity{
	SharedPreferences shareddata;
	Editor editor;
	String userName;
	TextView texttitle;
	TextView textdetails;
	TextView comment;
	EditText typecomment;
	String Title,Content;
	 CommentDataBase commentdatabase;
	 LoginDataBaseAdapter logindb;
	 ArrayList<CommentDrawer> cd;
	 CommentAdapter adapter;
	 Uri image;
ListView listcomment;
ImageView imageView1;
AlertDialogManager alert = new AlertDialogManager();
String pref;
//Bundle b=new Bundle();
//SessionManager sm=new SessionManager();
	List<String> comments=new ArrayList<String>();
	List<String> names=new ArrayList<String>();
	private String cmmt;
	private String namm;
	public int mState;
	public int State=0;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
		
		shareddata =getApplicationContext(). getSharedPreferences("MyPREFERENCES",MODE_PRIVATE);
		//s=shareddata.getString("USER_NAME", "def");		
		logindb=new LoginDataBaseAdapter(this);
		logindb=logindb.open();
		
		commentdatabase=new CommentDataBase(this);
		commentdatabase=commentdatabase.open();
		imageView1=(ImageView)findViewById(R.id.imageView1);
		texttitle=(TextView)findViewById(R.id.title);
		textdetails=(TextView)findViewById(R.id.details);
		comment=(TextView)findViewById(R.id.comment);
		
		typecomment=(EditText)findViewById(R.id.typecomment);
		listcomment=(ListView)findViewById(R.id.listView1);
		
		Intent intent = getIntent();
		 
		// Get the name
		Title = intent.getStringExtra("title");
		Content=intent.getStringExtra("details");
	//	image=intent.get("Img");
		// Locate the TextView in singleitemview.xml
		
 
		// Load the text into the TextView
		texttitle.setText(Title);
		textdetails.setText(Content);
		//Uri uri;
	//	imageView1.setImageURI(uri);
		
		pref=shareddata.getString("USER_NAME", ""); 
		if(!pref.equals(""))
		{
		mState = 1; // to hide or mState = 0; to show
		invalidateOptionsMenu();
		}
		else if(pref.equals(""))
		{
		mState = 0; // to hide or mState = 0; to show
		invalidateOptionsMenu();
		}
		
		
	
		cmmt=commentdatabase.getcmt(Title);
		System.out.println("!!!!!!!!!!!!!comment!!!!!!!!!!!"+cmmt);
		namm=commentdatabase.getname(Title);
		System.out.println("!!!!!!!!!!!!!name!!!!!!!!!!!"+namm);
		if(!cmmt.equals(""))
		{
			 names.add(namm);
				comments.add(cmmt);
				cd=new ArrayList<CommentDrawer>();
				for(int i=0;i<comments.size();i++)
					
				{
			cd.add(new CommentDrawer(names.get(i),comments.get(i)));
				}
				
				
				adapter=new CommentAdapter(getApplicationContext(), cd);
				listcomment.setAdapter(adapter);
				System.out.println("!!!!!!!!!!!!!comment!!!!!!!!!!!"+cmmt);
				//namm=commentdatabase.getname(Title);
				System.out.println("!!!!!!!!!!!!!name!!!!!!!!!!!"+namm);
		}
		
		
		comment.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
			//pref=shareddata.getString("USER_NAME", ""); // getting String
	
				if(pref.isEmpty())
    		{
			
					checkLogin();
				}
				else 
				{
					
					System.out.println("***********from shareddata******"+pref);
					System.out.println("      user position is login      ");
		    		
		    					//String ss=logindb.getSinlgeEntry(userName);
		    				
		    					String commd=typecomment.getText().toString();
		    					
		    					
		    					
		    					commentdatabase.insertEntry(Title,pref, commd);
		    					  names.add(pref);
		    					comments.add(commd);
		    				   
		    				  
		    			cd=new ArrayList<CommentDrawer>();
		    				for(int i=0;i<comments.size();i++)
		    					
		    				{
		    			cd.add(new CommentDrawer(names.get(i),comments.get(i)));
		    				}
		    				
		    				
		    				adapter=new CommentAdapter(getApplicationContext(), cd);
		    				listcomment.setAdapter(adapter);
		    				
		    			System.out.println("----comment------"+commd);
		    			System.out.println("-----namee in detail-------"+pref);	
		    			typecomment.setText("");
		
				
				}     
				
				}

		
		});
		
		
		
	
}
	

	public void checkLogin(){
	        // Check login status
	        if(!this.isLoggedIn()){
	        
	            // user is not logged in redirect him to Login Activity
	       	 alert.showAlertDialog(Detail.this, "Login status..", "Please do Login first for post any comments.....", false);
	       	 
	        	//Toast.makeText(getApplicationContext(), "Please do Login for Post any comments",Toast.LENGTH_SHORT ).show();	
				System.out.println("      logout position     ");
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+pref);
				
	            Intent i = new Intent(getApplicationContext(), Login.class);
	       
	            startActivity(i);
	            finish();
	          
	        }
	       
	        
	       
	        	
	        }
	        
	        
	    
	private boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return shareddata.getBoolean("isLoggedIn", false);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.mainmenu, menu);
	 if (mState == 1) //1 is true, 0 is false
	    {
	        //hide only option 2
	            menu.getItem(2).setVisible(false);
	            menu.getItem(4).setVisible(true);
	    }
	 else
	 {
		 menu.getItem(2).setVisible(true);
		 menu.getItem(4).setVisible(false);
	 }
	
	return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	// toggle nav drawer on selecting action bar app icon/title
	
	// Handle action bar actions click
	switch (item.getItemId()) {
	case R.id.Settings:
		return true;
	case R.id.Refresh:

		
		return true;
	case R.id.Login:
		
		
		Intent i=new Intent(getApplicationContext(),Login.class);
		startActivity(i);
		
		
		return true;	
		
		
	case R.id.Register:
		
		Intent intent=new Intent(getApplicationContext(),Register.class);
		
		startActivity(intent);
		
		return true;
	case R.id.Lgout:
		
		/*if(is_user_logged_in()==false)
		{
			item.setVisible(true);
		}
		item.setVisible(false);*/
	shareddata = getSharedPreferences("MyPREFERENCES",Context. MODE_PRIVATE); 
         SharedPreferences.Editor editor=shareddata.edit();
         
       
             editor.putString("USER_NAME", "");
             editor.putString("PWD", "");
             editor.commit();
             System.out.println("@@@@@@@@@@@@@logoutfunction@@@@@@@@@"+shareddata.getAll());
         	
          //   adapter=new CommentAdapter(getApplicationContext(), cd);
			//listcomment.setAdapter(adapter);
          finish();	
          System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+shareddata.getAll());
	return true;
	case R.id.Exit:
		
		 finish();
	     System.exit(0);
		return true;	
	default:
		return super.onOptionsItemSelected(item);
	}
	}
	@Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        commentdatabase.close();
       
    }
/*	private boolean is_user_logged_in() {
		// TODO Auto-generated method stub
		if(!pref.equals(""))
		{
			
			return true;
		}
		return false;
		
		
	}*/


}	
