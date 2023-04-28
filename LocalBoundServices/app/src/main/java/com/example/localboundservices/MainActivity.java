package com.example.localboundservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyLocalBoundService myLocalBoundService = new MyLocalBoundService();
    boolean isConnected = false;
    Button btn;
    TextView tv;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override


        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBoundService.MyLocalBinder myLocalBinder = (MyLocalBoundService.MyLocalBinder) service;
            myLocalBoundService = myLocalBinder.getBoundService();
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isConnected = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        tv = findViewById(R.id.textView);
        Intent intent = new Intent(this,
                MyLocalBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        btn.setOnClickListener(v -> {
            if (isConnected) {
                String currentTime = myLocalBoundService.getSystemTime();
                tv.setText(currentTime);
            }
        });
    }
}