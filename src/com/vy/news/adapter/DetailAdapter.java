package com.vy.news.adapter;





import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.vy.news.R;
import com.vy.news.model.DetailItem;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<DetailItem > detailitems;
	  ImageView imgIcon;
	   ImageLoadBitmapTask imageLoadBitmapTask;
	   Bitmap myBitmap;
	
	public DetailAdapter(Context context,ArrayList<DetailItem> detailitems){
		this.context = context;
		this.detailitems = detailitems;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return detailitems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return detailitems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			 LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			 convertView = inflater.inflate(R.layout.drawer_news_item,parent,false);
        }
         
        imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView Title = (TextView) convertView.findViewById(R.id.title);
        TextView Details = (TextView) convertView.findViewById(R.id.details);
         
        //String url="http://t2.gstatic.com/images?q=tbn: ANd9GcSihBq3qfsQ0DbJdhSGsAdDPPV-txot6_6nzhhP1qs2akZEizYbJVLcnfLRe0H5CBUuKb26aJQU";
        String url=detailitems.get(position).getIcon();
      
        /*
    try {
	url = URLDecoder.decode(url, "UTF-8");
	 System.out.println("****url****");
    }
    catch (UnsupportedEncodingException e) {

    }*/
        imageLoadBitmapTask=new ImageLoadBitmapTask(url, imgIcon);
		imageLoadBitmapTask.execute();
		//new ImageLoadBitmapTask((ImageView) findViewById(R.id.icon)).execute(imgIcon[position]);
		
       Title.setText(detailitems.get(position).getTitle());
        System.out.println("****Title is ******"+detailitems.get(position).getTitle());
       Details.setText(detailitems.get(position).getContentSnippet());
        System.out.println("****contentSnippet is******"+detailitems.get(position).getContentSnippet());
		
        System.out.println("******image is*******"+url);
  
		return convertView;
	}
	
	
	public class ImageLoadBitmapTask extends AsyncTask<String, String, Bitmap> {
		
		
        String url;//="http://t1.gstatic.com/images?q=tbn:%20ANd9GcTnQtS1_9_7j4ZB0rUV_6y4ySH5C36NudwL32y9jEmJLUuglmtBN5ZfdZZm3RvX-mydL6ZJO5w";
        ImageView image;
      
       public ImageLoadBitmapTask(String url, ImageView image) {
			// TODO Auto-generated constructor stub
			this.url=url;
			this.image=image;
		}
       @Override
       protected void onPreExecute() {
           Log.i("ImageLoadTask", "Loading image...");
       }
       

    	

		@Override
		protected Bitmap doInBackground(String...params) {
			// TODO Auto-generated method stub
	
				  try {
			        	/* URL aURL = new URL(url);
			        	 System.out.println("--url--"+url);			             
			        	 System.out.println("--urlllllllllllllllllllllllll--"+aURL);
			        	 
			            HttpURLConnection conn = (HttpURLConnection)aURL.openConnection();
			             conn.setDoInput(true);
			             conn.connect();
                           conn.setRequestMethod(url);
			            // System.out.println("----connection--"+conn);
			            
			             InputStream is = conn.getInputStream();
			             BufferedInputStream bis = new BufferedInputStream(is);
			             myBitmap = BitmapFactory.decodeStream(bis);
			             System.out.println("----buffered reader-------"+bis);
			        	 
			             bis.close();
			             is.close();*/
					  URL aurl = new URL(url);
					  HttpURLConnection conn = (HttpURLConnection)aurl.openConnection();
					  conn.setDoInput(true);
					  conn.connect();
					 
					  Bitmap bmp = BitmapFactory.decodeStream(conn.getInputStream());
					//  imgIcon.setImageBitmap(bmp);
			             System.out.println("--bitmap--"+bmp);
				           return bmp; 
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				return null;
		}
				/*   Bitmap bitmap=null;
			
				  URL  aurl = new URL(url);
			    System.out.println("++++++++++++++after decode++++++++++++++"+url);
			       
				   System.out.println("!!!!!!!!!!!!!!!!!!!!url is !!!!!!!!!!!!!"+aurl);
				   HttpURLConnection connection = (HttpURLConnection) aurl.openConnection();
				  
				//   System.out.println("++++++++++++++after decode++++++++++++++"+url);
				   connection.connect();
				   connection.setRequestMethod("GET");
				 connection.setDoInput(true);
				  
				 //  connection.
				   System.out.println("!!!!!!!!!!!!!!!!!!!!conn is !!!!!!!!!!!!!"+connection);
			      
				   InputStream iss =(connection.getInputStream());
				   //OutputStream oss=(connection.getOutputStream());
				//   BufferedReader reader = new BufferedReader(iss);
				   
				   System.out.println("!!!!!!!!!!!!!!!!!!!!inputStream is !!!!!!!!!!!!!"+iss);
				   bitmap = BitmapFactory.decodeStream(iss);
				  
				
				   System.out.println("!!!!!!!!!!!!!!!!!image is !!!!!!!!!!!!!!!"+bitmap);
				 //  return response.getEntity().getContent();
				//   oss.close();
				   iss.close();
				   
				   return bitmap;
				  } 
			  catch (IOException e) {
				   e.printStackTrace();
				   Log.e("Exception", e.getMessage());
				   return null;
				  }*/
			  

	
		  @Override
		    protected void onPostExecute(Bitmap result) {
		//if (result != null) {
		    					imgIcon.setImageBitmap(result);
		    				//} else {
		    					//imgIcon.setImageDrawable(imgIcon.getContext().getResources()
		    							//.getDrawable(R.drawable.success));
		    				//}
		    			
System.out.println("--Bitmap--"+result);
		        
		       
		    }
	}
	 public String method(String str) {
		    if (str.length() > 0 && str.charAt(str.length()-1)=='"') {
		      str = str.substring(0, str.length()-1);
		      System.out.println("----after removing---"+str);
		    }
		    return str;
		}
	

}
