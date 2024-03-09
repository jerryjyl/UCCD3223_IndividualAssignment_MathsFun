package com.example.mathsfun.general;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class GeneralFunctionClass implements GeneralFunctionInterface{

    public void switchIntent(Context fromContext, Class<?>toActivityClass){
        Intent intent=new Intent(fromContext, toActivityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        fromContext.startActivity(intent);
    }
}
