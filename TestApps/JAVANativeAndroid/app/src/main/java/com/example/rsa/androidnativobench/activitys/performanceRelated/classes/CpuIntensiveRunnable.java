package com.example.rsa.androidnativobench.activitys.performanceRelated.classes;

import android.os.Looper;
import android.util.Log;

import com.example.rsa.androidnativobench.interfaces.ThreadUpdateUI;

import java.util.logging.Handler;

import static java.lang.Math.atan;
import static java.lang.Math.cbrt;
import static java.lang.Math.tan;

public class CpuIntensiveRunnable implements Runnable{
    private ThreadUpdateUI callback;

    public CpuIntensiveRunnable(ThreadUpdateUI callback){
        this.callback = callback;
    }

        @Override
        public void run() {
            for (int x = 0; x < 100000; x++) {
                for (int y = 2; y < x; y++) {
                    if (x % y == 0) {
                        break;
                    }
                }
            }
            callback.addCompletetionTime(Thread.currentThread().getName());
        }
}
