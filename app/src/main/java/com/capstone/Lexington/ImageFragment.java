package com.capstone.Lexington;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by Andrew on 5/5/2014.
 */

public class ImageFragment extends Fragment {
	
	GridView gridView;
	TextView textProgress;
	ProgressBar imageProgress;
	Activity activity;
	ArrayList<String> names;
	ArrayList<String> desc;
	ArrayList<String> files;
	TableLayout table;
	
	ImageFragmentCallBack mCallBack;
	public interface ImageFragmentCallBack {
		public void getSelectedImageRow(String name, String desc, String file);
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		this.activity = activity;
		
		mCallBack = (ImageFragmentCallBack) activity;
	}
	
	@SuppressWarnings("unchecked")
	public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
		
		ViewGroup root = (ViewGroup) i.inflate(R.layout.fragment_images, container, false);
		gridView = (GridView) root.findViewById(R.id.gridView);
		
       	names = getArguments().getStringArrayList("names");
    	desc = getArguments().getStringArrayList("desc");
    	files = getArguments().getStringArrayList("files");
    	
    	popGallery task = new popGallery(root, names, desc, files);
       	
		task.execute(files);

		// inflate view
		return root;
	}

	public class popGallery extends AsyncTask<ArrayList<String>, Integer, Void> {
    	
		ArrayList<Bitmap> scaledImages = new ArrayList<Bitmap>();
		
    	ArrayList<String> names;
    	ArrayList<String> desc;
    	ArrayList<String> files;
    	ViewGroup context;
    	
    	public popGallery(ViewGroup root, ArrayList<String> getNames, ArrayList<String> getDesc, ArrayList<String> getFiles)
    	{
    		context = root;
    		names = getNames;
    		desc = getDesc;
    		files = getFiles;
    	}
    	
    	@Override
		protected void onPreExecute() {
    		
    		imageProgress = (ProgressBar) context.findViewById(R.id.imageProgressBar);
    		textProgress = (TextView) context.findViewById(R.id.progressText);
    		
    		imageProgress.setVisibility(View.VISIBLE);
    		textProgress.setVisibility(View.VISIBLE);
    		
			super.onPreExecute();
		}

    	protected void onProgressUpdate(Integer... progress) {    
    		
			imageProgress.setProgress(progress[0]);
		}
    	
		@Override
		protected Void doInBackground(ArrayList<String>... params) {
		
			int size = names.size();
			
			for(int i=0; i < size; i++)
			{	
				String[] currFileSplit = files.get(i).split("\\.");
				String fileName = currFileSplit[0];
				
				Log.i("fuuuuuu", fileName);
				
				// get preview image id from resources
			    int getImgID = context.getResources().getIdentifier(fileName, "drawable", context.getContext().getPackageName());
				Bitmap image = BitmapFactory.decodeResource(context.getResources(), getImgID);
				
				if(image != null)
				{
					Bitmap newImage = Bitmap.createScaledBitmap(image, 300, 300, false);
					scaledImages.add(newImage);
				}
				
				publishProgress((int) ((i / (float) size) * 100));
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			super.onPostExecute(result);
			
			gridView.setAdapter(new ImageAdapter(getActivity(), scaledImages, mCallBack, names, desc, files));
			
			imageProgress.setVisibility(View.GONE);
			textProgress.setVisibility(View.GONE);
		}

    } // end asynctask
}
