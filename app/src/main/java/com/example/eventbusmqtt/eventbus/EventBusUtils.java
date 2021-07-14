/*
 *EventBus工具类
 */

package com.example.eventbusmqtt.eventbus;
import com.example.eventbusmqtt.eventbus.event.EventMessage;
import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {
    private EventBusUtils() {
    }

    /**
     * 注册 EventBus
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(subscriber)) { //如果没有注册则注册
            eventBus.register(subscriber);
        }
    }

    /**
     * 解除注册 EventBus
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(subscriber)) { //如果注册了就解除注册
            eventBus.unregister(subscriber);
        }
    }

    /**
     * 发送事件消息
     * @param event
     */
    public static void post(EventMessage event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件消息
     * @param event
     */
    public static void postSticky(EventMessage event) {
        EventBus.getDefault().postSticky(event);
    }


    /**
     * 解除粘性事件消息
     * 粘性事件不解除只要发送一次每次进入这个页面都会获取
     *
     * @param event
     */
    public static void removeSticky(EventMessage event) {
        EventBus.getDefault().removeStickyEvent(event);
    }
}
