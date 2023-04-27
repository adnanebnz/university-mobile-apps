package com.example.lowbatterybroadcasting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BroadCastReceiver","LOW BATTERY");
        Toast.makeText(context, "LOW BATTERY", Toast.LENGTH_LONG).show();
    }
}