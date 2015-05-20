package com.vy.news;

import com.vy.news.adapter.LoginDataBaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity{
	EditText name;
	EditText email;
	EditText username;
	EditText password;
	EditText confirmpassword;
	Button register;
	Button backtologin;
	LoginDataBaseAdapter loginDataBaseAdapter;
	 AlertDialogManager alert = new AlertDialogManager();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		
		  loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	        loginDataBaseAdapter=loginDataBaseAdapter.open();
	        
		name=(EditText)findViewById(R.id.editText1);
		email=(EditText)findViewById(R.id.editText2);
		username=(EditText)findViewById(R.id.editText3);
		password=(EditText)findViewById(R.id.editText4);
		confirmpassword=(EditText)findViewById(R.id.editText5);
		
		register=(Button)findViewById(R.id.button1);
		backtologin=(Button)findViewById(R.id.button2);
		
		
		
		
		
		//when click register button
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Name=name.getText().toString();
				String Email=email.getText().toString();
				String UserName=username.getText().toString();
				String PassWord=password.getText().toString();
				String confpwd=confirmpassword.getText().toString();
				 // check if any of the fields are vaccant
	            if(UserName.equals("")||PassWord.equals("")||confpwd.equals(""))
	            {
	                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
	                    return;
	            }
	            // check if both password matches
	            if(!PassWord.equals(confpwd))
	            {
	            	// alert.showAlertDialog(Register.this, "Login Successfull..", "Username and Password is correct", false);
	                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
	                return;
	            }
	            else
	            {
	                // Save the Data in Database
	                loginDataBaseAdapter.insertEntry(Name,Email,UserName, PassWord,confpwd);
	                alert.showAlertDialog(Register.this, "Registeration..", "Account Successfully Created", true);
	                // Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
	               // Intent i=new Intent(getApplicationContext(),Login.class);
	                //startActivity(i);
	                finish();
	            }
				
			}
		});
		
		//when click backtologin button
		backtologin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),Login.class);
				startActivity(i);
				
			}
		});
		
	}
	 @Override
	    protected void onDestroy() {
	        // TODO Auto-generated method stub
	        super.onDestroy();
	 
	        loginDataBaseAdapter.close();
	    }
}
