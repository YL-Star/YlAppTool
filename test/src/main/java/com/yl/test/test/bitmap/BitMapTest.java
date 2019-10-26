package com.yl.test.test.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @date: $date$ $time$
 * @author: yanglei
 * @description
 */
public class BitMapTest {
    public static void Main(String[] args) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeFile("", options);
    }
}
