package com.example.tp5;

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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    Messenger mService = null;
    boolean isConnected = false;
    TextView tvVal;
    String stringInitialTVal ;
    private class ClientHandler extends Handler {
        @Override
        public void handleMessage( Message msg) {
            int valCouranteChrono = msg.arg1;
            Log.i("COMPTEUR COURANT ", "VALEUR ACTUELLE DU CHRONO = "+ valCouranteChrono);
            tvVal.setText(stringInitialTVal + valCouranteChrono);
        } }
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = null;
            isConnected = true;}
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            isConnected = false;
        }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutAtt = (Button) findViewById(R.id.btn);
        Button boutDett = (Button) findViewById(R.id.btn1);
        Button boutVal = (Button) findViewById(R.id.btn2);
        TextView tvEtat = (TextView) findViewById(R.id.header);
        tvVal = (TextView) findViewById(R.id.chorus);
        String stringInitialTvEta = (String) tvEtat.getText();
        stringInitialTVal = (String) tvVal.getText();
        boutAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent = new Intent();
                monIntent.setComponent(new ComponentName("???","???"));
                bindService(monIntent, mConnection,
                        Context.BIND_AUTO_CREATE);
                Toast.makeText(MainActivity.this," Liaison établie ", Toast.LENGTH_LONG).show();
                tvEtat.setText(stringInitialTvEta + " ATTACHEE ");
                isConnected = true;
            } });
        boutDett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected) {
                    unbindService(mConnection);
                    Toast.makeText(MainActivity.this, " Liaison coupée ", Toast.LENGTH_LONG).show();
                    tvEtat.setText(stringInitialTvEta + " DETACHEE ");
                    isConnected = false; }}});
        boutVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }
        );
    }
}