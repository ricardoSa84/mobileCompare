package com.example.rsa.androidnativobench.general;

import android.os.AsyncTask;

import com.example.rsa.androidnativobench.interfaces.ThreadUpdateUI;

public class CpuIntensiveAsyncTask extends AsyncTask<String, Void, String> {
    ThreadUpdateUI updateUI;
    int threadNumber = 0;

    public CpuIntensiveAsyncTask(ThreadUpdateUI updateUI,int threadNumber) {
        this.updateUI = updateUI;
        this.threadNumber = threadNumber;
    }

    protected String doInBackground(String... urls) {
        for (int x = 0; x < 100000; x++) {
            for (int y = 2; y < x; y++) {
                if (x % y == 0) {
                    break;
                }
            }
        }
        return null;
    }

    protected void onPostExecute(String string) {
        updateUI.addCompletetionTime(String.valueOf(threadNumber));
    }
}