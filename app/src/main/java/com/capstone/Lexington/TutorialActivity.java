package com.capstone.Lexington;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TutorialActivity extends Activity implements OnClickListener
{
	Button scanButton;
	
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_tutorial);
    
    scanButton = (Button) findViewById(R.id.scan_button);
    
    scanButton.setOnClickListener(this);    
  }

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	Intent scan = new Intent(this, ScanActivity.class);
	startActivity(scan);
}

  
  


}