package com.yl.module_view.painterview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.io.ByteArrayOutputStream;

/**
 * ==============Sinoxk=================
 *
 * Author: 麦芽
 * Email : maiya@sinoxk.com
 * Time  : 2016/12/22 16:22
 * Action  :图片工具,用于生成Bitmap对象
 *
 * ==============Sinoxk=================
 */
public class BitmapUtil {

    public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return bitmap;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}  