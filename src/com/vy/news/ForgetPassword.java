package com.vy.news;

import com.vy.news.adapter.LoginDataBaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPassword extends Activity {
	EditText username;
	Button submit,ok;
	TextView pswd;
	String name,password;
	LoginDataBaseAdapter lg;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.forgetpwd);
		
		lg=new LoginDataBaseAdapter(this);
		lg=lg.open();
		
		
username=(EditText)findViewById(R.id.editText1);
submit=(Button)findViewById(R.id.button1);
pswd=(TextView)findViewById(R.id.textView2);
ok=(Button)findViewById(R.id.button2);	
submit.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 name=username.getText().toString();
		password= lg.getSinlgeEntry(name);
		
		pswd.setText("Your Password is:"+password);
		
		
		
	}
});

ok.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//Intent i=new Intent(getApplicationContext(),Login.class);
		//startActivity(i);
		username.setText("");
		finish();
		
		
	}
});
}
}