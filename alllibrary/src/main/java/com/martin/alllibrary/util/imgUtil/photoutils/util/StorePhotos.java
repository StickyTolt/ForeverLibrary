package com.martin.alllibrary.util.imgUtil.photoutils.util;

import android.graphics.Bitmap;

import java.io.FileOutputStream;
import java.io.IOException;

public class StorePhotos {

	FileOutputStream fos;

	private Bitmap bitmap;

	private String fileName;

	public StorePhotos(Bitmap bitmap, String fileName) {
		this.bitmap=bitmap;
		this.fileName=fileName;
		//调用存储方法
		store();
	}

	private void store() {
		try {
			if (bitmap!=null) {
				fos = new FileOutputStream(fileName);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if (bitmap!=null) {
					fos.flush();
					fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
