package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestActivity recepteur1 = new TestActivity();
        //TODO REGISTER BR
        IntentFilter monIntFilt = new IntentFilter ("evenementdecontact");
        registerReceiver(recepteur1, monIntFilt);
        btn1 = findViewById(R.id.button1);
        Intent intent = new Intent("evenementdecontact");
        intent.putExtra("message", " Hi!! ");
      //TODO SET ACTION
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast (intent);
                //TODO SEND BROADCAST

            }
        });
    }
}