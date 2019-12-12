//package com.yl.module_base_utils;
//
//import android.util.Log;
//
//import com.hlkj.cmarketing.entity.AssignObj;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.List;
//
///**   网络工具   @author yanglei   @version [1.0, 2019.3]  */public class HttpUtils {
//    public static String getUrlContent1(String url, int timeOut) {
//        String str = "";
//        Log.d("urlxx",url+"");
////        System.out.println("urlxx:" + url);
//        try {
//            URL aURL = new URL(url);
//            URLConnection conn = aURL.openConnection();
//            conn.connect();
//            StringBuilder b = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    conn.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                b.append(line);
//            }
//            reader.close();
//            str = b.toString();
//        } catch (IOException e) {
//        } catch (Exception e) {
//        }
//        return str;
//    }
//
//    public static String getUrlContent(String url, int timeOut) {
//        String str = "";
////		System.out.println("url1:" + url);
//        try {
//            URL aURL = new URL(url);
//            URLConnection conn = aURL.openConnection();
//            conn.connect();
//            StringBuilder b = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    conn.getInputStream(), "UTF-8"));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                b.append(line);
//            }
//            reader.close();
//            str = b.toString();
//        } catch (IOException e) {
//        } catch (Exception e) {
//        }
//        System.out.println("back:" + str);
//
//        return str;
//    }
//
//    public static String stripHtml(String content) {
//        // <p>段落替换为换行
//        content = content.replaceAll("<p .*?>", "\r\n");
//        // <br><br/>替换为换行
//        content = content.replaceAll("<br\\s*/?>", "\r\n");
//        // 去掉其它的<>之间的东西
//        content = content.replaceAll("\\<.*?>", "");
//        // 还原HTML
//        // content = HTMLDecoder.decode(content);
//        return content;
//    }
//
//    // 去重
//    public static List<AssignObj> distinct(List<AssignObj> list) {
//        for (int i = 0; i < list.size() - 1; i++) {
//            for (int j = list.size() - 1; j > i; j--) {
//                if (list.get(j).getOrderId().equals(list.get(i).getOrderId())) {
//                    list.remove(j);
//                }
//            }
//        }
//        return list;
//    }
//}
