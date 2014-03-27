package com.example.easygo;


import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.example.easygo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends SherlockActivity {
	
	View mainActionBarView ;
	ArrayList<String> data=null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initActionBar();
		Button btn_city = (Button)findViewById(R.id.button_city);
		Button btn_checkinDate = (Button)findViewById(R.id.button_date);
		Button btn_checkoutdate = (Button)findViewById(R.id.button_checkout);
		Button btn_search = (Button)findViewById(R.id.button_search);
		Button btn_cancel = (Button)findViewById(R.id.button_cancel);
		Button btn_ok = (Button)findViewById(R.id.button_ok);
		
		final FrameLayout frame_date = (FrameLayout)findViewById(R.id.framelayout_date);
		final FrameLayout frame_checkoutdata = (FrameLayout)findViewById(R.id.framelayout_checkoutdate);
		RelativeLayout mainlayout = (RelativeLayout)findViewById(R.id.mainlayout);
		
		ListView list_checkin = (ListView)findViewById(R.id.listview_checkindate);
		ListView list_days = (ListView)findViewById(R.id.listview_days);
		ListView list_checkout = (ListView)findViewById(R.id.listview_checkoutdate);
		
		data = new ArrayList<String>();
		for(int i=0;i<10;i++)
		{
			data.add("object:"+i);
		}
		list_checkin.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				convertView = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null);
				TextView date = (TextView)convertView.findViewById(android.R.id.text1);
				date.setText(data.get(position));
				date.setTextColor(android.graphics.Color.BLACK);
				return convertView;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return data.get(position);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return data.size();
			}
		});
		
		list_days.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				convertView = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null);
				TextView days = (TextView)convertView.findViewById(android.R.id.text1);
				days.setText(data.get(data.size()-position-1));
				days.setTextColor(android.graphics.Color.BLACK);
				return convertView;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return data.get(position);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return data.size();
			}
		});
		
		list_checkout.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				convertView = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null);
				TextView checkout = (TextView)convertView.findViewById(android.R.id.text1);
				checkout.setText(data.get(position));
				checkout.setTextColor(android.graphics.Color.BLACK);
				return convertView;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return data.get(position);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return data.size();
			}
		});

		btn_checkinDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame_date.setVisibility(View.VISIBLE);
			}
		});
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame_date.setVisibility(View.GONE);
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//TODO 确认日期后的操作
				frame_date.setVisibility(View.GONE);
			}
		});

		btn_checkoutdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame_checkoutdata.setVisibility(View.VISIBLE);
			}
		});
		
		mainlayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame_checkoutdata.setVisibility(View.GONE);
				frame_date.setVisibility(View.GONE);
			}
		});
		
		btn_city.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Activity_citylist.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});
		
		btn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(MainActivity.this,Activity_hotellist.class);
				startActivity(intent2);
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});
	}

	
	
	
	private void initActionBar(){
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));
		
		mainActionBarView = LayoutInflater.from(this).inflate(R.layout.main_action_bar, null);
		((TextView)mainActionBarView.findViewById(R.id.actionBarReturnText)).setText(R.string.search_hotel);
		getSupportActionBar().setCustomView(mainActionBarView);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		  MenuItem login = menu.add("login");
	        login.setIcon(R.drawable.r_icon_search_list);
		  	login.setTitle("选项");
	        login.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	        login.setOnMenuItemClickListener(new OnMenuItemClickListener() {
	            @Override
	            public boolean onMenuItemClick(MenuItem item) {
	            	Toast.makeText(MainActivity.this, "选项还在开发中", Toast.LENGTH_LONG).show();
	            	return true;
	            }
	        });
	      /* SubMenu more=menu.addSubMenu("more");
	       more.add("分享给好友");
	       more.add("访问作者微博");
	       more.add("注销账号");
	       more.add("关于");
	       MenuItem morebtn = more.getItem();
	       //morebtn.setIcon(R.drawable.menu_apprec);
	       morebtn.setTitle("…");
	       morebtn.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	       more.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener(){
	    	   @Override
	            public boolean onMenuItemClick(MenuItem item) {
	                onShare();
	            	return true;
	            }
	       });*/
		return super.onCreateOptionsMenu(menu);
	}
}
