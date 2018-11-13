package com.example.rsa.androidnativobench.activitys.performanceRelated;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rsa.androidnativobench.R;
import com.example.rsa.androidnativobench.activitys.performanceRelated.classes.CpuIntensiveRunnable;
import com.example.rsa.androidnativobench.general.CpuIntensiveAsyncTask;
import com.example.rsa.androidnativobench.interfaces.ThreadUpdateUI;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Math.atan;
import static java.lang.Math.cbrt;
import static java.lang.Math.tan;

public class CpuIntensiveTask extends AppCompatActivity implements ThreadUpdateUI {

    private LinearLayout resultsContainer;
    private int count = 0;
    private Calendar startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_intensive_task);
        resultsContainer = findViewById(R.id.resultsContainer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTime = Calendar.getInstance();
        if (getIntent().getExtras().getBoolean("MT")) {
            final int NUMBER_OF_THREADS = 32;
            List<Thread> threadList = new ArrayList<Thread>(NUMBER_OF_THREADS);
            for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
                Thread t = new Thread(new CpuIntensiveRunnable(this));
                t.setPriority(Thread.MAX_PRIORITY);
                t.setName("T-" + i);
                threadList.add(t);

            }
            for (int i = 0; i < threadList.size(); i++) {
                Thread t = threadList.get(i);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (getIntent().getExtras().getBoolean("MTAS")) {
            for (int i = 1; i <= 32; i++) {
                new CpuIntensiveAsyncTask(this, i).execute();
            }
        } else {
            cpuIntensiveTask();
        }
    }

    private void cpuIntensiveTask() {
        for (int x = 0; x < 100000; x++) {
            for (int y = 2; y < x; y++) {
                if (x % y == 0) {
                    break;
                }
            }
        }
        TextView text = new TextView(this);
        text.setText("Process finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()));
        resultsContainer.addView(text);
    }

    @Override
    public void addCompletetionTime(String threadNumber) {
        count++;
        TextView text = new TextView(this);
        text.setText("Task finished: " + threadNumber);
        resultsContainer.addView(text);
        if (count == 32) {
            text = new TextView(this);
            text.setText("Process finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()));
            resultsContainer.addView(text);
        }
    }
}
