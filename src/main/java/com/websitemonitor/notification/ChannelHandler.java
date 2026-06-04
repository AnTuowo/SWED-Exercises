package com.websitemonitor.notification;

import com.websitemonitor.domain.User;

public interface ChannelHandler {
    void deliver(User user, String message);
}