package com.martin.alllibrary.util.systemUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：Martin on 2017/8/4 11:10
 * 邮箱：martin0207mfh@163.com
 */
public class StringUtil {

    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }

    // 判断一个字符串是否都为数字
    public static boolean isDigit2(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(strNum);
        return matcher.matches();
    }

    //截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 截取非数字
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 判断一个字符串是否含有数字
    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * json数组 转为字符串
     */
    public static String JsonArr2Sting(JSONArray array) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < array.length(); i++) {
            try {
                stringBuffer.append(array.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString().replace("\\", "").trim();
    }

    /**
     * jsonArr 转化为 JsonObject
     */
    public static String jsonArr2JsonObject(String jsonArr) {
        String jsonObject = "{\"data\":" + jsonArr + "}";
        return jsonObject;
    }

}
