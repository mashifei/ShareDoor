package com.example.share;

import com.example.common.Settings;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendMessageActivity extends Activity {
	private Button sendMessage;
	private Button chooseUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
		
		sendMessage = (Button)findViewById(R.id.button_sendMessage);
		sendMessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SendMessage();
			}
		});
		
		chooseUser = (Button)findViewById(R.id.button_chooseUser);
		chooseUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ChooseUser();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_send_message, menu);
		return true;
	}
	
	private void SendMessage(){
		try{
			String[] numbers = CheckUser(((EditText)findViewById(R.id.editText_phonelist)).getText().toString());
			
			Intent intent = new Intent(this,ShareImageActivity.class);
			intent.putExtras(this.getIntent().getExtras());
			PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
			SmsManager sms=SmsManager.getDefault();
			String msgContent = ((TextView)findViewById(R.id.textView_content)).getText().toString();
			for(String num : numbers){
				sms.sendTextMessage(num, null, msgContent, pi, null);
			}
			
			Toast.makeText(this, "成功发送短信", 0).show();
			
		}catch(Exception e){
			Toast.makeText(this, e.getMessage(), 0).show();
		}
	}
	
	private String[] CheckUser(String users) throws Exception{
		if(users == null){
			throw new Exception("电话号码不符合要求");
		}
		String[] phones = users.split(";");
		
		for(String phone : phones){
			if(phone == null || !PhoneNumberUtils.isGlobalPhoneNumber(phone) || phone.length() <= 0){
				throw new Exception("电话号码不符合要求");
			}
		}
		return phones;
	}

	private void ChooseUser(){
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setClass(SendMessageActivity.this, PhoneBookActivity.class);
		startActivity(intent);
	}
}
