package com.websitemonitor.controller;

import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;
import com.websitemonitor.monitor.WebsiteMonitor;
import java.util.UUID;

public class SubscriptionController {
    private final WebsiteMonitor monitor;

    public SubscriptionController(WebsiteMonitor monitor) {
        this.monitor = monitor;
    }

    public Subscription registerSubscription(User user, String url,
                                              String frequency, String channel) {
        String id = UUID.randomUUID().toString();
        Subscription subscription = new Subscription(id, url, frequency, channel);
        user.addSubscription(subscription);
        monitor.addSubscription(subscription, user);
        return subscription;
    }

    public void modifySubscription(Subscription subscription,
                                    String newFrequency, String newChannel) {
        if (newFrequency != null) {
            subscription.setFrequency(newFrequency);
        }
        if (newChannel != null) {
            subscription.setChannel(newChannel);
        }
    }

    public void cancelSubscription(User user, Subscription subscription) {
        user.removeSubscription(subscription);
        monitor.removeSubscription(subscription);
    }
}