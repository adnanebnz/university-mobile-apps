package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText t1;
    private Button button;
    private TextView textout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button1);
        t1 = (EditText) findViewById(R.id.editText1);
        textout = (TextView) findViewById(R.id.textView2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value1=t1.getText().toString();
                int a=Integer.parseInt(value1);
                int res=fibonacciLoop(a);
                Toast.makeText(getApplicationContext(),String.valueOf(res),Toast.LENGTH_LONG).show();
                textout.setText("resultat="+res);
            } }); }
    public int fibonacciLoop(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        int fibo1 = 1, fibo2 = 1, fibonacci = 1;
        for (int i = 3; i <= number; i++) {
            fibonacci = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonacci; }
        return fibonacci;
    }
}