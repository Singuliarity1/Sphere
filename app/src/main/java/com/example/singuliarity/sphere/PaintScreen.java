package com.example.singuliarity.sphere;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint;

import java.util.ArrayList;

public class PaintScreen {

    Canvas c;
    Paint p;
    int x = 90, y = 200;
    ArrayList<int[]> line;
    String score = "0";
    int playScores;
    float[] widths;
    Paint fontPaint;
    Context ctx;
    DB db;
    Sound sound;
    int sizePoint=50;

    PaintScreen(Context ctx,int maxWidthScreen) {
        db = new DB(ctx);
        this.ctx = ctx;
        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setTextSize(50);
        fontPaint.setStyle(Paint.Style.STROKE);
        playScores = Integer.parseInt(db.getResult());
        sound=new Sound(R.raw.road,ctx);
        sound.play();
        sizePoint=(int)Math.ceil(maxWidthScreen*0.05);
    }
    int getScores() {
        return playScores;
    }

    Context getContext() {
        return ctx;
    }

    public void init(Canvas c) {
        this.c = c;
        p = new Paint();
        p.setColor(Color.BLACK);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.FILL);

    }
    public void newIntent(){
        String scores = String.valueOf(playScores);
        db.saveResults(scores);
        Intent intent = new Intent(ctx, EndScreen.class);
        intent.putExtra("score", scores);
        ctx.startActivity(intent);
        sound.stopRelease();
    }

    public boolean EndScreen(boolean type,int maxHeight) {
        boolean stop = true;
        if (!type) {
            if ((y - (sizePoint-1)) <= 0) {
                newIntent();
                stop = false;
            }
        }else{
            if ((y + (sizePoint-1)) >= maxHeight) {
                newIntent();
                stop = false;
            }
        }
        return stop;
    }
    public void getCoord(int x, int y) {
        if (((x >= (this.x - sizePoint))
                && (x <= (this.x + sizePoint)))
                && ((y >= (this.y - sizePoint))
                && (y <= (this.y + sizePoint)))) {
            this.x = x;
            this.y = y;
        }
    }
    void setAllLines(ArrayList<int[]> line) {
        this.line = line;
    }

    public void paintSphere(boolean backType) {

        for (int i = 0; i < line.size(); i++) {
            int[] param = line.get(i);
            if (backType) {
                painterBackParam(param);
            } else {
                painterForwardParam(param);
            }
        }

        c.drawText(String.valueOf(playScores), 1000, 50, fontPaint);
        c.drawCircle(x, y, sizePoint, p);


    }

    void painterForwardParam(int[] param) {
        if (((param[5] - sizePoint) <= y) && ((param[5] + (Math.round(sizePoint/5))) >= y)) {

            if ((((x - sizePoint) <= param[2]) || ((x + sizePoint) >= param[3]))) {

                y = param[5] - sizePoint+1;
            } else {

                playScores += 1;
                fontPaint.getTextWidths(String.valueOf(playScores), new float[String.valueOf(playScores).length()]);

            }
        } else if (((param[5] + sizePoint) >= y) && ((param[5] - Math.round(sizePoint/2)) <= y)) {
            if ((((x - sizePoint) <= param[2]) || ((x + sizePoint) >= param[3]))) {

                y = param[5] + sizePoint+1;
            }
        }
    }


    void painterBackParam(int[] param) {
        if (((param[5] - sizePoint) <= y) && ((param[5] + Math.round(sizePoint/5)) >= y)) {

            if ((((x - sizePoint) <= param[2]) || ((x + sizePoint) >= param[3]))) {

                y = param[5] + sizePoint+1;
            } else {
                playScores += 1;
                fontPaint.getTextWidths(String.valueOf(playScores), new float[String.valueOf(playScores).length()]);

            }
        } else if (((param[5] + sizePoint) >= y) && ((param[5] - Math.round(sizePoint/2)) <= y)) {
            if ((((x - sizePoint) <= param[2]) || ((x + sizePoint) >= param[3]))) {

                y = param[5] + sizePoint+1;
            }
        }
    }

    void painterPram(int[] param) {


        if (((param[5] - 50) <= y) && ((param[5] + 10) >= y)) {

            if ((((x - 50) <= param[2]) || ((x + 50) >= param[3]))) {

                y = param[5] - 51;
            } else {
                playScores += 1;
                fontPaint.getTextWidths(String.valueOf(playScores), new float[String.valueOf(playScores).length()]);

            }
        } else if (((param[5] + 50) >= y) && ((param[5] - 30) <= y)) {
            if ((((x - 50) <= param[2]) || ((x + 50) >= param[3]))) {

                y = param[5] + 51;
            }
        }
    }

    public void resetWindow() {
        c.drawColor(Color.WHITE);
    }
}
