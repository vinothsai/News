package com.vy.news;







import java.util.ArrayList;

import com.vy.news.adapter.NavDrawerListAdapter;
import com.vy.news.model.NavDrawerItem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	String[] navMenuTitles;
//	String[] actiontitle;
	Toast toast;
	SharedPreferences shareddata;
	Editor editor;
		// used to store app title
		private CharSequence mTitle;
		private ArrayList<NavDrawerItem> navDrawerItems;
		private NavDrawerListAdapter adapter;
		 String pref;
		 int mState;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
    	navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
      //  actiontitle=getResources().getStringArray(R.array.draw_items);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8]));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[9]));
       
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
	
		shareddata = getSharedPreferences("MyPREFERENCES",Context.MODE_PRIVATE);
	
	//	editor.putString("USER_NAME", "name");
	//	editor.putString("PASSWORD","pwd");
		
		pref=shareddata.getString("USER_NAME", ""); 
		if(!pref.equals(""))
		{
		mState = 1; // to hide or mState = 0; to show
		invalidateOptionsMenu();
		}
		else if(pref.equals(""))
		{
		mState = 0; // to hide or mState = 0; to show
		invalidateOptionsMenu();
		}
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			@SuppressLint("NewApi") public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			@SuppressLint("NewApi") public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
		
    }
	@SuppressLint("NewApi") private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		
		case 0:
			fragment = new IndiaFragement();
			break;
		case 1:
			fragment = new WorldFragement();
			break;
		case 2:
			fragment = new BusinessFragement();
			break;
		case 3:
			fragment = new TechnologyFragement();
			break;
		case 4:
			fragment = new EntertainmentFragement();
			break;
		case 5:
			fragment = new SportsFragement();
			break;
		case 6:
			fragment = new ScienceFragement();
			break;
		case 7:
			fragment = new HealthFragement();
			break;
		case 8:
			fragment = new MoreTopStoriesFragement();
			break;
		case 9:
			fragment = new SpotlightFragment();
			break;
		default:
			break;
		}
		

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@SuppressLint("NewApi") @Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	private class SlideMenuClickListener implements
	ListView.OnItemClickListener {
@Override
public void onItemClick(AdapterView<?> parent, View view, int position,
		long id) {
	// display view for selected nav drawer item
	displayView(position);
}
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.mainmenu, menu);
if (mState == 1) //1 is true, 0 is false
{
    //hide only option 2
        menu.getItem(2).setVisible(false);
        menu.getItem(4).setVisible(true);
}
else
{
 menu.getItem(2).setVisible(true);
 menu.getItem(4).setVisible(false);
}
return true;
}


@Override
public boolean onOptionsItemSelected(MenuItem item) {
// toggle nav drawer on selecting action bar app icon/title
if (mDrawerToggle.onOptionsItemSelected(item)) {
	return true;
}
// Handle action bar actions click
switch (item.getItemId()) {
case R.id.Settings:
	return true;
case R.id.Refresh:

	
	return true;
case R.id.Login:
	
	
	Intent i=new Intent(getApplicationContext(),Login.class);
	startActivity(i);
	
	
	return true;	
case R.id.Register:
	
	Intent intent=new Intent(getApplicationContext(),Register.class);
	startActivity(intent);
	
	return true;
case R.id.Lgout:
	
	shareddata = getSharedPreferences("MyPREFERENCES",Context. MODE_PRIVATE); 
         SharedPreferences.Editor editor=shareddata.edit();
         
       
             editor.putString("USER_NAME", "");
             editor.putString("PWD", "");
             editor.commit();
             System.out.println("@@@@@@@@@@@@@logoutfunction@@@@@@@@@"+shareddata.getAll());
         	
          //   adapter=new CommentAdapter(getApplicationContext(), cd);
			//listcomment.setAdapter(adapter);
        
          System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+shareddata.getAll());
	return true;
case R.id.Exit:
	
	 finish();
     System.exit(0);
	return true;	
default:
	return super.onOptionsItemSelected(item);
}
}


@Override
protected void onPostCreate(Bundle savedInstanceState) {
	super.onPostCreate(savedInstanceState);
	// Sync the toggle state after onRestoreInstanceState has occurred.
	mDrawerToggle.syncState();
}

//local method
private void showToast(){
Log.e("Vinoth","Sai");
}

@Override
public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	// Pass any configuration change to the drawer toggls
	mDrawerToggle.onConfigurationChanged(newConfig);
}
}
