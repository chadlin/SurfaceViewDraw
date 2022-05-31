package com.example.surfaceview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

	private lateinit var mSurfaceHolder: SurfaceHolder
	private lateinit var mCanvas: Canvas
	private lateinit var paint: Paint
	private lateinit var mSurfaceView: SurfaceView
	private lateinit var mFullScreenBitmap: Bitmap

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		mSurfaceView = findViewById(R.id.surfaceView)
		mFullScreenBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ALPHA_8)
		mSurfaceHolder = mSurfaceView.holder
		mSurfaceHolder.addCallback(object : SurfaceHolder.Callback {
			override fun surfaceCreated(p0: SurfaceHolder) {
				Log.d("XXXXX", "=======surfaceCreated========")
				Thread { draw() }.start()
			}

			override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
				Log.d("XXXXX", "=======surfaceChanged========")
			}

			override fun surfaceDestroyed(p0: SurfaceHolder) {
				Log.d("XXXXX", "=======surfaceDestroyed========")
			}

		})
		SurfaceViewInit()
	}

	private fun SurfaceViewInit() {
		Log.d("XXXXX", "=======SurfaceVIewDemo=init=======")
		paint = Paint(Paint.ANTI_ALIAS_FLAG)
		paint.color = Color.RED
		paint.strokeWidth = 5f
		paint.style = Paint.Style.STROKE
	}


	fun draw() {
		try {
			Log.d("XXXXX", "=======draw========mSurfaceHolder = $mSurfaceHolder")
			mCanvas = mSurfaceHolder.lockCanvas()
			mCanvas.drawColor(Color.BLUE)
			mCanvas.drawCircle(200f, 200f, 100f, paint)
			mCanvas.drawCircle(100f, 100f, 50f, paint)
		} catch (e: Exception) {
			e.printStackTrace()
		} finally {
			mSurfaceHolder.unlockCanvasAndPost(mCanvas)
		}
	}
}