package com.websitemonitor;

import com.websitemonitor.comparator.ContentComparator;
import com.websitemonitor.comparator.HTMLComparator;
import com.websitemonitor.comparator.SizeComparator;
import com.websitemonitor.comparator.TextComparator;
import com.websitemonitor.controller.SubscriptionController;
import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;
import com.websitemonitor.monitor.ChangeDetector;
import com.websitemonitor.monitor.WebsiteMonitor;
import com.websitemonitor.notification.NotificationService;

public class Main {
    public static void main(String[] args) {
        ChangeDetector changeDetector = new ChangeDetector(new TextComparator());
        WebsiteMonitor monitor = new WebsiteMonitor(changeDetector);
        NotificationService notificationService = new NotificationService();

        monitor.attachObserver(notificationService);

        SubscriptionController controller = new SubscriptionController(monitor);
        User makuru = new User("u1", "mac@example.com", "Makuru");
        User donarudo = new User("u2", "doi@example.com", "Donarudo");

        controller.registerSubscription(makuru, "https://www.reuters.com", "1 minute", "email");
        controller.registerSubscription(donarudo, "https://www.coindesk.com/price/bitcoin/", "5 minute", "sms");

        runStrategyCheck(changeDetector, monitor, new TextComparator(), "--- Check with Text strategy ---");
        runStrategyCheck(changeDetector, monitor, new SizeComparator(), "\n--- Check with Size strategy ---");
        runStrategyCheck(changeDetector, monitor, new HTMLComparator(), "\n--- Check with HTML strategy ---");

        // ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        // Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdownNow));

        // scheduler.scheduleAtFixedRate(() -> {
        //     try {
        //         System.out.println("--- Running website check ---");
        //         monitor.checkForUpdates();
        //     } catch (Exception e) {
        //         System.err.println("Website check failed: " + e.getMessage());
        //     }
        // }, 0, 1, TimeUnit.MINUTES);

        // System.out.println("Website monitor is running in the background.");
    }

    private static void runStrategyCheck(
            ChangeDetector changeDetector,
            WebsiteMonitor monitor,
            ContentComparator comparator,
            String message) {
        changeDetector.setComparator(comparator);
        System.out.println(message);
        monitor.checkForUpdates();
    }
}