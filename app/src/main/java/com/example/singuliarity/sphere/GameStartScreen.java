package com.example.singuliarity.sphere;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class GameStartScreen extends AppCompatActivity implements View.OnTouchListener {
    GameScreen gs;
    Point size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gs=new GameScreen(this);
        gs.setOnTouchListener(this);

        Display display=getWindowManager().getDefaultDisplay();
        size=new Point();
        display.getSize(size);
        gs.setParametersWindows(size.x,size.y);
        gs.getContextMain(this,size.x);
        setContentView(gs);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        if(((x-50)<=0)){
            x=50;
        }
        if((x+50)>=size.x){
            x=size.x-50;
        }

        if(((y-50)<=0)){
            y=50;
        }
        if((y+50)>=size.y){
            y=size.y-50;
        }
        gs.getScreenTouchCoords(x,y);
        return true;
    }
}
