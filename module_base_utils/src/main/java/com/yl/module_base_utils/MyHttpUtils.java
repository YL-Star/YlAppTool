package com.yl.module_base_utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class MyHttpUtils {

    public static String getUrlContent(String url, int timeOut) {
            String str = "";
            try {

                URL aURL = null;
                try {
                    aURL = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                HttpURLConnection conn = (HttpURLConnection) aURL.openConnection();

                conn.connect();
                StringBuilder b = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    b.append(line);
                }
                reader.close();
            str = b.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    public static String postData(String urlStr,String parameter){
        String jsonData = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);// 打开输入流
            connection.setDoOutput(true);// 打开输出流
            connection.setDoInput(true);// 打开输入流
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "utf-8"));
            writer.write(parameter);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer xml = new StringBuffer();
            String temp = "";
            while((temp = reader.readLine())!=null){
                xml.append(temp);
            }
            reader.close();
            jsonData = xml.toString();

        }catch (Exception e) {
            jsonData =e.toString();
        }
        return jsonData;
    }
    public static String sendPost(String url, String parameter) {
        String result = "";
        try {
            URL u0 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
            conn.setRequestMethod("POST");

            byte contentbyte[] = parameter.toString().getBytes();
            conn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 设置请求类型
            conn.setRequestProperty("Content-Type",
                    "application/json; charset=utf-8");
            // 设置表单长度
            conn.setRequestProperty("Content-Length", (new StringBuilder())
                    .append(contentbyte.length).toString());
            // 设置默认语言
            conn.setRequestProperty("Content-Language", "en-US");// zh-CN代表中国
            // 默认为美式英语
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            // 连接主机的超时时间（单位：毫秒）
            conn.setConnectTimeout(5000);
            // 从主机读取数据的超时时间（单位：毫秒)
            conn.setReadTimeout(5000);
            // Post 请求不能使用缓存
            conn.setUseCaches(false);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            conn.setDoInput(true);
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 2
            // http正文内，因此需要设为true, 默认情况下是false;
            conn.setDoOutput(true);

                BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
                        conn.getOutputStream(),"UTF-8"));
                bWriter.write(parameter.toString());
                bWriter.flush();
                bWriter.close();

            // 调用HttpURLConnection连接对象的getInputStream()函数,
            // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
            InputStream in = conn.getInputStream();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i != -1; ) {
                i = in.read();
                if (i != -1)
                    buffer.append((char) i);
            }
            in.close();

            // 此方法是用Reader读取BufferedReader reader = new BufferedReader(new
            // InputStreamReader( connection.getInputStream()));
            result = new String(buffer.toString().getBytes("iso-8859-1"),
                    "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
//            result = "";

            result = e.toString();
        }
        return result;
    }

    protected static String sendPost(HttpPost mPost, List<BasicNameValuePair> pairs) {
        String result = "";
        InputStream in = null;
        BufferedReader br = null;
        try {
            DefaultHttpClient mHttpClient = new DefaultHttpClient();
            //设置超时时间
            mHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
            mHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
            mPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));

            HttpResponse response = mHttpClient.execute(mPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                //String result = EntityUtils.toString(response.getEntity(),"utf-8");
                in = response.getEntity().getContent();
                br = new BufferedReader(new InputStreamReader(in, "utf-8"));

                String tempbf;
                StringBuffer html = new StringBuffer(256);
                while ((tempbf = br.readLine()) != null) {
                    html.append(tempbf + "\n");
                }
                result = html.toString();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = "服务器访问超时。";
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String UploadPic(String actionUrl, String recordzFile) {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        String backurl = "";
        try {
            File tempFile = new File(recordzFile);

//             actionUrl =  "http://202.102.92.79:8080/lnfx/dingdanjk.do?action=upload";
            URL url = new URL(actionUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");

            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);

            // 開始寫資料
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());

            FileInputStream fStream;
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            String name = tempFile.getAbsolutePath();
            name = name.substring(tempFile.getAbsolutePath().lastIndexOf("/"),
                    name.length());
            // 圖片
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; name=\"logourl\";filename=\""
                    + URLEncoder.encode(name) + "\"" + end);
            ds.writeBytes(end);
            // 取得檔案
            fStream = new FileInputStream(tempFile.getAbsoluteFile());
            length = -1;
            // 讀取至緩衝區
            while ((length = fStream.read(buffer)) != -1) {
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);

            fStream.close();
            ds.flush();

            // 結束
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

            // 取得網頁回應內容
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }

            backurl = b.toString().trim();

        } catch (Exception e) {
        }
        return backurl;
    }


}
