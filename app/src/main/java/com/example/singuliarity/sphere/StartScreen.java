package com.example.singuliarity.sphere;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle bun){
        super.onCreate(bun);
        setContentView(R.layout.start_layout);
    }

    @Override
    public void onClick(View v) {

    }
}
