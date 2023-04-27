package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle extras = getIntent().getExtras();
        String url = extras.getString("link");
        String value1 = extras.getString("Value1");
        String value2 = extras.getString("Value2");
        Log.d("ACTIVITY2", value1);
        Button button33 = (Button) findViewById(R.id.button33);

        try {
            button33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(sendIntent.EXTRA_TEXT, url);
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error" + e, Toast.LENGTH_SHORT).show();
        }
        try {
            int a = Integer.parseInt(value1);
            int b = Integer.parseInt(value2);
            int c = a + b;
            Toast.makeText(this, "resultat de la somme: " + c, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error please fill the 2 numbers inputs ", Toast.LENGTH_SHORT).show();
        }

        Button button1 = (Button) findViewById(R.id.button11);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}