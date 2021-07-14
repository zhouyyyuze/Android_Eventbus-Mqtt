package com.example.eventbusmqtt.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eventbusmqtt.MQTTService;
import com.example.eventbusmqtt.R;
import com.example.eventbusmqtt.eventbus.event.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MQTTService.class.getSimpleName();

    private Button btn_eventbus_send;
    private Button btn_eventbus_sticky;

    private TextView textView_eventbus_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        initView();
        initData();
    }

    private void initData() {
        //注册广播 使用工具类
        EventBusUtils.register(EventBusActivity.this);
    }

    public void initView(){
        btn_eventbus_send = findViewById(R.id.btn_eventbus_send);
        btn_eventbus_sticky = findViewById(R.id.btn_eventbus_sticky);
        textView_eventbus_result = findViewById(R.id.textView_eventbus_result);

        btn_eventbus_send.setOnClickListener(this);
        btn_eventbus_sticky.setOnClickListener(this);
    }


    //5 接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessageBus(EventMessage event){
        Log.i(TAG, "MessageEventBus: eventbus接收到数据:"+event.getName());
        //显示接收消息
        textView_eventbus_result.setText(event.gettopic()+":"+event.getName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 2 解注册
        EventBus.getDefault().unregister(EventBusActivity.this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_eventbus_send:
                Intent intent = new Intent();
                intent.setClass(EventBusActivity.this, EventBusSendActivity.class);
                EventBusActivity.this.startActivity(intent);
                break;
            case R.id.btn_eventbus_sticky:
                break;
        }
    }
}