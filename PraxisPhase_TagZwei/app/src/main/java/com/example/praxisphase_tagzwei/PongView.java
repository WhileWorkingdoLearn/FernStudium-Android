package com.example.praxisphase_tagzwei;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class PongView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String LOG_PONGVIEW = "PongView";
    private AnimationThread animationThread = null;
    private static final float paddleXPos = 20;
    private Canvas canvas;

    private float xPos, yPos, xDirect, yDirect, paddleYPos, paddleWidth = 10;

    private int ballRadius = 20, ballColor = 0xFFFF0000;

    private int paddleHeight = 200, paddleColor = 0xFFFFFFFF, backgroundColor = 0xFF000000;

    public PongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(LOG_PONGVIEW,"pong view created");
        getHolder().addCallback(this);
        xPos = 10;
        xDirect = -5;
        yDirect = 2;
        paddleYPos= ((this.getY() - 10) /2);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float firstTouchY = 0, startPosPaddle = 0;
        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:{
                startPosPaddle = paddleYPos;
                firstTouchY = event.getY();
                break;}
            case MotionEvent.ACTION_MOVE:{
                firstTouchY = event.getY();
                paddleYPos =   startPosPaddle + firstTouchY;
                break;}
            case MotionEvent.ACTION_UP:{break;}
            case MotionEvent.ACTION_CANCEL:{break;}
        }
        return true;
    }


    protected void screenDraw(Canvas canvas) {
        canvas.drawColor(0xFF000000); // löscht das vorherige Bild, indem alle Bildpunkte auf Schwarz gesetzt werden
        //canvas.drawBitmap(bitmap, xPos, yPos, null); // zeichnet das Bild
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(paddleColor);
        canvas.drawRect(paddleXPos,paddleYPos,paddleXPos + paddleWidth,paddleYPos +paddleHeight,paint);

        canvas.drawCircle(xPos,yPos,ballRadius,paint);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //Thread definieren
        Log.d("PongView", "started Thread");
        if(animationThread == null){
            animationThread = new AnimationThread(holder);
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        //
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if(animationThread != null){
            animationThread.interrupt();
        }
    }

    private class AnimationThread extends Thread {
        private static final String LOG_PONGVIEW = "PongViewThread";
        private SurfaceHolder surfaceHolder;
        private AnimationThread(SurfaceHolder surfaceHolder){
            this.surfaceHolder = surfaceHolder;
            this.start();
        }

        @Override
        public void run() {
            super.run();
            while (!Thread.currentThread().isInterrupted()){
                xPos += xDirect;
                yPos += yDirect;
                // Wenn Rand des Displays erreicht:
                // Änderung des Richtungsvektors nach dem Prinzip Einfallswinkel = Ausfallswinkel
                /*if (xPos<0) {
                    xDirect = -xDirect;
                    xPos = 0; }
                if (xPos>getWidth()-bitmap.getWidth()) {
                    xDirect = -xDirect;
                    xPos = getWidth()-bitmap.getWidth(); }
                if (yPos<0) {
                    yDirect = -yDirect;
                    yPos = 0; }
                if (yPos>getHeight()-bitmap.getHeight()) {
                    yDirect = -yDirect;
                    yPos = getHeight()-bitmap.getWidth(); }*/
                // xDirect *= 0.99;   optional: allmähliche Verlangsamung der Bewegung
                // yDirect *= 0.99;
                canvas = null;
                try {
                    // Belegung des Canvas
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        // Zeichnen auf dem Canvas (siehe Definition der Methode onDraw() oben
                       screenDraw(canvas);
                    }
                } catch (Exception e) {}
                finally {
                    if (canvas != null)
                        // Freigabe des Canvas
                        surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }


        }
    }

}
