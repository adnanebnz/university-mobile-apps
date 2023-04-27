package com.example.recievertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BroadCastReceiver","BroadCast message is received");
        Toast.makeText(context, "BroadCast message is received", Toast.LENGTH_LONG).show();
    }
}