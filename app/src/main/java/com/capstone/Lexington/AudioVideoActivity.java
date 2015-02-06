package com.capstone.Lexington;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class AudioVideoActivity extends Activity implements SurfaceHolder.Callback {
	private static final int UPDATE_FREQUENCY = 500;
    private static final int STEP_VALUE = 4000;
    
	private TextView mediaName;
	private TextView mediaDesc;
	private SurfaceView videoSurface;
	private SurfaceHolder videoHolder;
	private MediaPlayer mp = null;
	private String file;
    private SeekBar seekbar = null;
    private ImageButton playButton = null;
    private ImageButton prevButton = null;
    private ImageButton nextButton = null;
    private Intent info;
    private boolean isStarted = true;
    private boolean isMoveingSeekBar = false;
    private final Handler handler = new Handler();
	/** something for audio **/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		
		info = getIntent();

		file = info.getStringExtra("file");
		mp = new MediaPlayer();
		
		if(info.getIntExtra("type", 1) == 1)
		{
			setContentView(R.layout.activity_audio_row);
			mediaName = (TextView) findViewById(R.id.audioName);
			mediaDesc = (TextView) findViewById(R.id.audioDesc);
			mediaName.setText(info.getStringExtra("name"));
			mediaDesc.setText(info.getStringExtra("desc"));
		}
		else
		{
			setContentView(R.layout.activity_video_row);
			mediaName = (TextView) findViewById(R.id.videoName);
			mediaDesc = (TextView) findViewById(R.id.videoDesc);
			videoSurface = (SurfaceView)findViewById(R.id.videoView);
			mediaName.setText(info.getStringExtra("name"));
			mediaDesc.setText(info.getStringExtra("desc"));
			
			
			getWindow().setFormat(PixelFormat.UNKNOWN);
			videoHolder = videoSurface.getHolder();
			videoHolder.addCallback(this);
		}
		seekbar = (SeekBar)findViewById(R.id.seekbar);
	    playButton = (ImageButton)findViewById(R.id.play);
	    prevButton = (ImageButton)findViewById(R.id.prev);
	    nextButton = (ImageButton)findViewById(R.id.next);
		
        playButton.setOnClickListener(onButtonClick);
        nextButton.setOnClickListener(onButtonClick);
        prevButton.setOnClickListener(onButtonClick);
        
		startPlay("android.resource://com.capstone.Lexington/raw",file.substring(0, file.length() - 4));
		
		
	}
	
	public void startPlay(String path, String fileName){
		//set up MediaPlayer  
		if(mp == null)
			mp = new MediaPlayer();
		
        
        
	    mp.setOnCompletionListener(onCompletion);
	    mp.setOnErrorListener(onError);
	    mp.setOnPreparedListener(onPrepare);
	    seekbar.setOnSeekBarChangeListener(seekBarChanged);
		
	    if(info.getIntExtra("type", 1) == 2)
	    {
	    	mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
	    	
		    //Get the dimensions of the video
		    int videoWidth = mp.getVideoWidth();
		    int videoHeight = mp.getVideoHeight();
	
		    //Get the width of the screen
		    int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
	
		    //Get the SurfaceView layout parameters
		    android.view.ViewGroup.LayoutParams lp = videoSurface.getLayoutParams();
	
		    //Set the width of the SurfaceView to the width of the screen
		    lp.width = screenWidth;
	
		    //Set the height of the SurfaceView to match the aspect ratio of the video 
		    //be sure to cast these as floats otherwise the calculation will likely be 0
		    lp.height = (int) (((float)videoHeight / (float)videoWidth) * (float)screenWidth);
	
		    //Commit the layout parameters
		    videoSurface.setLayoutParams(lp);
	    }
	    
	    seekbar.setProgress(0);
	    try {mp.setDataSource(AudioVideoActivity.this, Uri.parse(path+"/"+fileName));
	         mp.prepareAsync();} 
	    catch (IllegalArgumentException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IllegalStateException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    


	}

	private final Runnable updatePositionRunnable = new Runnable() {
		public void run() {
			updatePosition();
		}
	};

	private void stopPlay() {
		mp.stop();
		mp.reset();
		playButton.setImageResource(android.R.drawable.ic_media_play);
		handler.removeCallbacks(updatePositionRunnable);
		seekbar.setProgress(0);

		isStarted = false;
	}

	private void updatePosition() {
		handler.removeCallbacks(updatePositionRunnable);

		seekbar.setProgress(mp.getCurrentPosition());

		handler.postDelayed(updatePositionRunnable, UPDATE_FREQUENCY);
	}
	

	private MediaPlayer.OnCompletionListener onCompletion = new MediaPlayer.OnCompletionListener() {

		@Override
		public void onCompletion(MediaPlayer mp) {
			stopPlay();
		}
	};

	private MediaPlayer.OnErrorListener onError = new MediaPlayer.OnErrorListener() {

		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			// returning false will call the OnCompletionListener
			return false;
		}
	};
	
	private MediaPlayer.OnPreparedListener onPrepare = new MediaPlayer.OnPreparedListener() 
	{
		  public void onPrepared(MediaPlayer mp) {
			      mp.start();
			      seekbar.setMax(mp.getDuration());
			      playButton.setImageResource(android.R.drawable.ic_media_pause);
			      
			      updatePosition();
			        
			      isStarted = true;
		  }
	};

	private SeekBar.OnSeekBarChangeListener seekBarChanged = new SeekBar.OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			isMoveingSeekBar = false;
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			isMoveingSeekBar = true;
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (isMoveingSeekBar) {
				mp.seekTo(progress);

				Log.i("OnSeekBarChangeListener", "onProgressChanged");
			}
		}
	};

	private View.OnClickListener onButtonClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.play: {
				if (mp.isPlaying()) {
					handler.removeCallbacks(updatePositionRunnable);
					mp.pause();
					playButton.setImageResource(android.R.drawable.ic_media_play);
				} else {
					if (isStarted) {
						mp.start();
						playButton.setImageResource(android.R.drawable.ic_media_pause);

						updatePosition();
					} else {
						startPlay("android.resource://com.capstone.Lexington/raw",file.substring(0, file.length() - 4));
					}
				}

				break;
			}
			case R.id.next: {
				int seekto = mp.getCurrentPosition() + STEP_VALUE;

				if (seekto > mp.getDuration())
					seekto = mp.getDuration();

				mp.pause();
				mp.seekTo(seekto);
				mp.start();

				break;
			}
			case R.id.prev: {
				int seekto = mp.getCurrentPosition() - STEP_VALUE;

				if (seekto < 0)
					seekto = 0;

				mp.pause();
				mp.seekTo(seekto);
				mp.start();

				break;
			}
			}
		}
	};
	

	@Override
	protected void onDestroy() {
		super.onDestroy();

		handler.removeCallbacks(updatePositionRunnable);
		seekbar.setOnSeekBarChangeListener(null);
		if(mp!=null)
		{
			mp.stop();
			mp.reset();
			mp.release();
	
			mp = null;
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mp.setDisplay(videoHolder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}

}