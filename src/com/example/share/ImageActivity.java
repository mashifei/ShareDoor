package com.example.share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.common.FileHelper;
import com.example.common.Settings;

public class ImageActivity extends Activity {
	private final String url = "url";
	private GridView imageView;
	private Button sureButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		imageView = (GridView)findViewById(R.id.gridView_images);
		initView();
		
		sureButton = (Button)findViewById(R.id.button_sureShare);
		sureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SureButtonClick();
			}
		});
	}
	
	private void initView(){
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		imageView.setNumColumns(dm.widthPixels/100);
		List<HashMap<String, Object>> list = GetData(FileHelper.GetAllPic("/mnt"));
		imageView.setAdapter(new SimpleAdapter(this,list,R.layout.imagelistitem,new String[]{url}, new int[]{R.id.imageView_imageListItem}));
		imageView.setOnItemClickListener(new GridViewItemClick());
	}

	private ArrayList<HashMap<String, Object>> GetData(ArrayList<String> list){
		if(list == null){
			return null;
		}
		
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		
		for(String str : list){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(url, str);
			listMap.add(map);
		}
		
		return listMap;
	}
	
	private void SureButtonClick(){
		ArrayList<String> imageUri = new ArrayList<String>();
		SimpleAdapter adapter = (SimpleAdapter)imageView.getAdapter();
		for(int i = 0 ; i < imageView.getChildCount() ; i++){
			View imageItemView = imageView.getChildAt(i);
			CheckBox checkBox = (CheckBox)imageItemView.findViewById(R.id.checkBox_imageListItem);
			if(checkBox.isChecked()){
				imageUri.add(((HashMap<String, Object>)adapter.getItem(i)).get(url).toString());
			}
		}
		
		if(imageUri.size() == 0){
			Toast.makeText(this, "未选择需要分享的图片", Toast.LENGTH_LONG).show();
			return;
		}
		Intent intent = new Intent();
		Bundle bundle = new Bundle(); 
		bundle.putStringArrayList(Settings.String_ImageURls, imageUri);
		intent.putExtras(bundle);
		intent.setClass(ImageActivity.this, SendMessageActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_images, menu);
		return true;
	}
	
	class GridViewItemClick implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			CheckBox checkBox = (CheckBox)arg1.findViewById(R.id.checkBox_imageListItem);
			checkBox.setChecked(!checkBox.isChecked());
		}
	}
}
