package com.vy.news;

import com.vy.news.adapter.CommentDataBase;
import com.vy.news.adapter.LoginDataBaseAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{
	
	SharedPreferences shareddata;
	Editor editor;
	EditText username;
	EditText password;
	Button signin;
	Button cancel;
	Button register;
	Button forget;
	String comment;
	Bundle bundle;
	CommentDataBase cb;
	 LoginDataBaseAdapter loginDataBaseAdapter;
	 AlertDialogManager alert = new AlertDialogManager();
	  Bundle b = new Bundle();
	  String name;
	  String pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		shareddata = getApplicationContext().getSharedPreferences("MyPREFERENCES",MODE_PRIVATE);
		
		    //Editor editor = pref.edit();
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        
        
        
        cb=new CommentDataBase(this);
        cb=cb.open();
        
        
        editor=shareddata.edit();
        editor.clear();
        editor.commit();
        
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		
		//comment=bundle.getString("Name");
		
		signin=(Button)findViewById(R.id.signinbutton);
		cancel=(Button)findViewById(R.id.cancelbutton);
		register=(Button)findViewById(R.id.registerbutton);
		forget=(Button)findViewById(R.id.passwordbutton);
		
		//when click signin button action
		signin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				name=username.getText().toString();
				
				
				pwd=password.getText().toString();
				 // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(name);
             
                // check if the Stored password matches with  Password entered by user
                if(pwd.equals(storedPassword))
                {
                	
                	 alert.showAlertDialog(Login.this, "Login Successfull..", "Username and Password is correct", true);
                   // Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                 
                    System.out.println("&&&&&&&&&&& login user name &&&&&&&&"+name);
                    
                  
                 
                    editor.putString("USER_NAME", name);
               //     editor.putInt("PWD",1);
                    editor.commit();
                   
                    System.out.println("@@@@@@@@@@@@@logoutfunction@@@@@@@@@"+shareddata.getAll());
                    finish();
                   
            		
                    
                }
                else
                {
                	 alert.showAlertDialog(Login.this, "Login failled..", "Username/Password is incorrect", false);
                   Toast.makeText(Login.this, "If you don't know your password click on forgetmy password....", Toast.LENGTH_LONG).show();
                }
                username.setText("");
                password.setText("");
                System.out.println("&&&&&&&&&&& login user name &&&&&&&&"+name);
           
			}
			
		});
		
		//when click cancel button action
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username.setText("");
				password.setText("");
			}
		});
		//when click register button action
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),Register.class);
				startActivity(i);
				
			}
		});
		// when click forget password button
		forget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intt=new Intent(getApplicationContext(),ForgetPassword.class);
				startActivity(intt);
				
			}
		});
}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
       
    }

}
