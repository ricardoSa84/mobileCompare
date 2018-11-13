package com.example.rsa.androidnativobench.activitys.performanceRelated;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rsa.androidnativobench.R;

import java.util.Calendar;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CpuIntensiveTaskRx extends AppCompatActivity {

    private LinearLayout resultsContainer;
    private int count = 0;
    private Calendar startTime;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private int totalRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_intensive_task_rx);
        resultsContainer = findViewById(R.id.resultsContainer);
        startTime = Calendar.getInstance();
        totalRequests = 32;
        for (int i = 1; i <= totalRequests; i++) {
            onRunCpuIntensiveTask(i);
        }
    }

    private Integer cpuIntensiveTask(int i) {
        for (int x = 0; x < 100000; x++) {
            for (int y = 2; y < x; y++) {
                if (x % y == 0) {
                    break;
                }
            }
        }
        return i;
    }

    void onRunCpuIntensiveTask(int i) {
        disposables.add(getCpuIntensiveTaskObservable(i)
                // Run on a background thread
                .subscribeOn(Schedulers.computation())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        count++;
                        TextView text = new TextView(getApplicationContext());
                        text.setText("Task finished: " + integer);
                        resultsContainer.addView(text);
                        if (count == totalRequests) {
                            text = new TextView(getApplicationContext());
                            text.setText("Process finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()));
                            resultsContainer.addView(text);
                        }
                    }
                }));
    }

    public Observable<Integer> getCpuIntensiveTaskObservable(final int i) {
        return Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(cpuIntensiveTask(i));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

}