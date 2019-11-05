package com.yl.ylapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class OpenCVTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static String  TAG = "TAG";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cvtest);
        initLoadOpenCVLibs();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    private void initLoadOpenCVLibs() {
        boolean success = OpenCVLoader.initDebug();
        if (success){
            Log.e(TAG, "加载完了 ");
        }
    }

    @Override
    public void onClick(View view) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher,options);
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap,src);
        Imgproc.cvtColor(src,dst,Imgproc.COLOR_BGRA2GRAY);
        Utils.matToBitmap(dst,bitmap);
        ImageView imagview = (ImageView) this.findViewById(R.id.imageView);
        imagview.setImageBitmap(bitmap);
    }
}
