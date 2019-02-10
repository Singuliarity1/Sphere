package com.example.singuliarity.sphere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Sound sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sound=new Sound(R.raw.schelchek,this);
        setContentView(R.layout.start_layout);
        Button btn=(Button) findViewById(R.id.start_button);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        sound.play();
        switch(v.getId()){
            case R.id.start_button:
                Intent intent=new Intent(this,GameStartScreen.class);
                startActivity(intent);
                break;
        }
    }
}
