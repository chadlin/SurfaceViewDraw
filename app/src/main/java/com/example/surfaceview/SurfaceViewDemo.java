package com.example.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class SurfaceViewDemo extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder mSurfaceHolder;
	private Canvas mCanvas;
	private Paint paint;

	public SurfaceViewDemo(Context context) {
		this(context,null,0);
		Log.d("XXXXX", "=======SurfaceVIewDemo========");
	}

	public SurfaceViewDemo(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SurfaceViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init(){
		Log.d("XXXXX", "=======SurfaceVIewDemo=init=======");
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setKeepScreenOn(true);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
		paint.setStyle(Paint.Style.STROKE);
	}

	@Override
	public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
		Log.d("XXXXX", "=======surfaceCreated========");
		new Thread(new Runnable() {
			@Override
			public void run() {
				draw();
			}
		}).start();
	}

	private void draw(){
		try {
			Log.d("XXXXX", "=======draw========");
			mCanvas = mSurfaceHolder.lockCanvas();
			mCanvas.drawCircle(200,200,100,paint);
			mCanvas.drawCircle(100,100,50,paint);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mCanvas != null) {
				mSurfaceHolder.unlockCanvasAndPost(mCanvas);
			}
		}
	}

	@Override
	public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
		Log.d("XXXXX", "=======surfaceChanged========");

	}

	@Override
	public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
		Log.d("XXXXX", "=======surfaceDestroyed========");

	}
}
