package com.example.rsa.androidnativobench.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rsa.androidnativobench.R;
import com.example.rsa.androidnativobench.activitys.performanceRelated.CpuIntensiveTask;
import com.example.rsa.androidnativobench.activitys.performanceRelated.CpuIntensiveTaskRx;
import com.example.rsa.androidnativobench.activitys.performanceRelated.MemoryManagmentTask;
import com.example.rsa.androidnativobench.activitys.performanceRelated.Networking;
import com.example.rsa.androidnativobench.activitys.routineTasks.LoadImagesFromDisk;
import com.example.rsa.androidnativobench.activitys.routineTasks.LoadImagesFromNetwork;
import com.example.rsa.androidnativobench.activitys.routineTasks.Login;

public class MainActivity extends AppCompatActivity {

    private Button login,loadImageFromDisk,loadImageFromNetwork,cpuTaskSt,
            cpuTaskMt,cpuTaskMtas,cpuTaskMtRx, memoryManagement, networkingTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUiComponents();
        setListeners();
    }

    private void setUiComponents() {
        login = findViewById(R.id.loginBtn);
        loadImageFromDisk = findViewById(R.id.loadImgFromDiskBtn);
        loadImageFromNetwork = findViewById(R.id.loadImgFromNetworkBtn);
        cpuTaskSt= findViewById(R.id.CPUIntensiveTaskStBtn);
        cpuTaskMt = findViewById(R.id.CPUIntensiveTaskMtBtn);
        cpuTaskMtas = findViewById(R.id.CPUIntensiveTaskMtasBtn);
        cpuTaskMtRx = findViewById(R.id.CPUIntensiveTaskMtRxBtn);
        memoryManagement = findViewById(R.id.memoryManagement);
        networkingTask = findViewById(R.id.netWorkRequestsBtn);
    }

    private void setListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
        loadImageFromDisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoadImagesFromDisk.class));
            }
        });
        loadImageFromNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoadImagesFromNetwork.class));
            }
        });
        cpuTaskSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CpuIntensiveTask.class);
                i.putExtra("MT",false);
                startActivity(i);
            }
        });
        cpuTaskMt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CpuIntensiveTask.class);
                i.putExtra("MT",true);
                startActivity(i);
            }
        });
        cpuTaskMtas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CpuIntensiveTask.class);
                i.putExtra("MT",false);
                i.putExtra("MTAS",true);
                startActivity(i);
            }
        });
        cpuTaskMtRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CpuIntensiveTaskRx.class);
                startActivity(i);
            }
        });
        memoryManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MemoryManagmentTask.class);
                startActivity(i);
            }
        });
        networkingTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Networking.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent().getExtras() !=null && getIntent().getExtras().getBoolean("FinishAfterLoading")){
            finish();
        }
    }
}
