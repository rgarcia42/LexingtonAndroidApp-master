package com.capstone.Lexington;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class ImageZoomActivity extends Activity {

	String imageFile;
	LinearLayout imageLayout;
	Bitmap bitmapImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_image_zoom);
		
		Intent info = getIntent();
		imageFile = info.getStringExtra("file");
		
		imageLayout = (LinearLayout) findViewById(R.id.zoomImageLayout);
		
		String[] currFileSplit = imageFile.split("\\.");
		String fileName = currFileSplit[0];
		Log.e("image name", fileName);
		int getImgID = this.getResources().getIdentifier(fileName, "drawable", "com.capstone.Lexington");
		
		// create bitmap for zoomable view
		bitmapImage = BitmapFactory.decodeResource(getResources(), getImgID);
		
		ZoomableImageView zoomView = new ZoomableImageView(this);
		zoomView.setImageBitmap(bitmapImage);
		
		imageLayout.addView(zoomView);
	}

}
