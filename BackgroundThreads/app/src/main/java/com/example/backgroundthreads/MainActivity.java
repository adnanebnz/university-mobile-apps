package com.example.backgroundthreads;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")

    Handler objHandler = new Handler() {
        //this is very important especially for the download tasks so we can change the ui based on the task
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle objBundle = msg.getData();
            String message = objBundle.getString("message");

            TextView tv = findViewById(R.id.tv1);
            tv.setText(message);
        }
    };
Button btn;
    @SuppressLint("SetTextI18n")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            Message objMessage = objHandler.obtainMessage();
            Bundle objBundle = new Bundle();
            Runnable runnable = () -> {
                try{
                    Thread.sleep(5000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                objBundle.putString("message", "message from thread");
                objMessage.setData(objBundle);
                objHandler.sendMessage(objMessage);
            };
            Thread thread = new Thread(runnable);
            thread.start();

            TextView tv = findViewById(R.id.tv1);
            tv.setText("Button is clicked");

        });
    }
}