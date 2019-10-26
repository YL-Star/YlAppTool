package com.yl.module_update_app;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.yl.module_base_utils.Entity.pathSD;

public class DownManager {

    public static File getFileFromServer(String path, LscMsgDialog dialog)
            throws Exception {
        File file = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            dialog.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();

            CreateDir();
            file = new File(pathSD
                    + path.substring(path.lastIndexOf("/") + 1));
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                // 获取当前下载量
                dialog.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
        } catch (Exception e) {
        }
        return file;
    }

    // 判断sd卡上目录是否存在，不存在则创建
    public static void CreateDir() {
        try {
            File dir = new File(pathSD);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
        }
    }

}
