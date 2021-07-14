package com.example.eventbusmqtt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventbusmqtt.eventbus.EventBusActivity;
import com.example.eventbusmqtt.eventbus.EventBusUtils;
import com.example.eventbusmqtt.eventbus.event.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MQTTActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = MQTTService.class.getSimpleName();

    private Button btn_publish;
    private TextView textView_show;
    private EditText editText_input;
    private EditText editText_topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);

        initView();
        initData();
    }

    private void initData() {

        EventBusUtils.register(MQTTActivity.this);
    }


    public void initView(){
        btn_publish = findViewById(R.id.btn_publish);
        textView_show = findViewById(R.id.textView_receive);
        editText_input = findViewById(R.id.editView_input);
        editText_topic = findViewById(R.id.editView_topic);

        btn_publish.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_publish:
                Log.i(TAG, "点击publish按钮 ");
                String topic = editText_topic.getText().toString();
                String input = editText_input.getText().toString();

                if (topic == null){
                    topic = "topictest2";
                }
                MQTTService.publish(topic,input);
                break;
        }
    }

    //5 接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessageBus(EventMessage event){
        Log.i(TAG, "MessageEventBus: eventbus接收到数据:"+event.getName());
        //显示接收消息
        textView_show.setText(event.gettopic()+":"+event.getName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 2 解注册
        EventBus.getDefault().unregister(MQTTActivity.this);
    }
}
