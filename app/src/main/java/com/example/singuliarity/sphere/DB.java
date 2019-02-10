package com.example.singuliarity.sphere;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DB extends Activity {
    SharedPreferences sf;
    DB(Context ctx){

        sf=ctx.getSharedPreferences("saved_text",Context.MODE_PRIVATE);
    }

    void saveResults(String res){
        Editor ed=sf.edit();
        ed.putString("saved_text",res);
        ed.commit();
    }

    String getResult(){

        String getText=sf.getString("saved_text","0");
        return getText;
    }

    void getNullResults(){
        Editor ed=sf.edit();
        ed.putString("saved_text","0");
        ed.commit();
    }
}
