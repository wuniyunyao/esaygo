package com.example.easygo;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.easygo.R.drawable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.TextView;

public class Activity_hotelpic extends SherlockActivity implements OnGestureListener {
	View mainActionBarView ;
	
	private FlingView flingView;
	private GestureDetector myGesture;

	public static int deviceScreenWidth;
	public static int deviceScreenHeight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 获得手机的宽带和高度像素单位为px
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		deviceScreenWidth   = dm.widthPixels;
		deviceScreenHeight = dm.heightPixels;
		Log.i("mActivity", "deviceScreenWidth = " + deviceScreenWidth + "; deviceScreenHeight = " + deviceScreenHeight);

		Bitmap[] bitmaps = {
				BitmapFactory.decodeResource(getResources(), R.drawable.img1),
				BitmapFactory.decodeResource(getResources(), R.drawable.img2),
				BitmapFactory.decodeResource(getResources(), R.drawable.img3),
				BitmapFactory.decodeResource(getResources(), R.drawable.img4),
				BitmapFactory.decodeResource(getResources(), R.drawable.img5) };

		myGesture = new GestureDetector(this);
		flingView = new FlingView(this, bitmaps);
		setContentView(flingView);
		initActionBar();
	}
	
	private void initActionBar(){
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));
		mainActionBarView = LayoutInflater.from(this).inflate(R.layout.list_action_bar, null);
		((TextView)mainActionBarView.findViewById(R.id.actionBarReturnText)).setText(R.string.title_pic);
		getSupportActionBar().setCustomView(mainActionBarView);
		getSupportActionBar().setIcon(drawable.return_btn_bg);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			flingView.onFling(0);			// 手指抬起后，重置滑动距离offsetX = 0
			break;
		}

		return myGesture.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		Log.i("mActivity", "velocityX = " + velocityX + "; velocityY = " + velocityY);
		flingView.onFling((int) - velocityX);
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		Log.i("mActivity", "distanceX = " + distanceX + "; distanceY = " + distanceY);
		flingView.handleScroll(-1 * (int) distanceX);
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
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