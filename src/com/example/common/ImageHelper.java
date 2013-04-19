package com.example.common;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;

public final class ImageHelper {
	
//	private static final Hashtable<String, Resources> hash = new Hashtable<String, Resources>();
	
	public static ImageView CreateNetImageView(String str, Context context){
		ImageView view = new ImageView(context);
		
		try{
			URL url = new URL(str);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.connect();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				InputStream stream = conn.getInputStream();
				view.setImageBitmap(BitmapFactory.decodeStream(stream));
			}
		}catch(Exception e){
			Log.i("main", "网络读取图片出现异常：" + e.getLocalizedMessage());
		}
		
		return view;
	}
	
	public static ImageView CreateLoacalImageView(String str, Context context){
		ImageView view = new ImageView(context);
		view.setImageBitmap(BitmapFactory.decodeFile(str));
		return view;
	}
	
	public static ImageView CreateAPKImageView(int id, Context context){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
		ImageView view = new ImageView(context);
		view.setImageBitmap(bitmap);
		return view;
	}
	
	public static Bitmap Resize(Bitmap bitmap, int width, int height){
		int oldWidth = bitmap.getWidth();
		int oldHeight = bitmap.getHeight();
		float scaleWidth = (float)width / oldWidth;
		float scaleHeight = (float)height / oldHeight;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(bitmap, 0, 0 , oldWidth, oldHeight, matrix,true);
	}
}
