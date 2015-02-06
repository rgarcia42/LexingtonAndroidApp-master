package com.capstone.Lexington;

import java.util.ArrayList;

import com.capstone.Lexington.ImageFragment.ImageFragmentCallBack;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Bitmap> imageIDs;
    private ImageFragmentCallBack mCallBack; 
    
    private ArrayList<String> names;
	private ArrayList<String> desc;
	private ArrayList<String> files;
	
    // constructor
    public ImageAdapter(Context c, ArrayList<Bitmap> scaledImages, ImageFragmentCallBack getCallBack, ArrayList<String> getNames, ArrayList<String> getDesc, ArrayList<String> getFiles)
    {
        context = c;
        imageIDs = scaledImages;
        mCallBack = getCallBack;
        names = getNames;
        desc = getDesc;
        files = getFiles;
    }
    
    public int getCount()
    {
        return imageIDs.size();
    }
    
    public Object getItem(int position) 
    {
        return position;
    }
    
    public long getItemId(int position)
    {
        return position;
    }

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView imageView;	
		
		if (convertView == null) {
		
			imageView = new ImageView(context);
			imageView.setPadding(5, 5, 5, 5);
			
			convertView = imageView;
			
		} else {
			
			imageView = (ImageView) convertView;
		}

		imageView.setImageBitmap(imageIDs.get(position));
		imageView.setTag((int)position);
		
		imageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				int index = (Integer)v.getTag();
				mCallBack.getSelectedImageRow(names.get(index), desc.get(index), files.get(index));
			}
		});
		
		return imageView;
	}
}