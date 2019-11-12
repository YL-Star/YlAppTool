package com.yl.module_base_utils;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**   网络工具   @author yanglei   @version [1.0, 2019.3]  */public class ConnectionUrlUtil {
	/**
	 * post提交
	 *
	 * @param url
	 *            接收的url
	 * @param parameter
	 *            接收的参�?
	 * @return 返回结果
	 */
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
					"application/x-www-form-urlencoded");
			// 设置表单长度
			conn.setRequestProperty("Content-Length", (new StringBuilder())
					.append(contentbyte.length).toString());
			// 设置默认语言
			conn.setRequestProperty("Content-Language", "en-US");// zh-CN代表中国
			// 默认为美式英�?
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			// 连接主机的超时时间（单位：毫秒）
			conn.setConnectTimeout(60000);
			// 从主机读取数据的超时时间（单位：毫秒)
			conn.setReadTimeout(60000);
			// Post 请求不能使用缓存
			conn.setUseCaches(false);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			conn.setDoInput(true);
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 2
			// http正文内，因此�?要设为true, 默认情况下是false;
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream()));
			bWriter.write(parameter.toString());
			bWriter.flush();
			bWriter.close();
			// 调用HttpURLConnection连接对象的getInputStream()函数,
			// 将内存缓冲区中封装好的完整的HTTP请求电文发�?�到服务端�??
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}
			in.close();

			// 此方法是用Reader读取BufferedReader reader = new BufferedReader(new
			// InputStreamReader( connection.getInputStream()));
			result = new String(buffer.toString().getBytes("iso-8859-1"),
					"UTF-8");
		} catch (Exception ex) {
			result = "";
		}
		return result;
	}

	/**
	 * post提交
	 *
	 * @param url
	 *            接收的url
	 * @param parameter
	 *            接收的参�?
	 * @return 返回结果
	 */
	public static String sendPostTest(String url, String parameter, String ip) {
		String result = "";
		HttpURLConnection conn = null;
		try {
			URL u0 = new URL(url);

			if (ip != null) {
				String str[] = ip.split("\\.");
				byte[] b = new byte[str.length];
				for (int i = 0, len = str.length; i < len; i++) {
					b[i] = (byte) (Integer.parseInt(str[i], 10));
				}
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
						InetAddress.getByAddress(b), 80)); // b是绑定的ip，生成proxy代理对象，因为http底层是socket实现�?
				conn = (HttpURLConnection) u0.openConnection(proxy);
			} else {
				conn = (HttpURLConnection) u0.openConnection();
			}
			conn.setRequestMethod("POST");
			byte contentbyte[] = parameter.toString().getBytes();
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 设置请求类型
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 设置表单长度
			conn.setRequestProperty("Content-Length", (new StringBuilder())
					.append(contentbyte.length).toString());
			// 设置默认语言
			conn.setRequestProperty("Content-Language", "en-US");// zh-CN代表中国
			// 默认为美式英�?
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");

			// 连接主机的超时时间（单位：毫秒）
			conn.setConnectTimeout(60000);
			// 从主机读取数据的超时时间（单位：毫秒)
			conn.setReadTimeout(60000);
			// Post 请求不能使用缓存
			conn.setUseCaches(false);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			conn.setDoInput(true);
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 2
			// http正文内，因此�?要设为true, 默认情况下是false;
			conn.setDoOutput(true);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream()));
			bWriter.write(parameter.toString());
			bWriter.flush();
			bWriter.close();
			// 调用HttpURLConnection连接对象的getInputStream()函数,
			// 将内存缓冲区中封装好的完整的HTTP请求电文发�?�到服务端�??
			InputStream in = conn.getInputStream();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i != -1;) {
				i = in.read();
				if (i != -1)
					buffer.append((char) i);
			}
			in.close();

			// 此方法是用Reader读取BufferedReader reader = new BufferedReader(new
			// InputStreamReader( connection.getInputStream()));
			result = new String(buffer.toString().getBytes("iso-8859-1"),
					"UTF-8");
		} catch (Exception ex) {
			result = "";
		}
		return result;
	}
}
