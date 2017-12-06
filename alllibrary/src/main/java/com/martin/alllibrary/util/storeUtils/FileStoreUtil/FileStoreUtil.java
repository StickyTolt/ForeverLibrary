package com.martin.alllibrary.util.storeUtils.FileStoreUtil;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by marti on 2017/9/11.
 */

public class FileStoreUtil {

    private static FileStoreUtil instance;

    private FileStoreUtil() {
    }

    public static FileStoreUtil getInstance() {
        if (instance == null) {
            synchronized (FileStoreUtil.class) {
                if (instance == null) {
                    instance = new FileStoreUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 文件存储，保存字符串
     */
    public void saveStr(String data, String fileName, Context context) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput(fileName + ".txt", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取出字符串
     */
    public String getStr(String fileName, Context context) {
        StringBuilder buffer = new StringBuilder();

        FileInputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = context.openFileInput(fileName+".txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return buffer.toString();
    }

}
