package com.vy.news.adapter;

import com.vy.news.database.DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;



public class CommentDataBase {
	static final String DATABASE_NAME = "cmt.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    public static final String DATABASE_CREATE= "create table "+"CMT"+
                                 "( " +"ID"+" integer primary key autoincrement,"+ "TITLE text,NAME text,COMMENT text); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHandler dbHelper;
    public  CommentDataBase(Context _context) 
    {
        context = _context;
        dbHelper = new DataBaseHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  CommentDataBase open() throws SQLException 
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() 
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String title,String name,String comment)
    {
       ContentValues newValues = new ContentValues();
        // Assign values for each row.
       
       newValues.put("NAME",name);
       newValues.put("TITLE",title);
       
       newValues.put("COMMENT",comment);
        // Insert the row into your table
        db.insert("CMT", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+name);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+comment);
       
    }
    public int deleteEntry(String name)
    {
        //String id=String.valueOf(ID);
        String where="NAME=?";
        int numberOFEntriesDeleted= db.delete("COMMENTS", where, new String[]{name}) ;
       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }  
    
    
    
    
    public String getname(String title)
    {
        Cursor cursor=db.query("CMT", null, " TITLE=?", new String[]{title},null,null,null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "";
        }
        cursor.moveToLast();
        String name1= cursor.getString(cursor.getColumnIndex("NAME"));
        cursor.close();
        return name1;                
    }
    
    
    public String getcmt(String title)
    {
        Cursor cursor=db.query("CMT", null, " TITLE=?", new String[]{title},null,null,null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "";
        }
        cursor.moveToLast();
        String nam= cursor.getString(cursor.getColumnIndex("COMMENT"));
        cursor.close();
        return nam;                
    }
    
    
    public void  updateEntry(String name,String comment)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("NAME",name);
        updatedValues.put("COMMENT",comment);
      
        String where="COMMENT = ?";
        db.update("COMMENTS",updatedValues, where, new String[]{comment});               
    }        
}
