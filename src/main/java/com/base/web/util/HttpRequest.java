package com.base.web.util;

/**
 * Created by liubo on 2017/1/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.base.web.vo.ResultBase;
import io.itit.itf.okhttp.FastHttpClient;
import io.itit.itf.okhttp.FastHttpClientBuilder;
import io.itit.itf.okhttp.PostBuilder;
import io.itit.itf.okhttp.Response;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpRequest {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			// connection.setRequestProperty("Accept-Charset", "UTF-8");
			// connection.setRequestProperty("contentType", "UTF-8");
			// connection.setRequestProperty("Content-type",
			// "application/x-www-form-urlencoded;charset=UTF-8");
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			// 遍历所有的响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param reqCharset
	 *            请求编码
	 * @param resCharset
	 *            响应编码
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param, String reqCharset, String resCharset) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			// conn.setRequestProperty("Accept-Charset", "UTF-8");
			// conn.setRequestProperty("contentType", "UTF-8");
			// conn.setRequestProperty("Content-type",
			// "application/x-www-form-urlencoded;charset=UTF-8");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			// out = new PrintWriter(conn.getOutputStream());
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), reqCharset));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), resCharset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String doPost03(String url, Map<String, String> headers, Map<String, String> params,
			String reqcharset, String rescharset) {

		HttpClient client = new HttpClient();
		// post请求
		PostMethod postMethod = new PostMethod(url);

		// 设置header
		setHeaders(postMethod, headers);

		// 设置post请求参数
		setParams(postMethod, params);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);

		String responseStr = "";
		BufferedReader in = null;
		try {
			client.executeMethod(postMethod);

			in = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), rescharset));
			String line;
			while ((line = in.readLine()) != null) {
				responseStr += line;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			responseStr = "";
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					;
				}
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}

		return responseStr;

	}

	public static String doPost04(String url, Map<String, String> headers, Map<String, String> params,
			String reqcharset, String rescharset) {

		try {
			FastHttpClientBuilder httpbuilder = FastHttpClient.newBuilder();
			httpbuilder.connectTimeout(10, TimeUnit.SECONDS);
			httpbuilder.readTimeout(30, TimeUnit.SECONDS);
			PostBuilder postbuilder = httpbuilder.build().post();

			// 设置header
			if (headers != null && headers.size() > 0) {
				Set<String> headersKeys = headers.keySet();
				Iterator<String> iterHeaders = headersKeys.iterator();
				while (iterHeaders.hasNext()) {
					String key = iterHeaders.next();
					postbuilder.addHeader(key, headers.get(key));
				}
			}
			// 设置参数
			postbuilder.addParams(params);
			postbuilder.url(url);
			Response response = postbuilder.build().execute();
			return response.string(rescharset);
		} catch (Exception e) {
			;
		} finally {

		}
		return "";
	}
	
	public static ResultBase doPost05(String url, Map<String, String> headers, Map<String, String> params,
									  String reqcharset, String rescharset) {
		String responseStr = "";
		try {
			FastHttpClientBuilder httpbuilder = FastHttpClient.newBuilder();
			httpbuilder.connectTimeout(10, TimeUnit.SECONDS);
			httpbuilder.readTimeout(30, TimeUnit.SECONDS);
			PostBuilder postbuilder = httpbuilder.build().post();

			// 设置header
			if (headers != null && headers.size() > 0) {
				Set<String> headersKeys = headers.keySet();
				Iterator<String> iterHeaders = headersKeys.iterator();
				while (iterHeaders.hasNext()) {
					String key = iterHeaders.next();
					postbuilder.addHeader(key, headers.get(key));
				}
			}
			// 设置参数
			postbuilder.addParams(params);
			postbuilder.url(url);
			Response response = postbuilder.build().execute();
			responseStr = response.string(rescharset);
		} catch (Exception e) {
			responseStr="{'success':false}";
			logger.info(e.getMessage());
		} finally {

		}
		return JSONObject.parseObject(responseStr, ResultBase.class);
	}

	/**
	 * 设置请求头部信息
	 * 
	 * @param method
	 * @param headers
	 */
	private static void setHeaders(HttpMethod method, Map<String, String> headers) {
		Set<String> headersKeys = headers.keySet();
		Iterator<String> iterHeaders = headersKeys.iterator();
		while (iterHeaders.hasNext()) {
			String key = iterHeaders.next();
			method.setRequestHeader(key, headers.get(key));
		}
	}

	/**
	 * 设置请求参数
	 * 
	 * @param method
	 * @param params
	 */
	private static void setParams(PostMethod method, Map<String, String> params) {

		// 校验参数
		if (params == null || params.size() == 0) {
			return;
		}

		Set<String> paramsKeys = params.keySet();
		Iterator<String> iterParams = paramsKeys.iterator();
		while (iterParams.hasNext()) {
			String key = iterParams.next();
			method.addParameter(key, params.get(key));
		}

	}
}