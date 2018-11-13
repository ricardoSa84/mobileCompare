package com.example.rsa.androidnativobench.activitys.performanceRelated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rsa.androidnativobench.R;
import com.example.rsa.androidnativobench.activitys.MainActivity;

import java.util.Calendar;

public class MemoryManagmentTask extends AppCompatActivity {

    private TextView tvState;
    private EditText edNumberOfTimes;
    private Button loadAcitivitysBtn;
    private static Calendar startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_management_layout);

        tvState = findViewById(R.id.stateText);
        edNumberOfTimes = findViewById(R.id.numberOfTimes);
        loadAcitivitysBtn = findViewById(R.id.loadActivitysBtn);

        loadAcitivitysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvState.setText("Processing please wait");
                startTime = Calendar.getInstance();
                int times = Integer.parseInt(edNumberOfTimes.getText().toString());
                for (int i = 0; i < times; i++) {
                    Intent intent = new Intent(MemoryManagmentTask.this, MainActivity.class);
                    intent.putExtra("FinishAfterLoading",true);
                    startActivityForResult(intent, 99);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            tvState.setText("Process finished in " + String.valueOf(Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()) + "ms");
        }
    }
}
