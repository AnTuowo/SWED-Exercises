package com.websitemonitor.handler;

import com.websitemonitor.domain.User;
import com.websitemonitor.notification.ChannelHandler;

public class EmailHandler implements ChannelHandler {
    @Override
    public void deliver(User user, String message) {
        System.out.println("EMAIL to " + user.getEmail() + ": " + message);
    }
}