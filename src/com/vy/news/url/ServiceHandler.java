package com.vy.news.url;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class ServiceHandler {
	  public ServiceHandler() { }
	    
	    
	    public String postUrl(String url) {
		       
	    	System.out.println("--THE DISTANCE URL___"+url);
	    	        try {
	    	        	String response = new String();
	                	
	                //	HttpEntity requestEntity = new UrlEncodedFormEntity(nameValuePairs);
	                	HttpClient httpClient=new DefaultHttpClient();
	                	HttpPost httpPost = new HttpPost(url);
	                	//httpPost.setHeader(, value)
	                //	httpPost.setEntity(requestEntity);
	                	HttpResponse httpResponse = httpClient.execute(httpPost);
	                	HttpEntity responseEntity = httpResponse.getEntity();
	                	if(responseEntity!=null) {
	                	    response = EntityUtils.toString(responseEntity);
	                	}
	                	System.out.println("--RESPONSE-"+response);
	    	           
	    	            return response;
	    	        } catch (Exception e) {
	    	            e.printStackTrace();
	    	        }
	    	        return null;
	    	    }
	    

	    public String postUrl(String url,List<NameValuePair> nameValuePairs) {
	       
	System.out.println("--THE DISTANCE URL___"+url);
	        try {
	        	String response = new String();
          	
          	HttpEntity requestEntity = new UrlEncodedFormEntity(nameValuePairs);
          	HttpClient httpClient=new DefaultHttpClient();
          	HttpPost httpPost = new HttpPost(url);
         
          	httpPost.setEntity(requestEntity);
          	HttpResponse httpResponse = httpClient.execute(httpPost);
          	HttpEntity responseEntity = httpResponse.getEntity();
          	if(responseEntity!=null) {
          	    response = EntityUtils.toString(responseEntity);
          	}
          	System.out.println("--RESPONSE-"+response);
	           
	            return response;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
}
