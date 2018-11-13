package com.example.rsa.androidnativobench.activitys.routineTasks;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rsa.androidnativobench.R;

import java.util.Calendar;

public class LoadImagesFromDisk extends AppCompatActivity {

    private LinearLayout linearScroll;
    private EditText numberOfTimes;
    private Button loadButton;
    private TextView stateText;
    private Calendar startTime;
    private int imagesToLoad = 0;
    private int viewsAttached = 0;

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
                imagesToLoad = Integer.parseInt(numberOfTimes.getText().toString());
                viewsAttached = 0;
                linearScroll.removeAllViews();
                startTime = Calendar.getInstance();
                stateText.setText("Processing please wait");
                int times = Integer.parseInt(numberOfTimes.getText().toString());
                for(int i = 0; i<times; i++){
                    loadImage();
                }
            }
        });

    }

    private void loadImage(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.highres);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                viewsAttached++;
                if(viewsAttached == imagesToLoad)
                stateText.setText("Process finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()) + "ms");
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            @Override
            public void onViewDetachedFromWindow(View view) {

            }
        });
        linearScroll.addView(imageView);
    }

}
