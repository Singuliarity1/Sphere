package com.example.singuliarity.sphere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends Activity implements View.OnClickListener{
    Button btn,btnnull;
    TextView tv;
    Sound sound;
    @Override
   protected void onCreate(Bundle bun){
        super.onCreate(bun);
        setContentView(R.layout.end_screen);
        btn=(Button) findViewById(R.id.restart_button);
        btnnull=(Button) findViewById(R.id.NullScores);
        sound=new Sound(R.raw.schelchek,this);
        btn.setOnClickListener(this);
        btnnull.setOnClickListener(this);
        Intent intent=getIntent();
        tv=(TextView)findViewById(R.id.scores);
        tv.setText(intent.getStringExtra("score"));
    }

    @Override
    public void onClick(View v) {
        sound.play();
        switch(v.getId()){
            case R.id.restart_button:
                Intent intent=new Intent(this,GameStartScreen.class);
                startActivity(intent);
                break;
            case R.id.NullScores:
                DB db=new DB(this);
                tv=(TextView)findViewById(R.id.scores);
                tv.setText("0");
                db.getNullResults();
                break;
        }
    }
}
