package com.example.rsa.androidnativobench.activitys.routineTasks;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rsa.androidnativobench.R;
import com.example.rsa.androidnativobench.general.DownloadImageTask;
import com.example.rsa.androidnativobench.interfaces.TaskCompletionNotice;

import java.util.Calendar;

public class LoadImagesFromNetwork extends AppCompatActivity implements TaskCompletionNotice {

    private LinearLayout linearScroll;
    private EditText numberOfTimes;
    private Button loadButton;
    private TextView stateText;
    private Calendar startTime;
    private int times;
    private int imageLoadCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);

        linearScroll = findViewById(R.id.linearScroll);
        numberOfTimes = findViewById(R.id.numberOfTimes);
        loadButton = findViewById(R.id.loadImagesButton);
        stateText = findViewById(R.id.stateText);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearScroll.removeAllViews();
                startTime = Calendar.getInstance();
                imageLoadCount = 0;
                stateText.setText("Processing please wait");
                times = Integer.parseInt(numberOfTimes.getText().toString());
                for(int i = 0; i<times; i++){
                    new DownloadImageTask(LoadImagesFromNetwork.this.linearScroll,LoadImagesFromNetwork.this,LoadImagesFromNetwork.this)
                            .execute("https://wallpapertag.com/wallpaper/full/7/9/1/134025-cool-medical-wallpaper-2000x1153-ipad-retina.jpg");
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });


    }

    @Override
    public void taskCompleted() {
        imageLoadCount++;
        if(times == imageLoadCount){
            stateText.setText("Process finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()) + "ms");
        }
    }
}
