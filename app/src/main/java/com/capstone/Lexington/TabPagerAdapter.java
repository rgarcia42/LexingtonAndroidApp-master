package com.capstone.Lexington;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Raymond on 3/8/14.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
	
	ArrayList<Media> textList;
	ArrayList<Media> audioList;
	ArrayList<Media> videoList;
	ArrayList<Media> imageList;
	
	Bundle args;
	
	ArrayList<String> names;
	ArrayList<String> desc;
	ArrayList<String> files;
	
	// constructor
    public TabPagerAdapter(FragmentManager fm, ArrayList<Media> text, ArrayList<Media> audio, ArrayList<Media> video, ArrayList<Media> image)
    {
        super(fm);
        
        textList = text;
        audioList = audio;
        videoList = video;
        imageList = image;
    }

    public Fragment getItem(int index)
    {
        //use the tab switcher to select their respective fragments
    	Fragment current;
        switch(index)
        {
            case 0: // text fragment
            	current =  new InfoFragment();
            	
            	// get names and descriptions from textList
            	names = new ArrayList<String>();
            	desc = new ArrayList<String>();
            	
            	for(Media value : textList)
            	{
            		names.add(value.name);
            		desc.add(value.desc);
            	}
            	
            	// add data to bundle
            	args = new Bundle();
            	args.putStringArrayList("names", names);
            	args.putStringArrayList("desc", desc);
            	
            	// add bundle to fragment
            	current.setArguments(args);
            	
                return current;
            
            case 1: // audio fragment
            	current =  new AudioFragment();
            	
            	// get names from audioList
            	names = new ArrayList<String>();
            	desc = new ArrayList<String>();
            	files = new ArrayList<String>();
            	
            	for(Media value : audioList)
            	{
            		names.add(value.name);
            		desc.add(value.desc);
            		files.add(value.path);
            	}
            	
            	// add data to bundle
            	args = new Bundle();
            	args.putStringArrayList("names", names);
            	args.putStringArrayList("desc", desc);
            	args.putStringArrayList("files", files);
            	
            	// add bundle to fragment
            	current.setArguments(args);
            	
                return current;
            
            case 2: // video fragment
            	current =  new VideoFragment();
            	
            	// get names from audioList
            	names = new ArrayList<String>();
            	desc = new ArrayList<String>();
            	files = new ArrayList<String>();
            	
            	for(Media value : videoList)
            	{
            		names.add(value.name);
            		desc.add(value.desc);
            		files.add(value.path);
            	}
            	
            	// add data to bundle
            	args = new Bundle();
            	args.putStringArrayList("names", names);
            	args.putStringArrayList("desc", desc);
            	args.putStringArrayList("files", files);
            	
            	// add bundle to fragment
            	current.setArguments(args);
            	
                return current;
                
            case 3: // image fragment
            	current =  new ImageFragment();
            	
            	// get names from audioList
            	names = new ArrayList<String>();
            	desc = new ArrayList<String>();
            	files = new ArrayList<String>();
            	
            	for(Media value : imageList)
            	{
            		names.add(value.name);
            		desc.add(value.desc);
            		files.add(value.path);
            	}
            	
            	// add data to bundle
            	args = new Bundle();
            	args.putStringArrayList("names", names);
            	args.putStringArrayList("desc", desc);
            	args.putStringArrayList("files", files);
            	
            	// add bundle to fragment
            	current.setArguments(args);
            	
                return current;
        }

        return null;

    }

    public int getCount()
    {//sets the size of the tab list
        return 4;
    }
}
