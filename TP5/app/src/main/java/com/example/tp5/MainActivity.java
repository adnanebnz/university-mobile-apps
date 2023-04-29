package com.example.tp5;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Messenger mService = null;


    boolean isConnected = false;
    private final ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            //TODO MY SOLUTION
            mService = new Messenger(service);
            //TODO END OF SOLUTION
            isConnected = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            isConnected = false;
        }
    };
    TextView tvVal;
    String stringInitialTVal;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutAtt = (Button) findViewById(R.id.button);
        Button boutDett = (Button) findViewById(R.id.button2);
        Button boutVal = (Button) findViewById(R.id.button3);
        TextView tvEtat = (TextView) findViewById(R.id.textView);
        tvVal = (TextView) findViewById(R.id.textView2);
        String stringInitialTvEta = (String) tvEtat.getText();
        stringInitialTVal = (String) tvVal.getText();
        boutAtt.setOnClickListener(v -> {
            try {
                Intent monIntent = new Intent();
                //TODO MY SOLUTION
                monIntent.setComponent(new ComponentName("com.example.tp5",
                        "com.example.tp5.MonService"));
                //TODO END OF MY SOLUTION
                bindService(monIntent, mConnection,
                        Context.BIND_AUTO_CREATE);
                Toast.makeText(MainActivity.this, " Liaison établie ", Toast.LENGTH_LONG).show();
                tvEtat.setText(stringInitialTvEta + " ATTACHEE ");
                isConnected = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        boutDett.setOnClickListener(v -> {
            try {
                if (isConnected) {
                    unbindService(mConnection);
                    Toast.makeText(MainActivity.this, " Liaison coupée ", Toast.LENGTH_LONG).show();
                    tvEtat.setText(stringInitialTvEta + " DETACHEE ");
                    isConnected = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        boutVal.setOnClickListener(v -> {
            //TODO MY SOLUTION
            if (isConnected) {
                Message msg = Message.obtain();
                msg.arg1 = 1;
                msg.replyTo = new Messenger(new ClientHandler());
                try {
                    mService.send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //TODO END OF SOLUTION
        });
    }

    @SuppressLint("HandlerLeak")
    private class ClientHandler extends Handler {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            int valCouranteChrono = msg.arg1;
            Log.i("COMPTEUR COURANT ", "VALEUR ACTUELLE DU CHRONO = " + valCouranteChrono);
            tvVal.setText(stringInitialTVal + valCouranteChrono);
        }
    }
}
