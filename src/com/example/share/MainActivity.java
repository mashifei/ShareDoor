package com.example.share;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button image;
	private Button ppt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		image = (Button)findViewById(R.id.button_main_image);
		image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OpenImageActivity();
			}
		});
		
		ppt = (Button)findViewById(R.id.button_main_ppt);
		ppt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OpenPPtActivity();
			}
		});
		
//		Dialog dialog = new AlertDialog.Builder(this).create();
//		dialog.show();
//		dialog.set
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void OpenPPtActivity(){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ChoosePPTActivity.class);
		startActivity(intent);
	}
	
	private void OpenImageActivity(){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ImageActivity.class);
		startActivity(intent);
	}
}
