package com.capstone.Lexington;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MapActivity extends Activity {
	
	private Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		
		ImageView location = (ImageView) findViewById(R.id.location_dot);
		RelativeLayout.LayoutParams parameters = (RelativeLayout.LayoutParams)location.getLayoutParams();
		
		i = getIntent();
		
		double moveX = i.getDoubleExtra("loc_x", 0);
		double moveY = i.getDoubleExtra("loc_y", 0);
		
		Toast.makeText(getApplicationContext(), "X: " + Double.toString(moveX) + " Y: " + Double.toString(moveY), Toast.LENGTH_LONG).show();
		
		parameters.setMargins((int)(moveX),(int)(moveY), 0, 0);


		location.setLayoutParams(parameters);
		
		
	}
	
}