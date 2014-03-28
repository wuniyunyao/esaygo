package com.example.easygo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class FlingView extends View {

	private Bitmap bitmap;
	private Bitmap nBitmap;
	private Bitmap fBitmap;

	public int offsetX = 0;
	public int offsetY = 0;
	public int postion = 0;

	private Bitmap[] bitmaps;

	public FlingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlingView(Context context, Bitmap[] bitmaps) {
		super(context);
		
		this.bitmaps = bitmaps;
		bitmap = getBitmap(0);
		nBitmap = getBitmap(1);
	}
	
	public void setBitMaps(Bitmap[] bitmaps){
		this.bitmaps = bitmaps;
		bitmap = getBitmap(0);
		nBitmap = getBitmap(1);
	}
	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		Rect rect = new Rect();
		canvas.drawColor(Color.BLACK);

		// 绘制当前图片
		if (bitmap != null) {
			int left = offsetX;
			int top = offsetY;
			int right = offsetX + Activity_hotelpic.deviceScreenWidth;
			int bottom = offsetY + Activity_hotelpic.deviceScreenHeight;
			rect.set(left, top, right, bottom);
			canvas.drawBitmap(bitmap, null, rect, paint);
		}
		
		// 绘制下一张图
		if (offsetX < 0) {			// 向左滑动
			if (nBitmap != null) {
				int left = Activity_hotelpic.deviceScreenWidth + 15 + offsetX;
				int top = 0;
				int right = left + Activity_hotelpic.deviceScreenWidth;
				int bottom = Activity_hotelpic.deviceScreenHeight;
				rect.set(left, top, right, bottom);
				canvas.drawBitmap(nBitmap, null, rect, paint);
			}
		} else if (offsetX > 0) {		// 向右滑动
			if (fBitmap != null) {
				int left = -Activity_hotelpic.deviceScreenWidth - 15 + offsetX;
				int top = 0;
				int right = left + Activity_hotelpic.deviceScreenWidth;
				int bottom = Activity_hotelpic.deviceScreenHeight;
				rect.set(left, top, right, bottom);
				canvas.drawBitmap(fBitmap, null, rect, paint);
			}
		}
	}

	public void handleScroll(int deltaX) {
		if (deltaX > 0) {
			offsetX -= -deltaX;
		} else {
			offsetX += deltaX;
		}
		invalidate();
	}


	boolean isFling = false;		// 标记是否切换到下（滑动距离超过屏幕宽度的 1/3?
	boolean isFlingRight = false;	// 标记为需要向右滑
	boolean isFlingLeft = false;	// 标记为需要向左滑

	class MyAnimation extends Animation {
		private int tmpOffsetX;

		@Override
		public void initialize(int width, int height, int parentWidth, int parentHeight) {
			tmpOffsetX = offsetX;
			super.initialize(width, height, parentWidth, parentHeight);
			setDuration(500);
			setFillAfter(true);
			setInterpolator(new LinearInterpolator());
		}

		@Override
		protected void applyTransformation(float interpolatedTime, Transformation t) {
//			 Log.i("bb", "offsetX==>"+offsetX);

			if (isFling) {				// 滑动图片时根据方向来变换offsetX大小
				if (tmpOffsetX > 0) {		
					offsetX = (int) ((Activity_hotelpic.deviceScreenWidth - tmpOffsetX) * interpolatedTime + tmpOffsetX);
				} else {				
					offsetX = (int) ((-Activity_hotelpic.deviceScreenWidth - tmpOffsetX) * interpolatedTime + tmpOffsetX);
				}
			} else {				// 不需要变换的情况
				offsetX = (int) (tmpOffsetX * (1 - interpolatedTime));
			}

			invalidate();	
		}
	}

	// 动画结束后需要做工作
	@Override
	protected void onAnimationEnd() {
		if (isFlingRight) {			// 向右滑动，position?
			nBitmap = bitmap;
			bitmap = fBitmap;
			fBitmap = null;
			postion = postion - 1;
		} else if (isFlingLeft) {		// 向左滑动，position?
			fBitmap = bitmap;
			bitmap = nBitmap;
			nBitmap = null;
			postion = postion + 1;
		}
		
		isFlingRight = false;			
		isFlingLeft = false;
		isFling = false;
		offsetX = 0;
		if (fBitmap == null && offsetX == 0) {			// 如果前一张图片为空（向右滑），则重置前一张图片（position - 1?
			if (postion > 0) {
				fBitmap = getBitmap(postion - 1);
			}

		} else if (nBitmap == null && offsetX == 0) {		// 如果后一张图片为空（向左滑），则重置后一张图片（position + 1?
			if (postion < bitmaps.length - 1) {
				nBitmap = getBitmap(postion + 1);
			}
		}
		clearAnimation();			
	}
	
	/** 获得当前位置的图�? */
	public Bitmap getBitmap(int currentPos) {
		if (currentPos > bitmaps.length - 1) {
			return null;
		}
		Bitmap currBitmap = bitmaps[currentPos];
		offsetX = 0;
		offsetY = 0;

		return currBitmap;
	}

	public void onFling(int paramFloat1) {
		if (offsetX > Activity_hotelpic.deviceScreenWidth / 5) {
			if (fBitmap != null) {
				isFling = true;
				isFlingRight = true;
			}
		} else if (offsetX < -Activity_hotelpic.deviceScreenWidth / 5) {
			if (nBitmap != null) {
				isFling = true;
				isFlingLeft = true;
			}
		}
		// 动画效果
		startAnimation(new MyAnimation());
	}

}
