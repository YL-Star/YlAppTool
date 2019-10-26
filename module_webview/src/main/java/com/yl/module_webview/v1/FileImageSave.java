package com.yl.module_webview.v1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yl.module_base_utils.Entity;
import com.yl.module_base_utils.myUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

//import android.webkit.WebView;

/**
 * Created by huzhimin on 16/3/11.
 */
public class FileImageSave {


    private static FileImageSave fragment;

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

    public static FileImageSave newInstance() {
        if (fragment == null) {
            fragment = new FileImageSave();
        }
        return fragment;
    }

    private Activity context;
    private String picUrl;

    public void saveImage(final WebView webView, final Activity context) {
        this.context = context;
        // 长按点击事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final WebView.HitTestResult hitTestResult = webView.getHitTestResult();
                // 如果是图片类型或者是带有图片链接的类型
                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                        hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    // 弹出保存图片的对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("保存图片到本地");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //获取图片链接
                            picUrl = hitTestResult.getExtra();
//                            picUrl = "http://ln241.5ibp.com/lnfx_oto_yxs/getqrcode?size=240&content=http%3A//ln241.5ibp.com/lnfx_oto_yxs/wd/imgDetail%3FsourceFlag%3Dwx%26id%3D2377%26zwphone%3D15850781700";
                            myUtils.log("获取图片链接picUrl:", picUrl);
                            if (picUrl.contains("lnfx_oto_yxs/getqrcode")) {//获取url自己生成二维码。直接下载失败，使用笨方法

                                Uri uri = Uri.parse(picUrl);
                                String url = uri.getQueryParameter("content");

                                if (TextUtils.isEmpty(url)) {
                                    Toast.makeText(context, "内容为空!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                Bitmap mBitmap =  CodeUtils.createImage(url, 400, 400, null);
                                save2Album(mBitmap);
                            } else {
                        //保存图片到相册
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Bitmap bitmap = myUtils.getBitmap(picUrl);
                                        save2Album(bitmap);
                                    }
                                }).start();
                            }
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        // 自动dismiss
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }
                return false;//保持长按可以复制文字
            }
        });
    }

    public void url2bitmap(String url) {
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
            is.close();
            if (bm != null) {
                save2Album(bm);
            }
        } catch (Exception e) {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void save2Album(Bitmap bitmap) {
        File appDir = new File(Entity.pathSD);
        if (!appDir.exists()) {
            appDir.mkdir();
            try {
                appDir.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(appDir, myUtils.getNowTime() + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (bitmap == null) {
                return;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        file.getAbsolutePath(), myUtils.getNowTime() + ".jpg", null);
//                Toast.makeText(context, "保存成功！", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
//                Toast.makeText(context, "保存失败！", Toast.LENGTH_LONG).show();
            }

            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            context.sendBroadcast(intent);

            onSaveSuccess(file);
        } catch (IOException e) {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void onSaveSuccess(final File file) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
