package com.example.julive.wechathelper;


import android.util.Log;

import java.io.*;

public class FileUtil {

    static String readLogByString(String path, String defaultValue) {
        StringBuffer sb = new StringBuffer();
        String tempstr = null;
        try {
            File file = new File(path);
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
                writeLog(path, defaultValue, false, "utf-8");
                return defaultValue;
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            while ((tempstr = br.readLine()) != null) {
                sb.append(tempstr);
            }
        } catch (IOException ex) {
            return defaultValue;
        }
        return sb.toString();
    }

    /**
     * 写入文件,末尾自动添加\r\n
     *
     * @param path
     * @param str
     */
    static void writeLog(String path, String str, boolean is_append, String encode) {
        try {
            File file = new File(path);
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists())
                file.createNewFile();
            FileOutputStream out = new FileOutputStream(file, is_append); //true表示追加
            StringBuffer sb = new StringBuffer();
            sb.append(str + "\r\n");
            out.write(sb.toString().getBytes(encode));//
            out.close();
            Log.d("AccessibilityNodeInfo", "writeLog: $path " + path + "$content " + str);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e("AccessibilityNodeInfo", "writeLog: StackTrace " + ex.getStackTrace());
        }
    }
}
