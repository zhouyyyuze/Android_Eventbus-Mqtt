package com.example.eventbusmqtt.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eventbusmqtt.R;
import com.example.eventbusmqtt.eventbus.event.EventMessage;

import org.greenrobot.eventbus.EventBus;

public class EventBusSendActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_eventbus_send_result;
    private Button btn_eventbus_send_main;
    private Button btn_eventbus_send_sticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_send);

        initView();

    }

    public void initView(){
        textView_eventbus_send_result = findViewById(R.id.textView_eventbus_send_result);
        btn_eventbus_send_main = findViewById(R.id.btn_eventbus_send_main);
        btn_eventbus_send_sticky = findViewById(R.id.btn_eventbus_send_sticky);

        btn_eventbus_send_main.setOnClickListener(this);
        btn_eventbus_send_sticky.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_eventbus_send_main: //主线程发送数据按钮点击时间处理
                //4 发送消息
              //  EventBus.getDefault().post(new MessageEvent("主线程发送的基本数据"));
                //4 发送消息
                EventBusUtils.post(new EventMessage("topic测试","主线程发送的基本数据"));

                finish();
                break;
            case R.id.btn_eventbus_send_sticky: //接收粘性的点击时间的处理
                break;
        }


    }
}