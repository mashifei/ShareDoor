package com.example.share;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import com.example.common.ImageHelper;
import com.example.common.Settings;

public class ShareImageActivity extends Activity implements OnGestureListener  {
	private ViewFlipper flipper;
	private GestureDetector detector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_image);
		
		detector = new GestureDetector(this);
		flipper = (ViewFlipper)findViewById(R.id.viewFlipper_image);
		
		Bundle bundle = this.getIntent().getExtras();
		
		if(bundle.containsKey(Settings.String_ImageURls)){
			ArrayList list = bundle.getCharSequenceArrayList(Settings.String_ImageURls);
			for(Object str : list.toArray()){
				flipper.addView(ImageHelper.CreateLoacalImageView(str.toString(), this));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_share_image, menu);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		flipper.showNext();
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
