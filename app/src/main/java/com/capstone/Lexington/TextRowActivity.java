package com.capstone.Lexington;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TextRowActivity extends Activity {

	TextView textName;
	TextView textDesc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		setContentView(R.layout.activity_text_row);
		
		textName = (TextView) findViewById(R.id.textName);
		textDesc = (TextView) findViewById(R.id.textDesc);
		
		Intent info = getIntent();
		textName.setText(info.getStringExtra("name"));
		textDesc.setText(info.getStringExtra("desc"));
	}
}
