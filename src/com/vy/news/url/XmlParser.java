package com.vy.news.url;



import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vy.news.adapter.DetailAdapter;
import com.vy.news.model.DetailItem;


import android.text.Html;

public class XmlParser {

	//List<String> newstitle=new ArrayList<String>();
	//List<String> newsContent=new ArrayList<String>();
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
				System.out.println("-------Title is--------- "+title.getTextContent());
				
				//newstitle.add(title.getTextContent());
					Node description = itemSubList.item(getNodeIndex(itemSubList,"description"));
					System.out.println("--------DESCRIPTION------"+description.getTextContent());
				//	newsContent.add(description.getTextContent());
				//	Node contentSnippet=itemSubList.item(getNodeIndex(itemSubList, "contentSnippet"));
				//	System.out.println("---------contentSnippet---------"+contentSnippet.getTextContent());
					
					
					int startIndex = description.getTextContent().indexOf("<img src")+10;
					 String substring = description.getTextContent().substring(startIndex, startIndex+200);
					 int subendIndex = substring.indexOf("alt");
					 int endIndex = startIndex + subendIndex;
					 try{
					  icon = description.getTextContent().substring(startIndex,endIndex);
					 System.out.println("--------images is---------"+icon);
					 
					 icon= method(icon);
					 }catch (StringIndexOutOfBoundsException e){
						 icon="empty";
					 }
					
					 
					 int startIndex1 = description.getTextContent().indexOf("<b>")+3;
				 String substring1 = description.getTextContent().substring(startIndex1, startIndex1+200);
			 int subendIndex1 = substring1.indexOf("</b>");
					 int endIndex1 = startIndex1 + subendIndex1;
					String contentSnippet = description.getTextContent().substring(startIndex1,endIndex1);
				 System.out.println("------contentsnippet-------"+contentSnippet);
				
					// String without_html = Html.fromHtml(description.getTextContent().toString()).toString();
					// System.out.println("ggggg"+without_html);
					 
					 detailitems.add(new DetailItem(icon,title.getTextContent(),contentSnippet,description.getTextContent()));
					 
				iteration++;
			}
				
		}
		System.out.println("---item Iteration---"+iteration);
		
	
		return  detailitems;
	}
	
	public String method(String str) {
	    if (str.length() > 0 && str.charAt(str.length()-1)=='"') {
	      str = str.substring(0, str.length()-1);
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

	public Document getDomElement(Document dom) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
