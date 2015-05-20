package com.vy.news.adapter;

import java.util.ArrayList;

import com.vy.news.R;
import com.vy.news.model.CommentDrawer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<CommentDrawer > commentitems;
	
	public CommentAdapter(Context context,ArrayList<CommentDrawer> commentitems){
		this.context = context;
		this.commentitems = commentitems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return commentitems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return commentitems.get(position);
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
			 convertView = inflater.inflate(R.layout.comment_item_list,parent,false);
       }
		
		
		
		 TextView name = (TextView) convertView.findViewById(R.id.name);
	        TextView comment = (TextView) convertView.findViewById(R.id.comment);
	        
	        name.setText(commentitems.get(position).getName());
	        comment.setText(commentitems.get(position).getComment());
		return convertView;
	}

}
