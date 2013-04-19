package com.example.share;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.common.FileHelper;

public class ChoosePPTActivity extends Activity {
	private ListView pptList;
	private Button sureButton;
	private Button cancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_ppt);
		
		pptList = (ListView)findViewById(R.id.listView_ppt);
		InitList();
		
		sureButton = (Button)findViewById(R.id.button_choosePPT);
		sureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SureButtonClick();
			}
		});
		
		cancelButton = (Button)findViewById(R.id.button_choosePPTCancel);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CancelButtonClick();
			}
		});
	}
	
	private void InitList(){
		List<HashMap<String, Object>> list = GetData(FileHelper.GetAllPPT("/mnt"));
		pptList.setAdapter(new SimpleAdapter(this,list,R.layout.pptlistitem,new String[]{"name","path"}, new int[]{R.id.textView_pptlistItem_name, R.id.textView_pptlistItem_path}));
	}
	
	private ArrayList<HashMap<String, Object>> GetData(ArrayList<String> list){
		if(list == null){
			return null;
		}
		
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		
		for(String str : list){
			HashMap<String, Object> map = new HashMap<String, Object>();
			File file = new File(str);
			map.put("name", file.getName());
			map.put("path", file.getParent());
			listMap.add(map);
		}
		
		return listMap;
	}
	
	private void SureButtonClick(){
		if(pptList.getChildCount() == 0){
			Toast.makeText(this, "设备中没有找到ppt或pptx文件", 0).show();
			return;
		}
		
		Intent intent = new Intent();
		Bundle bundle = new Bundle(); 
		bundle.putString("ppt", ((HashMap<String, Object>)pptList.getSelectedItem()).get("path").toString());
		intent.putExtras(bundle);
		intent.setClass(ChoosePPTActivity.this, SendMessageActivity.class);
		startActivity(intent);
	}
	
	private void CancelButtonClick(){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_ppt, menu);
		return true;
	}
}
