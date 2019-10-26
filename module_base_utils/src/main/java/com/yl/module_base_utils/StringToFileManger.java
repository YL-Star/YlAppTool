package com.yl.module_base_utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.RandomAccessFile;

/**
 * Created by yanglei on 2018/10/19;
 */
public class StringToFileManger {
    /**
     * 数据存储
     *
     * @author yanglei
     * @version [1.0, 2019.]
     */
    public static void stringTxt(String str) {
        try {
            FileWriter fw = new FileWriter(Entity.pathPackage + myUtils.getNowTime()+"log.txt");//SD卡中的路径
            fw.flush();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void WriteLog(String s) {
        writeTxtToFile(s, Entity.pathPackage, "log.txt");
    }
    // 将字符串写入到文本文件中
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    //删除指定txt文件   通过路径
    public void deleteFile(String filePath, String fileName) {
        File f = new File(filePath + fileName);  // 输入要删除的文件位置
        if (f.exists()) {
            f.delete();
        }

    }
    public static String getSDFile(String dataApath, String fname) {

        String result=null;
        try {
            File f=new File(dataApath,fname);
            int length=(int)f.length();
            byte[] buff=new byte[length];
            FileInputStream fin=new FileInputStream(f);
            fin.read(buff);
            fin.close();
            result=new String(buff,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }




}
