package com.yl.module_base_utils;

/**
 * Created by Administrator on 2017/8/1.
 * 检测手机是否被root
 */


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import java.io.DataOutputStream;

public class RootManager{
    //使用方式：
//    private void checkRoot() {
//        String apkRoot = "chmod 777 " + getPackageCodePath();
//        boolean root = RootManager.RootCommand(apkRoot);
//        if (root) {
//            new AlertDialog.Builder(HomeActivity.this).setCancelable(false).setTitle("风险提示").setMessage("手机已经root，继续使用可能有风险！")
//                    .setPositiveButton("继续", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    finish();
//                }
//            }).create().show();
//        }
//
//    }
    public static boolean RootCommand(String command)
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e)
        {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;
        } finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            } catch (Exception e)
            {
            }
        }
        Log.d("*** DEBUG ***", "Root SUC ");
        return true;
    }

}