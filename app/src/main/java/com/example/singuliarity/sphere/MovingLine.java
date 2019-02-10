package com.example.singuliarity.sphere;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;

public class MovingLine {
    Canvas c;
    ArrayList<int[]> allLines;
    int maxHeaight;
    int maxWidth;
    Path path = new Path();
    Paint p;
    int widthHole=200;
    int speedCount=1;
    int lineCount=0;
    private boolean forw=true;

    MovingLine(int x,int y){

        allLines=new ArrayList<int[]>();

        p=new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        p.setColor(Color.BLACK);
        maxHeaight=y;
        maxWidth=x;
    }
    int getMaxHeaight(){
        return maxHeaight;
    }
    void setParam(Context ctx){


    }
    ArrayList<int[]> getAllLines(){
        return allLines;
    }
    void generateLines(int line){
        forw=true;
        boolean generate=false;
        for(int i=0;i<allLines.size();i++ ){
            paintLines(i);
            if(allLines.get(i)[5]<=0){
                allLines.remove(i);
            }
        }

        if((allLines.size()==0)){
            generate=true;
        }else if((allLines.size()<line)&&(allLines.get((allLines.size()-1))[5]<=(maxHeaight*(line-1)/line))){
            generate=true;
        }

        if((allLines.size()==0)){
            generate=true;
        }
        if(generate){
            int holePos= (int) Math.round(Math.random()*(Math.ceil(maxWidth*0.8)));
            int[] param={0,maxHeaight,holePos,(int)(holePos+Math.ceil(maxWidth*0.2)),maxWidth,maxHeaight};
            allLines.add(param);
            lineCount++;
            speedCount= (int) Math.ceil(lineCount/10)+1;
        }
    }

    void generateReverseLines(int line){
        boolean generate=false;
        forw=false;
        for(int i=0;i<allLines.size();i++ ){
            paintLines(i);
            if(allLines.get(i)[5]>=maxHeaight){
                allLines.remove(i);
            }
        }

        if((allLines.size()==0)){
            generate=true;
        }else if((allLines.size()<line)&&(allLines.get((allLines.size()-1))[5]>=(maxHeaight/(line-1)))){
            generate=true;
        }

        if(generate){
            int holePos= (int) Math.round(Math.random()*(Math.ceil(maxWidth*0.8)));
            int[] param={0,maxHeaight,holePos,(int)(holePos+Math.ceil(maxWidth*0.2)),maxWidth,0};
            allLines.add(param);
            lineCount++;
            speedCount= (int) Math.ceil(lineCount/10)+1;
        }
    }

    void paintLines(int i) {
        int[] params=allLines.get(i);
        if(forw) {
            params[5] -= speedCount;
        }else{
            params[5] += speedCount;
        }
        allLines.set(i,params);
        path.reset();
        path.moveTo(0,params[5]);
        path.lineTo(params[2],params[5]);
        path.moveTo(params[3],params[5]);
        path.lineTo(params[4],params[5]);
        path.close();
        c.drawPath(path,p);

    }
    void setCanvas(Canvas c){
        this.c=c;
    }




}
