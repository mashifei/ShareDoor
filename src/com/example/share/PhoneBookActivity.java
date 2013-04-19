package com.example.share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//µç»°±¡
public class PhoneBookActivity extends Activity {
	private ListView phoneList;
	private Button sureButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_book);
		phoneList = (ListView)findViewById(R.id.listview_phone);
		initView();
		sureButton = (Button)findViewById(R.id.button_sureChoose);
		sureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SureButtonClick();
			}
		});
	}

	private void initView(){
		Uri uri = Phone.CONTENT_URI;
		Cursor cursor = this.managedQuery(uri, null, null, null, null);
		List<HashMap<String, Object>> list = GetData(cursor);
		phoneList.setAdapter(new SimpleAdapter(this,list,R.layout.phonebooklistitem,new String[]{"name","number"}, new int[]{R.id.textView_phonebooklistItem_name,R.id.textView_phonebooklistItem_number}));
	}

	private ArrayList<HashMap<String, Object>> GetData(Cursor cursor){
		if(cursor == null){
			return null;
		}
		
		ArrayList<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		
		int numberIndex = cursor.getColumnIndex(Phone.NUMBER);
		int nameIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME);
		
		if(cursor.getCount() > 0){
			do{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", cursor.getString(nameIndex));
				map.put("number", cursor.getString(numberIndex));
				listMap.add(map);
			}while(cursor.moveToNext());
		}
		
		return listMap;
	}
	
	private void SureButtonClick(){
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_phone_book, menu);
		return true;
	}
}
