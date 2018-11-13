package com.example.rsa.androidnativobench.general;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.rsa.androidnativobench.interfaces.TaskCompletionNotice;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    LinearLayout linearLayout;
    Context context;
    TaskCompletionNotice taskCompletionNotice;

    public DownloadImageTask(LinearLayout linearLayout, Context context, TaskCompletionNotice taskCompletionNotice) {
        this.linearLayout = linearLayout;
        this.context = context;
        this.taskCompletionNotice = taskCompletionNotice;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        ImageView imageView = new ImageView(this.context);
        imageView.setImageBitmap(result);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                taskCompletionNotice.taskCompleted();
            }

            @Override
            public void onViewDetachedFromWindow(View view) {

            }
        });
        linearLayout.addView(imageView);
    }
}