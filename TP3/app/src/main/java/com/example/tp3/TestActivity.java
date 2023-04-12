package com.example.tp3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class TestActivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent inte) {
        Bundle extras = inte.getExtras();
        String message = extras.getString("message");

        if (inte.getAction()=="evenementdecontact") {
            Toast.makeText(arg0, message, Toast.LENGTH_SHORT).show();
        }
    }
}