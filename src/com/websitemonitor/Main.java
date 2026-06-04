package com.websitemonitor;

import com.websitemonitor.controller.SubscriptionController;
import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;
import com.websitemonitor.monitor.WebsiteMonitor;
import com.websitemonitor.notification.NotificationService;

public class Main {
    public static void main(String[] args) {

        // Create subject and observer
        WebsiteMonitor monitor             = new WebsiteMonitor();
        NotificationService notificationService = new NotificationService();

        // Register the observer with the subject — no direct call dependency
        monitor.addObserver(notificationService);

        // Controller and domain setup
        SubscriptionController controller = new SubscriptionController();
        User alice = new User("u1", "alice@example.com", "Alice");

        Subscription s1 = controller.registerSubscription(alice, "https://example.com", "daily", "email");
        Subscription s2 = controller.registerSubscription(alice, "https://news.com", "hourly", "sms");

        monitor.addSubscription(s1, alice);
        monitor.addSubscription(s2, alice);

        System.out.println("--- Check 1 ---");
        monitor.checkForUpdates();

        // Add a second observer — e.g. a logger — with zero changes to WebsiteMonitor
        monitor.addObserver((sub, owner, info) ->System.out.println("LOG: change detected for " + owner.getName()+ " on " + sub.getUrl()));

        System.out.println("\n--- Check 2 (two observers) ---");
        monitor.checkForUpdates();

        // Remove the notification observer
        monitor.removeObserver(notificationService);
        System.out.println("\n--- Check 3 (logger only) ---");
        monitor.checkForUpdates();
    }
}