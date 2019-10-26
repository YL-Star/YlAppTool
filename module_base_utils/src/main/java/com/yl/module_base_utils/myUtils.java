package com.yl.module_base_utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/5/6.
 */

public class myUtils {
    //在这里抽取了一个方法   可以封装到自己的工具类中...
    public static File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 数据存储
     *
     * @author yanglei
     * @version [1.0, 2019.]
     */
    public static void stringTxt(String path, String str) {
        try {
            FileWriter fw = new FileWriter(path + "idlog.txt");//SD卡中的路径
            fw.flush();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断某个Activity 界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     * @return
     */
    public static boolean isActResume(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;

    }

    public static String buildMap(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                sb.append(key + "=");
                if (TextUtils.isEmpty(map.get(key))) {
                    sb.append("&");
                } else {
                    String value = map.get(key);
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    sb.append(value + "&");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 日志
     *
     * @author yanglei
     * @version [1.0, 2019.]
     */
    public static void log(String name, String str) {
        if (str == null) return;
        if (BuildConfig.isLog) {
            if (str.length() > 3000) {
                for (int i = 0; i < str.length(); i += 3000) {
                    if (i + 3000 < str.length())
                        Log.d("tag" + i, name + ":\n" + str.substring(i, i + 3000));
                    else
                        Log.d("tag" + i, name + ":\n" + str.substring(i, str.length()));

                }
            } else {
                Log.d("tag", name + ":\n" + str);
            }
        }
    }

    /**
     * 格式化银行卡 加*
     * 3749 **** **** 330
     *
     * @param cardNo 银行卡
     * @return 3749 **** **** 330
     */
    public static String formatCard(String cardNo) {
        if (cardNo.length() < 8) {
            return "";
        }
        String card = "";
        card = cardNo.substring(0, 4) + "**********";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 规则3：密码长度为8位及以上；包含字母大小写、数字和特殊字符最少两种。
     *
     * @param str
     * @return
     */
    public static boolean isContainAll_(Activity activity, String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLowerCase = false;//定义一个boolean值，用来表示是否包含字母
        boolean isUpperCase = false;//大写字母
        boolean isXX = false;//是否包含特殊字符
        boolean islength = false;//长度
        boolean isRight = false;
        String sx;
        sx = activity.getResources().getString(R.string.checkpassword);

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i)) || Character.isUpperCase(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true;
                isUpperCase = true;
            }
            if (sx.contains(str.charAt(i) + "")) {
                isXX = true;
            }
        }
        if (str.length() > 7) {
            islength = true;
        }
        if (isDigit && (isLowerCase || isUpperCase) && islength) {//数字+字母+长度
            isRight = true;
        } else if ((isLowerCase || isUpperCase) && isXX && islength) {//字母+特殊字符+长度
            isDigit = true;
        } else if (isDigit && isXX && islength) {//数字+特殊字符+长度
            isDigit = true;
        } else {
            isDigit = false;
        }
        return isRight;
    }

    /**
     * 规则4：密码长度为8位及以上；包含字母大小写、数字最少两种。
     *
     * @param str
     * @return
     */
    public static boolean isContainAll(Activity activity, String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLowerCase = false;//定义一个boolean值，用来表示是否包含字母
        boolean isUpperCase = false;//大写字母
        boolean islength = false;//长度
        boolean isRight = false;
        String sx;
        sx = activity.getResources().getString(R.string.checkpassword);

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i)) || Character.isUpperCase(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true;
                isUpperCase = true;
            }

        }
        if (str.length() > 7) {
            islength = true;
        }
        if (isDigit && (isLowerCase || isUpperCase) && islength) {//数字+字母+长度
            isRight = true;
        } else {
            isDigit = false;
        }
        return isRight;
    }

    /**
     * 保存bitmap到本地
     *
     * @param mBitmap
     * @return
     */
    public static void saveBitmap(Bitmap mBitmap) {
        File sdDir = Environment.getExternalStorageDirectory();
        File fileDir = new File(sdDir.getPath() + "/保存图片的文件夹名");
        if (!fileDir.exists()) {
            // 必须要先有父文件夹才能在父文件夹下建立想要的子文件夹
            // 即LIMS文件必须存在，才能建立IMG文件夹
            fileDir.mkdir();
        }
        String fileUrl = fileDir.getAbsolutePath() + "/图片名字.png";
        try {
            FileOutputStream fos = new FileOutputStream(new File(fileUrl));
            //此处注意为Bitmap.CompressFormat.PNG
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * 检查SD卡是否挂载
     *
     * @return
     */
    public static boolean checkSDcard(Context context) {
        boolean flag = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (!flag) {
            Toast.makeText(context, "请插入手机存储卡再使用本功能", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }
}
