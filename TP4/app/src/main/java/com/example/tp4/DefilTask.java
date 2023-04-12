package com.example.tp4;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.ImageView;

public class DefilTask extends AsyncTask<Void,String,Void> {
    private Context ctx;
    int random=0;
    String[] imagesArray ={"java","android","kotlin"};
    DefilTask(Context ctx){
        this.ctx = ctx;
    }



    @Override
    protected Void doInBackground(Void... voids) {
        HandlerThread thread = new HandlerThread("worker");
        thread.start();
        Handler handler = new Handler(thread.getLooper()) ;
        handler.post(new Runnable(){
            @Override
            public void run() {
               random = (int) Math.floor(Math.random()*3);
            }
        });
                System.out.println(random);
                publishProgress(imagesArray[random]);



        return null;
    }
    @Override
    protected void onProgressUpdate(String... image) {
        super.onProgressUpdate(image);
        int idImgDraw = ((Activity) ctx).getResources().getIdentifier(image[0], "drawable",((Activity) ctx).getPackageName());
        Drawable monImgDraw = ((Activity) ctx).getResources().getDrawable(idImgDraw) ;
        ImageView monImgView = (ImageView) ((Activity) ctx).findViewById(R.id.imageView);
        monImgView.setImageDrawable(monImgDraw);
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        Drawable monImgDraw = ((Activity) ctx).getResources().getDrawable(R.drawable.java);
        ImageView monImgView = (ImageView) ((Activity) ctx).findViewById(R.id.imageView);
        monImgView.setImageDrawable(monImgDraw);
    }
}

