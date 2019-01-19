package com.example.ddd.detection.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.example.ddd.detection.MainActivity;
import com.example.ddd.detection.db.Picture;

import org.litepal.LitePal;

public class pictureService extends Service {

    private OcrTask ocrTask;

    private pictureOcrListener listener = new pictureOcrListener() {
        @Override
        public void onProgress(Integer... params) {
            // 更新progressBar
            int i = params[0],len = params[1];
            MainActivity.progressBar.setMax(len);
            MainActivity.progressBar.setProgress(i);
        }

        @Override
        public void onSuccess() {
            ocrTask = null;
            MainActivity.progressBar.setVisibility(View.GONE);
            Toast.makeText(pictureService.this, "Ocr Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(){
            ocrTask = null;
            MainActivity.progressBar.setVisibility(View.GONE);
            Toast.makeText(pictureService.this, "Ocr Cancel", Toast.LENGTH_SHORT).show();
        }
    };

    private pictureBinder mBinder = new pictureBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    public class pictureBinder extends Binder {
        public void OcrProcess(){
            MainActivity.progressBar.setVisibility(View.VISIBLE);
            if(ocrTask == null){
                ocrTask = new OcrTask(pictureService.this,listener);
                ocrTask.execute();
            }
        }
        public void Pause(){
            //if(ocrTask != null){
               // ocrTask.cancelOcr();
           // }
            LitePal.deleteAll(Picture.class);
            Toast.makeText(pictureService.this, "Database Deleted", Toast.LENGTH_SHORT).show();
        }
    }


}
