package com.example.tp5;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MonService extends Service {
    public MonService() {
    }
    private int count = 0;
    private Handler handler= new Handler();
    Messenger mMessenger = null ;
    class HandlerEntrant extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1==1){
                Message reponseMsgVal = Message.obtain();
                reponseMsgVal.arg1= count;
                try {
                    msg.replyTo.send(reponseMsgVal); // envoyer la réponse à l’émetteur (client)
                } catch (RemoteException e) {
                    e.printStackTrace();
                }}}}
    // est appelee lorsqu'on execute bindService
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Start ", "Service Chrono BIND " );
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        handler.postDelayed(runnable, 0);
        return null;
    }
    // est appelee lorsqu'on execute unbindService
    @Override
    public void onDestroy() {
        Log.i("Destroy", "Service Chrono Arrêté ");
        Toast.makeText(getApplicationContext(),"OnDestroy: Service Chrono Arrêté avec la valeur " + count ,
                Toast.LENGTH_LONG).show();
        handler.removeCallbacks(runnable); // retirer la tâche runnable de la file d'attente du handler
    }
    // Tâche d'arrière plan qui incrémente le compteur de 1 à chaque seconde.
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count++;
            Log.i("Exec Service Chrono", "Valeur du compteur = " + count);
            handler.postDelayed(this, 1000); } }; }