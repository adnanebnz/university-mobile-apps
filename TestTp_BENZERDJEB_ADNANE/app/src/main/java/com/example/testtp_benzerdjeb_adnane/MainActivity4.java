package com.example.testtp_benzerdjeb_adnane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity4 extends AppCompatActivity {
EditText ed;
Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ed = (EditText) findViewById(R.id.msg);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = ed.getText().toString();
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/html");
                sendIntent.putExtra(sendIntent.EXTRA_EMAIL,"dieteticien@gmail.com");
                sendIntent.putExtra(sendIntent.EXTRA_TEXT,message);
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }
}