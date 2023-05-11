package com.example.testtp_benzerdjeb_adnane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
TextView tv;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView) findViewById(R.id.result1);
        btn = (Button) findViewById(R.id.button6);
        Bundle extras = getIntent().getExtras();
        Double imc = extras.getDouble("imc");

        try {
        tv.setText("Votre IMC est : "+imc);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}