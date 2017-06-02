package live.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

public class HttpUtil {
	
	public static Logger logger = LogManager.getLogger(HttpUtil.class);
	
	public static String doGet(String url) throws IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader(HttpHeaders.USER_AGENT, 
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; 360SE)");
		CloseableHttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
	    response.close();
	    httpclient.close();
	    return content;
	}
	
	public static String doPostForm(String url, Map<String, String> postData, String encode ) throws IOException{  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.USER_AGENT, 
				"Mozilla/5.0 (Linux; U; Android 4.3; en-us; sdk Build/MR1) AppleWebKit/536.23 (KHTML, like Gecko) Version/4.3 Mobile Safari/536.23");
	    List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	    for ( String key : postData.keySet() ){
	    	nvps.add( new BasicNameValuePair( key, postData.get(key) ) );
	    }
	    httpPost.setEntity(new UrlEncodedFormEntity(nvps, encode));
	    
	    CloseableHttpResponse response = httpclient.execute(httpPost);
        
        HttpEntity entity = response.getEntity(); 
        String content = EntityUtils.toString(entity);   
        EntityUtils.consume(entity);
        response.close();
        httpclient.close();
        logger.info(content);
        return content;
	}
	
	
	public static String doPostUploadFileAndParameters(String url, File file, Map<String, String> postData, String fileParameterName) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		// 把文件转换成流对象FileBody
		FileBody bin = new FileBody(file);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		//放入上传文件
		builder.addPart("fileParameterName", bin);
		for ( String key : postData.keySet() ){
			builder.addPart(key, new StringBody(postData.get(key), ContentType.create("text/plain", Consts.UTF_8)));
		}
		HttpEntity entity = builder.build();
		httpPost.setEntity(entity);
		//执行请求
		CloseableHttpResponse response = httpclient.execute(httpPost);
		//获取相应对象
		entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		response.close();
		httpclient.close();
		logger.info(content);
		return content;
	}
	
	public static String doPostJson(String url, Map<String, Object> postData, String encode ) throws IOException{  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.USER_AGENT, 
				"Mozilla/5.0 (Linux; U; Android 4.3; en-us; sdk Build/MR1) AppleWebKit/536.23 (KHTML, like Gecko) Version/4.3 Mobile Safari/536.23");

		StringEntity strEntity = new StringEntity(JSON.toJSONString(postData), encode);
		strEntity.setContentType("application/json");  
	    httpPost.setEntity(strEntity);
	    
	    CloseableHttpResponse response = httpclient.execute(httpPost);
        
        HttpEntity entity = response.getEntity(); 
        String content = EntityUtils.toString(entity);   
        EntityUtils.consume(entity);
        response.close();
        httpclient.close();
        return content;
	}
}
