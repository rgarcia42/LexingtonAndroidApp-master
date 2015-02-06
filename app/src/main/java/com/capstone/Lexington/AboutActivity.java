package com.capstone.Lexington;

import android.app.Activity;
import android.os.Bundle;

public class AboutActivity extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_about);
    getActionBar().setDisplayShowHomeEnabled(false);
	getActionBar().setDisplayHomeAsUpEnabled(false);
	getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
  }
}