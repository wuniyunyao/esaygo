package com.example.easygo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.easygo.R;
import com.example.easygo.R.drawable;

public class Activity_map extends SherlockActivity {
	View mainActionBarView ;
	/**
	 *  MapView 是地图主控件
	 */
	private MapView mMapView = null;
	/**
	 *  用MapController完成地图控制 
	 */
	private MapController mMapController = null;
	/**
	 *  MKMapViewListener 用于处理地图事件回调
	 */
	MKMapViewListener mMapListener = null;
	//ArrayList<String> data=null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		initMap();
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
		((TextView)mainActionBarView.findViewById(R.id.actionBarReturnText)).setText(R.string.title_map);
		getSupportActionBar().setCustomView(mainActionBarView);
		getSupportActionBar().setIcon(drawable.return_btn_bg);
	}

	public void initMap() {
		DemoApplication app = (DemoApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(getApplicationContext());
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(DemoApplication.strKey,
					new DemoApplication.MyGeneralListener());
		}
		/**
		 * 由于MapView在setContentView()中初始化,所以它需要在BMapManager初始化之后
		 */
		setContentView(R.layout.map_view);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		/**
		 * 获取地图控制器
		 */
		mMapController = mMapView.getController();
		/**
		 * 设置地图是否响应点击事件 .
		 */
		mMapController.enableClick(true);
		/**
		 * 设置地图缩放级别
		 */
		mMapController.setZoom(12);

		/**
		 * 将地图移动至指定点
		 * 使用百度经纬度坐标，可以通过http://api.map.baidu.com/lbsapi/getpoint/index
		 * .html查询地理坐标 如果需要在百度地图上显示使用其他坐标系统的位置，请发邮件至mapapi@baidu.com申请坐标转换接口
		 */
		GeoPoint p;
		double cLat = 39.945;
		double cLon = 116.404;
		Intent intent = getIntent();
		if (intent.hasExtra("x") && intent.hasExtra("y")) {
			// 当用intent参数时，设置中心点为指定点
			Bundle b = intent.getExtras();
			p = new GeoPoint(b.getInt("y"), b.getInt("x"));
		} else {
			// 设置中心点为天安门
			p = new GeoPoint((int) (cLat * 1E6), (int) (cLon * 1E6));
		}

		mMapController.setCenter(p);

		/**
		 * MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		 */
		mMapListener = new MKMapViewListener() {
			@Override
			public void onMapMoveFinish() {
				/**
				 * 在此处理地图移动完成回调 缩放，平移等操作完成后，此回调被触发
				 */
			}

			@Override
			public void onClickMapPoi(MapPoi mapPoiInfo) {
				/**
				 * 在此处理底图poi点击事件 显示底图poi名称并移动至该点 设置过：
				 * mMapController.enableClick(true); 时，此回调才能被触发
				 * 
				 */
				String title = "title";
				if (mapPoiInfo != null) {
					title = mapPoiInfo.strText;
					Toast.makeText(Activity_map.this, title, Toast.LENGTH_SHORT)
							.show();
					mMapController.animateTo(mapPoiInfo.geoPt);
				}
			}

			@Override
			public void onGetCurrentMap(Bitmap b) {
				/**
				 * 当调用过 mMapView.getCurrentMap()后，此回调会被触发 可在此保存截图至存储设备
				 */
			}

			@Override
			public void onMapAnimationFinish() {
				/**
				 * 地图完成带动画的操作（如: animationTo()）后，此回调被触发
				 */
			}

			/**
			 * 在此处理地图载完成事件
			 */
			@Override
			public void onMapLoadFinish() {
				Toast.makeText(Activity_map.this, "地图加载完成", Toast.LENGTH_SHORT)
						.show();

			}
		};
		mMapView.regMapViewListener(DemoApplication.getInstance().mBMapManager,
				mMapListener);
	}
	
	 @Override
	    protected void onPause() {
	    	/**
	    	 *  MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
	    	 */
	        mMapView.onPause();
	        super.onPause();
	    }
	    
	    @Override
	    protected void onResume() {
	    	/**
	    	 *  MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
	    	 */
	        mMapView.onResume();
	        super.onResume();
	    }
	    
	    @Override
	    protected void onDestroy() {
	    	/**
	    	 *  MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
	    	 */
	        mMapView.destroy();
	        super.onDestroy();
	    }
	    
	    @Override
	    protected void onSaveInstanceState(Bundle outState) {
	    	super.onSaveInstanceState(outState);
	    	mMapView.onSaveInstanceState(outState);
	    	
	    }
	    
	    @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    	super.onRestoreInstanceState(savedInstanceState);
	    	mMapView.onRestoreInstanceState(savedInstanceState);
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
