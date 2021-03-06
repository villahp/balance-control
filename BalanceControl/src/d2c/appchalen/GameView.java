package d2c.appchalen;

import d2c.appchalen.thread.GameLoop;
import d2c.appchalen.unit.Hand;
import d2c.appchalen.unit.Ruler;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	public long mGameTimeLoop;
	private GameLoop thread;
	private Hand mHand;
	private Ruler mRuler;
	public GameView(Context context) {
		super(context);
		init();
		mHand = new Hand(context);
		mHand.mSpriteHand.init(mHand.mHand);
		mRuler = new Ruler(context);
		mRuler.mSpriteRuler.init(mRuler.mRuler);
	}
	
	public void init()
	{
		getHolder().addCallback(this);
		thread = new GameLoop(getHolder(), this);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		mHand.mSpriteHand.draw(canvas);
		mRuler.mSpriteRuler.draw(canvas);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if(thread.getState() == Thread.State.TERMINATED)
		{
			thread = new GameLoop(getHolder(), this);
		}
		thread.setRunning(true);
		try {
			thread.start();
		} catch (IllegalThreadStateException e) {
			e.printStackTrace();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry)
		{
			try
			{
				thread.join();
				retry = false;
			}
			catch (InterruptedException e){}
		}
	}

}
