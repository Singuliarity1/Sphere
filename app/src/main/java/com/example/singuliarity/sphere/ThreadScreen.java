package com.example.singuliarity.sphere;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class ThreadScreen extends Thread {
    private SurfaceHolder sh;
    public PaintScreen ps;
    public MovingLine mv;

    private boolean start=false;
    ThreadScreen(SurfaceHolder sh,PaintScreen ps,MovingLine mv){
        this.sh=sh;
        this.ps=ps;
        this.mv=mv;

    }

    void startThread(boolean start){
        this.start=start;
    }

    @Override
   public void run(){
        boolean backLine=false;
        Canvas c;
        while(start){
            c=null;
            try{
                c=sh.lockCanvas(null);
                if (c == null)
                    continue;
                ps.init(c);
                mv.setCanvas(c);
                ps.resetWindow();
                if(ps.getScores()<1000){
                    mv.generateLines(5);
                    backLine=false;
                }else{
                    backLine=true;
                    mv.generateReverseLines(5);
                }
                ps.setAllLines(mv.getAllLines());
                ps.paintSphere(backLine);
                start=ps.EndScreen(backLine,mv.getMaxHeaight());
            }finally{
                if(c!=null){
                    sh.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
