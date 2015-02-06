package com.capstone.Lexington;

import java.util.List;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class ScanActivity extends Activity {
	
	private Camera mCamera;
	private ScanPreview mPreview;
	private FrameLayout preview;

	ImageScanner scanner;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_scanner);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mCamera = getCameraInstance();

		// Instance barcode scanner
		scanner = new ImageScanner();
		scanner.setConfig(0, Config.X_DENSITY, 3);
		scanner.setConfig(0, Config.Y_DENSITY, 3);

		mPreview = new ScanPreview(this, mCamera, previewCb, autoFocusCB);
		preview = (FrameLayout) findViewById(R.id.cameraPreview);
		preview.addView(mPreview);
		mCamera.setPreviewCallback(previewCb);
		mCamera.startPreview();
		mCamera.autoFocus(autoFocusCB);
	}
	
	// Mimic continuous auto-focusing
	AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera camera) {
		}
	};
	
	
	public void onResume()
	{ 		
	    super.onResume();
	    try
	    {
	        mCamera = getCameraInstance();
	        mCamera.setPreviewCallback(null);
	        mPreview = new ScanPreview(this, mCamera, previewCb, autoFocusCB);
	        mCamera.setPreviewCallback(previewCb);
	        preview.addView(mPreview);
	    } catch (Exception e){
            Log.d("Lex", "failed to get camera");
	    }
	}   
	
	
	public void onDestroy()
	{
		super.onDestroy();
		releaseCamera();
	}
	
	public void onPause() {
	    super.onPause();

	    releaseCamera();
	}
	
	
	
	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		
		Camera c = null;
		
		try {
			c = Camera.open();
		} catch (Exception e) {
			Log.d("DBG", "Error getting camera: " + e.getMessage());
		}
		
		try{
            Camera.Parameters parameters = c.getParameters();
            parameters.setPictureFormat(PixelFormat.JPEG);
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            parameters.setFlashMode("auto");
            List<int[]> fps = parameters.getSupportedPreviewFpsRange();

            if(fps != null)
            {
                parameters.setPreviewFpsRange(fps.get(fps.size()-1)[1], fps.get(fps.size()-1)[1]);
            }

            c.setParameters(parameters);}
        catch(NullPointerException nullP){
            Log.d("Lex", "null camera parameters");
        }
		return c;
	}

	private void releaseCamera() {
		if (mCamera != null) {
			mPreview.getHolder().removeCallback(mPreview);
			mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera = null;
		}
	}

	

	PreviewCallback previewCb = new PreviewCallback() {
		public void onPreviewFrame(byte[] data, Camera camera) {
			Camera.Parameters parameters = camera.getParameters();
			Size size = parameters.getPreviewSize();

			Image barcode = new Image(size.width, size.height, "Y800");
			barcode.setData(data);

			int result = scanner.scanImage(barcode);

			if (result != 0) {
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();

				SymbolSet syms = scanner.getResults();
				for (Symbol sym : syms) {
					Intent i = new Intent(ScanActivity.this, MainActivity.class);
					i.putExtra("result",sym.getData());
					startActivity(i);
				}
			}
		}
	};
} // end class