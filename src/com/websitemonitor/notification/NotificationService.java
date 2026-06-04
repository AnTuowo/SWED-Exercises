package com.websitemonitor.notification;

import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;
import com.websitemonitor.handler.EmailHandler;
import com.websitemonitor.handler.PushHandler;
import com.websitemonitor.handler.SMSHandler;
import com.websitemonitor.observer.ChangeObserver;

import java.util.HashMap;
import java.util.Map;

public class NotificationService implements ChangeObserver {

    private Map<String, ChannelHandler> channelHandlers = new HashMap<>();

    public NotificationService() {
        channelHandlers.put("email", new EmailHandler());
        channelHandlers.put("sms",   new SMSHandler());
        channelHandlers.put("push",  new PushHandler());
    }

    // ChangeObserver implementation - update

    @Override
    public void onChangeDetected(Subscription sub, User owner, String changeInfo) {
        sendNotification(owner, sub, changeInfo);
    }

    //Notification logic

    public String formatMessage(String url, String changeInfo) {
        return "Update on [" + url + "]: " + changeInfo;
    }

    public void sendNotification(User user, Subscription sub, String changeInfo) {
        String message = formatMessage(sub.getUrl(), changeInfo);
        ChannelHandler handler = channelHandlers.get(sub.getChannel());
        if (handler != null) {
            handler.deliver(user, message);
        } else {
            System.out.println("No handler for channel: " + sub.getChannel());
        }
    }
}