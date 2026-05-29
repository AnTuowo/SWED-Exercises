package com.websitemonitor;

import com.websitemonitor.controller.SubscriptionController;
import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;
import com.websitemonitor.monitor.WebsiteMonitor;
import com.websitemonitor.notification.NotificationService;

public class Main {
    public static void main(String[] args) {

        NotificationService  notificationService = new NotificationService();
        WebsiteMonitor       monitor    = new WebsiteMonitor(notificationService);
        SubscriptionController controller = new SubscriptionController();

        User alice = new User("u1", "alice@example.com", "Alice");

        Subscription s1 = controller.registerSubscription(
                alice, "https://example.com", "daily", "email");
        Subscription s2 = controller.registerSubscription(
                alice, "https://news.com", "hourly", "sms");

        monitor.addSubscription(s1, alice);
        monitor.addSubscription(s2, alice);

        System.out.println("--- Check 1 ---");
        monitor.checkForUpdates();

        controller.modifySubscription(s1, "weekly", "push");
        System.out.println("\n--- Check 2 (after modify) ---");
        monitor.checkForUpdates();

        controller.cancelSubscription(alice, s2);
        System.out.println("\nRemaining subscriptions: "
                + alice.getSubscriptions().size());
    }
}