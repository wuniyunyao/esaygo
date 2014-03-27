package com.example.easygo;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.easygo.R;
import com.example.easygo.R.drawable;

public class Activity_hotellist extends SherlockActivity {
	View mainActionBarView ;
	ArrayList<String> data=null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel_lister);
		initActionBar();
		
		
		ListView hotels = (ListView)findViewById(R.id.listView_hotels);
		
		data = new ArrayList<String>();
		for(int i=0;i<10;i++)
		{
			data.add("object:"+i);
		}
		
		hotels.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				convertView = LayoutInflater.from(Activity_hotellist.this).inflate(R.layout.item_hotel, null);
				convertView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Activity_hotellist.this, "选项还在开发中", Toast.LENGTH_LONG).show();
						Intent intent = new Intent(Activity_hotellist.this,Activity_hotelpage.class);
						startActivity(intent);
						overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
					}
				});
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
	}
	private void initActionBar(){
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));
		mainActionBarView = LayoutInflater.from(this).inflate(R.layout.list_action_bar, null);
		((TextView)mainActionBarView.findViewById(R.id.actionBarReturnText)).setText(R.string.title_searchhotel);
		getSupportActionBar().setCustomView(mainActionBarView);
		getSupportActionBar().setIcon(drawable.return_btn_bg);
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;		
		}
		return super.onOptionsItemSelected(item);
	}
}
