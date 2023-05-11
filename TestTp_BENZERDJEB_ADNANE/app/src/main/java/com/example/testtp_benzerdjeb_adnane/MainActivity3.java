package com.example.testtp_benzerdjeb_adnane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView tv;
    Button btnOui,btnNon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv = (TextView) findViewById(R.id.result1);
        btnOui = (Button) findViewById(R.id.button6);
        btnNon = (Button) findViewById(R.id.button4);
        Bundle extras = getIntent().getExtras();
        Double imc = extras.getDouble("imc");

        try {
            tv.setText("Votre IMC est : "+imc);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
            }
        });
        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}