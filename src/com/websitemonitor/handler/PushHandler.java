package com.websitemonitor.handler;

import com.websitemonitor.domain.User;
import com.websitemonitor.notification.ChannelHandler;

public class PushHandler implements ChannelHandler {
    @Override
    public void deliver(User user, String message) {
        System.out.println("PUSH to [" + user.getName() + "]: " + message);
    }
}