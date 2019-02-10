package com.example.singuliarity.sphere;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback {
    Thread thread=null;
    SurfaceHolder sh;
    boolean run=false;
    Paint p =new Paint();
    PaintScreen ps;
    ThreadScreen th;
    MovingLine mv;
    int x=0,y=0;
    public GameScreen(Context context) {
        super(context);
        getHolder().addCallback(this);
        sh=getHolder();
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.WHITE);


    }
    void setParametersWindows(int width, int height){
        mv=new MovingLine(width,height);
    }
    void getContextMain(Context ctx,int width){
        ps=new PaintScreen(ctx,width);
    }
    void getScreenTouchCoords(int x,int y){
        ps.getCoord(x,y);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        th=new ThreadScreen(sh,ps,mv);
        th.startThread(true);

        th.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean run=true;
        th.startThread(false);
        while(run){
            try{
                th.join();
                run=false;
            }catch(InterruptedException e){}
        }
    }
}
