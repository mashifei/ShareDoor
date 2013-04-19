package com.example.common;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import android.util.Log;

public class FileHelper {
	
	public static ArrayList<String> GetAllPPT(String path){
		String[] exts = new String[] {".ppt",".pptx"};
		
		ArrayList<String> list = new ArrayList<String>();
		
		FindExtFiles(exts, path, list);
		
		return list;
	}
	
	public static ArrayList<String> GetAllPic(String path){
		String[] exts = new String[] {".jpg",".png",".gif"};
		
		ArrayList<String> list = new ArrayList<String>();
		
		FindExtFiles(exts, path, list);
		
		return list;
	}
	
	private static void FindExtFiles(String[] exts, String path, ArrayList<String> list){
		File file = new File(path);
		if(file == null)
			return;
		File[] fileArray = file.listFiles();
		if(fileArray == null)
			return;
		for(File tempFile : fileArray){
			String name = tempFile.getName();
			for(String ext : exts){
				if(name.endsWith(ext)){
					list.add(tempFile.getParent() + "/" + name);
					break;
				}
			}
			FindExtFiles(exts, tempFile.getParent() + "/" + name, list);
		}
	}
}
