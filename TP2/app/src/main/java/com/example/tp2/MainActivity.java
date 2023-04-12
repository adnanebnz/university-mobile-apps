package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText1=(EditText)findViewById(R.id.editText1);
        final EditText editText2=(EditText)findViewById(R.id.editText2);
        final EditText editText3=(EditText)findViewById(R.id.editText3);
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3 =(Button)findViewById(R.id.button3);
        ImageView img=(ImageView)findViewById(R.id.imageView3);

            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    String url = editText3.getText().toString();

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                    startActivity(intent);
                }
            });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String a=editText1.getText().toString();String b=editText1.getText().toString();
            Intent it = new Intent(MainActivity.this, Activity2.class);
                String url = editText3.getText().toString();
                it.putExtra("link", url);
            it.putExtra("Value1", a);
            it.putExtra("Value2", b);
            startActivity(it);
        }});

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String url = editText3.getText().toString();
                InputStream is = null;
                try {
                    is = (InputStream) new URL(url).getContent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Drawable d = Drawable.createFromStream(is,"test");
            }});



    }// fin on create
} // fin MainActivity