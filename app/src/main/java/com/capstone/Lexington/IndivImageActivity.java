package com.capstone.Lexington;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IndivImageActivity extends Activity {
	
	String imageName;
	String imageDesc;
	String imageFile;
	
	ImageView imageView;
	ImageView expandView;
	
	TextView textDesc;
	TextView textName;
	
	LinearLayout expandLayout;
	LinearLayout imageLayout;
	
	Bitmap bitmapImage;
	
	GestureDetector gd;
	
	Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		setContentView(R.layout.activity_indiv_image);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		
		imageView = (ImageView) findViewById(R.id.indivImageView);
		textName = (TextView) findViewById(R.id.imageName);
		textDesc = (TextView) findViewById(R.id.indivImageText);
		expandLayout = (LinearLayout) findViewById(R.id.expandImageLayout);
		imageLayout = (LinearLayout) findViewById(R.id.indivImageLayout);
		
		// get image data
		Intent info = getIntent();
		imageName = info.getStringExtra("name");
		imageDesc = info.getStringExtra("desc");
		imageFile = info.getStringExtra("file");
		
		
		// set gesture detector
		gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
		{	
			@Override
            public boolean onDoubleTap(MotionEvent e) {
				
				Intent openZoomView = new Intent(context, ImageZoomActivity.class);
				openZoomView.putExtra("file", imageFile);
				startActivity(openZoomView);
				
				return true;
           }
		});
	
		// set image name
		textName.setText(imageName);
		
		// set image description
		textDesc.setText(imageDesc);
		
		// set imageView drawable
		String[] currFileSplit = imageFile.split("\\.");
		String fileName = currFileSplit[0];
		int getImgID = context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
		Drawable thumbImage = this.getResources().getDrawable(getImgID);
		imageView.setImageDrawable(thumbImage);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
			
		if(!gd.onTouchEvent(event))
		{
			return super.onTouchEvent(event);
		}
		
		return true;
	}
} 

