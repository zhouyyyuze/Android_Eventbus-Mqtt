package com.example.eventbusmqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.eventbusmqtt.eventbus.EventBusActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MQTTService.class.getSimpleName();

    private Button btn_startservice;
    private Button btn_stopservice;
    private Button btn_publish;
    private Button btn_toTestActivity;
    private Button btn_toEventActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //启动MQTTservice
        startMQTTService();

        init();

    }

    public void init(){

        btn_startservice = findViewById(R.id.btn_start);
        btn_stopservice = findViewById(R.id.btn_stop);
        btn_publish = findViewById(R.id.btn_publish);
        btn_toTestActivity = findViewById(R.id.btn_toTestActivity);
        btn_toEventActivity = findViewById(R.id.btn_toEventActivity);

        btn_startservice.setOnClickListener(this);
        btn_stopservice.setOnClickListener(this);
        btn_publish.setOnClickListener(this);
        btn_toTestActivity.setOnClickListener(this);
        btn_toEventActivity.setOnClickListener(this);

    }

    // Method to start the service
    public void startMQTTService() {
        Log.i(TAG, "startService: 启动服务");
        startService(new Intent(getBaseContext(), MQTTService.class));
    }

    // Method to stop the service
    public void stopMQTTService() {
        stopService(new Intent(getBaseContext(), MQTTService.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Log.i(TAG, "点击启动服务按钮 ");
                startMQTTService();
                break;
            case R.id.btn_stop:
                Log.i(TAG, "点击停止服务按钮 ");
                stopMQTTService();
                break;
            case R.id.btn_publish:
                Log.i(TAG, "点击publish按钮 ");
                MQTTService.publish("topictest2","asd");
                break;
            case R.id.btn_toTestActivity:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MQTTActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_toEventActivity: //跳转到eventbus
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, EventBusActivity.class);
                MainActivity.this.startActivity(intent2);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}