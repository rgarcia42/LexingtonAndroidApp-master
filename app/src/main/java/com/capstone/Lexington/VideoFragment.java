package com.capstone.Lexington;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Raymond on 3/8/14.
 */

public class VideoFragment extends Fragment {
	
	ArrayList<String> names;
	ArrayList<String> desc;
	ArrayList<String> files;
	
	Activity activity;
	TableLayout table;
	
	VideoFragmentCallBack mCallBack;
	
	public interface VideoFragmentCallBack {
		public void getSelectedMediaRow(String name, String desc, String file, int type);
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		this.activity = activity;
		
		mCallBack = (VideoFragmentCallBack) activity;
	}
	
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState)
    {
    	ViewGroup root = (ViewGroup) i.inflate(R.layout.fragment_video, container, false);
    	
    	TableRow row = new TableRow(activity);
    	TextView label = new TextView(activity);
    	
    	names = getArguments().getStringArrayList("names");
    	desc = getArguments().getStringArrayList("desc");
    	files = getArguments().getStringArrayList("files");
    	
    	table = (TableLayout) root.findViewById(R.id.videoTable);
    	
    	LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
    	
    	int count = 0;
    	
    	// add rows to audio table layout
    	for(String value : names)
    	{	
    		row = new TableRow(activity);
    		row.setGravity(Gravity.CENTER);
    		row.setLayoutParams(rowParams);
    		row.setPadding(5, 40, 5, 40);
    		row.setTag(count);
    		
    		label = new TextView(activity);
    		label.setText(value);
    		label.setTextSize(20);
    		label.setGravity(Gravity.CENTER);
    		
    		row.addView(label);
    		
    		row.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					int index = (Integer)v.getTag();
					mCallBack.getSelectedMediaRow(names.get(index), desc.get(index), files.get(index), 2);
				}
    		});
    		
    		table.addView(row);
    		
    		count++;
    	}	
    	
		//inflate view
    	return root;
    }
}
