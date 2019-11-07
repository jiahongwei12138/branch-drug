package com.drug.purchaseManagement.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpClientUtil {
	/**
	 * 
	 * 功能：分店接口客户端
	 * @param url  发送请求的路径
	 * @return json  字符串
	 * @dateTime 2019年10月31日上午10:18:13
	 */
	public static String postNoParam(String url) {
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		HttpPost httpPost = new HttpPost(url);
		// 响应模型
		CloseableHttpResponse response = null;
		String json="";
		try {
			// 由客户端执行(发送)Post请求
			response = httpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();

			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				json=EntityUtils.toString(responseEntity);
				//System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				//System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
	/**
	 * 
	 * 功能：分店接口客户端
	 * @param url 发送请求的路径
	 * @param bsfId  分店编号
	 * @param obj 所有对像的父类
	 * @dateTime 2019年10月31日上午10:14:41
	 */
	public static void post(String url,Integer bsfId,Object obj) {
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 参数
		StringBuffer params = new StringBuffer();
		params.append("bsfId="+bsfId);
		//创建post请求
		HttpPost httpPost = new HttpPost(url+"?"+params);
		// 将user对象转换为json字符串，并放入entity中
		StringEntity entity = new StringEntity(JSON.toJSONString(obj), "UTF-8");
		//System.out.println(entity);
		// post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
		httpPost.setEntity(entity);
		//设置请求头
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
 
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Post请求
			response = httpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
 
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
