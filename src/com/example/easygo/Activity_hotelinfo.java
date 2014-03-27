package com.example.easygo;

import java.util.ArrayList;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.easygo.R;
import com.example.easygo.R.drawable;

public class Activity_hotelinfo extends SherlockActivity {
	View mainActionBarView ;
	ArrayList<String> data=null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel_info);
		initActionBar();
		data = new ArrayList<String>();
		
	}
	private void initActionBar(){
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));
		mainActionBarView = LayoutInflater.from(this).inflate(R.layout.list_action_bar, null);
		((TextView)mainActionBarView.findViewById(R.id.actionBarReturnText)).setText(R.string.title_hotelinfo);
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

