package com.example.testtp_benzerdjeb_adnane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText ed1,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Intent secondIntent = new Intent(MainActivity.this, MainActivity3.class);
                String poid = ed1.getText().toString();
                String taille = ed2.getText().toString();

                double poidNum = Double.parseDouble(poid);
                double tailleNum = Double.parseDouble(taille);

                double imc = poidNum/(tailleNum*tailleNum);
                Log.i("IMC",""+imc);

                if (imc >= 18.5 && imc <= 24.9){
                    intent.putExtra("imc",imc);
                    startActivity(intent);
                }
                else{
                    secondIntent.putExtra("imc",imc);
                    startActivity(secondIntent);
                }

            }
        });
    }
}