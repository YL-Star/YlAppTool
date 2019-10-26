package com.yl.ylapp.camera;
 
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.yl.ylapp.R;

import java.io.IOException;
 
public class SufaceActivity extends AppCompatActivity {
 
 
    // Used to load the 'native-lib' library on application startup.
//    static {
////        System.loadLibrary("native-lib");
////    }
 
    private Camera camera;
    private SurfaceHolder sh;
    private SurfaceView sv_camera;
    private Matrix matrix = new Matrix();
    private SurfaceView sv_camera_face;
    private SurfaceHolder sv_camera_face_holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        camera = Camera.open(1);
 
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        sv_camera = findViewById(R.id.sv_camera);
        sv_camera_face = findViewById(R.id.sv_camera_face);
        tv.setText(stringFromJNI());
//        Camera.open();
        sh = sv_camera.getHolder();
        sv_camera_face_holder = sv_camera_face.getHolder();
        sv_camera_face_holder.setFormat(PixelFormat.TRANSLUCENT);
//        sv_camera_face_holder.addCallback();
 
        sh.addCallback(new SurfaceCallback());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("TEST","Granted");
            //init(barcodeScannerView, getIntent(), null);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 1);//1 can be another integer
        }
    }
 
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
 
 
 
 
    private class SurfaceCallback implements SurfaceHolder.Callback{
 
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            sh = holder;
            try {
                followScreenOrientation(getBaseContext(),camera);
                camera.startFaceDetection();
                camera.setFaceDetectionListener(new FaceDetectListener());
 
                final Camera.Parameters params = camera.getParameters();
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
 
                params.setSceneMode(Camera.Parameters.SCENE_MODE_BARCODE);
                params.setJpegQuality(100); // 设置照片质量
 
                camera.setPreviewDisplay(holder);
                camera.startPreview();
                Log.i("camera","create");
 
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
 
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.i("camera","change");
        }
 
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.setPreviewCallback(null );
            camera.release();
        }
    }
 
    public static void followScreenOrientation(Context context, Camera camera){
        final int orientation = context.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            camera.setDisplayOrientation(180);
        }else if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            camera.setDisplayOrientation(90);
        }
    }
 
    private class FaceDetectListener implements Camera.FaceDetectionListener{
 
 
        @Override
        public void onFaceDetection(Camera.Face[] faces, Camera camera) {
            Log.i("face_position",faces.toString());
            if(faces.length>0) {
                Rect face_rect = faces[0].rect;
                Log.i("检测到了",face_rect.toString());
                try{
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    paint.setStrokeWidth(5f);
                    paint.setAlpha(180);
                    paint.setStyle(Paint.Style.STROKE);
                    Canvas face_canvas = sv_camera_face_holder.lockCanvas();
                    face_canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//                    face_canvas.drawColor(Color.BLUE);
 
                    prepareMatrix(matrix, true, 90, sv_camera.getWidth(), sv_camera.getHeight());
                    RectF rf = new RectF(face_rect.left,
                            face_rect.top,
                            face_rect.right,
                            face_rect.bottom);
 
 
                    matrix.mapRect(rf);
 
                    face_rect =new Rect();
                    rf.round(face_rect);
 
                    face_canvas.drawRect(new Rect(face_rect.left,
                            face_rect.top,
                            face_rect.right,
                            face_rect.bottom),paint);//绘制矩形
 
 
                    sv_camera_face_holder.unlockCanvasAndPost(face_canvas );
                }catch (Exception ex){
                    Log.i("出错了","2");
                }
            }
            //
//            Canvas face_canvas = sh.lockCanvas(new Rect(1200, 1200, 1220, 1220));
//            sh.unlockCanvasAndPost(face_canvas );
        }
    }
 
 
    /**
     * 因为对摄像头进行了旋转，所以同时也旋转画板矩阵
     * 详细请查看{@link android.hardware.Camera.Face#rect}
     * @return 旋转后的矩阵
     */
    private Matrix updateFaceRect() {
        Matrix matrix = new Matrix();
        Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        // Need mirror for front camera.
        boolean mirror = (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT);
        matrix.setScale(mirror ? -1 : 1, 1);
        // This is the value for android.hardware.Camera.setDisplayOrientation.
        // 刚才我们设置了camera的旋转参数，所以这里也要设置一下
        matrix.postRotate(90);
        // Camera driver coordinates range from (-1000, -1000) to (1000, 1000).
        // UI coordinates range from (0, 0) to (width, height).
        matrix.postScale(sv_camera.getWidth() / 2000f, sv_camera.getHeight() / 2000f);
        matrix.postTranslate(sv_camera.getWidth() / 2f, sv_camera.getHeight() / 2f);
        return matrix;
    }
 
    private class SurfaceView_face_Callback implements SurfaceHolder.Callback{
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            followScreenOrientation(getBaseContext(),camera);
        }
 
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
 
        }
 
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
 
        }
    }
    public static void prepareMatrix(Matrix matrix, boolean mirror, int displayOrientation,
                                     int viewWidth, int viewHeight) {
        // Need mirror for front camera.
        matrix.setScale(mirror ? -1 : 1, 1);
        // This is the value for android.hardware.Camera.setDisplayOrientation.
        matrix.postRotate(displayOrientation);
        // Camera driver coordinates range from (-1000, -1000) to (1000, 1000).
        // UI coordinates range from (0, 0) to (width, height).
        matrix.postScale(viewWidth / 2000f, viewHeight / 2000f);
        matrix.postTranslate(viewWidth / 2f, viewHeight / 2f);
    }
}