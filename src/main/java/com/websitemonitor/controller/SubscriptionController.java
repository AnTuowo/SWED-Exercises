package com.websitemonitor.controller;

import com.websitemonitor.domain.Subscription;
import com.websitemonitor.monitor.WebsiteMonitor;
import com.websitemonitor.domain.User;
import java.util.UUID;

public class SubscriptionController {
    private final WebsiteMonitor monitor;

    //constructor
    public SubscriptionController(WebsiteMonitor monitor) {
        this.monitor = monitor;
    }

    public Subscription registerSubscription(User user, String url,
                                              String frequency, String channel) {
        String id  = UUID.randomUUID().toString();
        Subscription sub = new Subscription(id, url, frequency, channel);
        user.addSubscription(sub);
        monitor.addSubscription(sub, user);
        return sub;
    }

    public void modifySubscription(Subscription sub,
                                    String newFrequency, String newChannel) {
        if (newFrequency != null) sub.setFrequency(newFrequency);
        if (newChannel   != null) sub.setChannel(newChannel);
    }

    public void cancelSubscription(User user, Subscription sub) {
        user.removeSubscription(sub);
        monitor.removeSubscription(sub);
    }
}