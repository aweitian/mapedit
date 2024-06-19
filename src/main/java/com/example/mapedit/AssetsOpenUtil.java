package com.example.mapedit;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Date：2021/1/17 4:08 PM
 * Creator：arvin
 * Des：
 */

public class AssetsOpenUtil {
    public static void copyDir(Context context,String dirName) {
        try {
            String[] list = context.getAssets().list(dirName);
            if (null != list) {
                for (int i = 0; i < list.length; i++) {
                    copyFile(context,dirName, list[i]);
                }
            }
        } catch (Exception e) {

        }

    }

    public static void copyFile(Context context,String dir, String fileName) {

        String rootPath = context.getExternalFilesDir(null).getAbsolutePath() + (TextUtils.isEmpty(dir) ? "" : (File.separator + dir));
        String filePath = rootPath + File.separator + fileName;
        if (new File(filePath).exists()) {
            return;
        }
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        try {
            InputStream open = context.getAssets().open(TextUtils.isEmpty(dir) ? fileName : dir + File.separator + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024 * 8];
            int count = 0;
            while ((count = open.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, count);
            }
            open.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
