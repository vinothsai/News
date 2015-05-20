package com.vy.news;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import com.vy.news.adapter.DetailAdapter;
import com.vy.news.model.DetailItem;
import com.vy.news.url.ServiceHandler;
import com.vy.news.url.XmlParser;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class SpotlightFragment extends Fragment {
	protected static final ContextWrapper context = null;
	  private ArrayList<DetailItem> detailitems;
	
List<String> Icon=new ArrayList<String>();
String Title;
	
List<String> newstitle=new ArrayList<String>();
List<String> newsContent=new ArrayList<String>();
		public DetailAdapter adapter;
		ListView list;
		ProgressDialog Dialog;
	ServiceHandler servicehandler;
		 Document doc = null;
		  XmlParser xmlParser;
		  
		private static String SPOTLIGHT_URL="http://news.google.co.in/news?pz=1&cf=all&ned=in&hl=en&topic=ir&output=rss";
		
		
		Getnewsdetails getnewsdetails;
		    // Hashmap for ListView
		    HashMap<String,String> map;
		    ArrayList<HashMap<String, String>> newslist;
		  
	public SpotlightFragment(){}
	
	@Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {

      View rootView = inflater.inflate(R.layout.fragment_spotlight, container, false);
      
      newslist = new ArrayList<HashMap<String, String>>();
      map=new HashMap<String,String>();
      list=(ListView)rootView.findViewById(R.id.spotlistView1);
   
      System.out.println("++++++++++++++++++++++++++testing1++++++++++++++");
      new Getnewsdetails().execute();
      
       System.out.println("++++++++++++++++++++++++++testing2++++++++++++++");
        
       xmlParser= new XmlParser();
		
		
      list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
Intent intent=new Intent(getActivity(),Detail.class);
				
					
				
				intent.putExtra("title", newstitle.get(position).toString());
				intent.putExtra("details", newsContent.get(position).toString());
				
				startActivity(intent);
			}
		});
   
		

      return rootView;
      
  }
	 private class Getnewsdetails extends AsyncTask<String, Void, ArrayList<DetailItem>> {
		 
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            // Showing progress dialog
	            
	            Dialog = ProgressDialog.show(getActivity(), "Please Wait",
	                "Loading...");
	               // Dialog = new ProgressDialog(IndiaFragement.this);
	               Dialog.setMessage("Loading Spotlight News...");
	               Dialog.show();
	        }
	 
	        protected ArrayList<DetailItem> doInBackground(String... params) {
	            // Creating service handler class instance
	        	  String responseData = "";
	        	  Document doc = null;
	        	
	        	  
		            
		            
		            
		            
		          
	                DefaultHttpClient client = new DefaultHttpClient();
	                HttpGet httpGet = new HttpGet(SPOTLIGHT_URL);
	                try {
	                	   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            	          DocumentBuilder db = dbf.newDocumentBuilder();
	                  HttpResponse execute = client.execute(httpGet);
	                  InputStream content = execute.getEntity().getContent();
	                  System.out.println("--PASRE CONTENT--"+content);
	        			   doc = db.parse(content);
	        			System.out.println("--PASRE CONTENT DOC--"+doc.toString());
	        			
	        		
	        		detailitems =parseDocument(doc);
	        			System.out.println("////////////////"+detailitems);
	                  }catch (ParserConfigurationException e1) {
	                	  e1.printStackTrace();
	                }catch (Exception e) {
	                  e.printStackTrace();
	                }
	               
		      
	              return detailitems;
	            

	        }
	 
	      
	        	
	        protected void onPostExecute(ArrayList<DetailItem> detailitems) {
	           
	            // Dismiss the progress dialog
	            if (Dialog.isShowing())
	                Dialog.dismiss();
	         
	           //Updating parsed JSON data into ListView
	             
	          
	            adapter = new DetailAdapter(getApplicationContext(),detailitems);
	            
	          
	               list.setAdapter(adapter); 

	          
	          
	        	
	        }
	        	
	        	
	           
	        

			private Context getApplicationContext() {
				// TODO Auto-generated method stub
				return null;
			}
	 
	    

	
	 
	 }
	 public ArrayList<DetailItem> parseDocument(Document dom){
			//get the root element
			ArrayList<DetailItem> detailitems =   new ArrayList<DetailItem>();;
			Element docEle = dom.getDocumentElement();

			//get a nodelist of elements
			NodeList nl = docEle.getElementsByTagName("channel");
			System.out.println("--Employee Node Length-"+nl.getLength());
			NodeList n2=nl.item(0).getChildNodes();
			System.out.println("--Employee Node Length-"+n2.getLength());
		//	NodeList n3=n2.item(0).getChildNodes();
		//	System.out.println("---------------------"+n3.getLength());
			Node itemNode = n2
					.item(getNodeIndex(n2, "item"));
			int iteration = 0;
			for (int i = 0; i < n2.getLength(); i++) {
				if (n2.item(i).getNodeName().equals("item")){
					
					String icon ;
					
					 System.out.println("--NODE NAME--"+n2.item(i).getNodeName());
					NodeList itemSubList=n2.item(i).getChildNodes();
				
					Node title = itemSubList.item(getNodeIndex(itemSubList,"title"));
					System.out.println("-------Title is-----fghfg---- "+title.getTextContent());
					// add to list
					newstitle.add(title.getTextContent());
					
						Node description = itemSubList.item(getNodeIndex(itemSubList,"description"));
					
						
						String without_html = Html.fromHtml(description.getTextContent().toString()).toString();
						System.out.println("--------full details------"+without_html);
						//add to list
						newsContent.add(without_html);
				
						
						
					int startIndex = description.getTextContent().indexOf("<img src")+10;
						 String substring = description.getTextContent().substring(startIndex, startIndex+150);
						 int subendIndex = substring.indexOf("alt");
						 int endIndex = startIndex + subendIndex;
						 try{
						  icon = "http:"+description.getTextContent().substring(startIndex,endIndex);
					//	 System.out.println("--------images is---------"+icon);
						 
						 icon= method(icon);
						 }catch (StringIndexOutOfBoundsException e){
							 icon="empty";
						 }
						
						 
						 int startIndex1 = description.getTextContent().indexOf("<b>")+3;
					 String substring1 = description.getTextContent().substring(startIndex1, startIndex1+200);
				 int subendIndex1 = substring1.indexOf("</b>");
						 int endIndex1 = startIndex1 + subendIndex1;
						String contentSnippet = description.getTextContent().substring(startIndex1,endIndex1);
					 System.out.println("------contentsnippet----fgh---"+contentSnippet);
					
						 String with_html = Html.fromHtml(description.getTextContent().toString()).toString();
				//		 System.out.println("ggggg"+without_html);
						 
						 detailitems.add(new DetailItem(icon,title.getTextContent(),description.getTextContent(),contentSnippet));
						 
					iteration++;
				}
					
			}
			System.out.println("---item Iteration---"+iteration);
			
		 
			return  detailitems;
		}
		
		public String method(String str) {
		    if (str.length() > 0 && str.charAt(str.length()-2)=='"') {
		      str = str.substring(0, str.length()-2);
		      System.out.println("----after removing---"+str);
		    }
		    return str;
		}
		
		private int getNodeIndex(NodeList nl, String nodename) {
			for (int i = 0; i < nl.getLength(); i++) {
				if (nl.item(i).getNodeName().equals(nodename)){
					return i;
				}
					
			}
			return -1;
		}

		

}
