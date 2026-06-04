package com.websitemonitor.monitor;

import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;
import com.websitemonitor.observer.ChangeObserver;
import com.websitemonitor.observer.ChangeSubject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsiteMonitor implements ChangeSubject {

    private final List<Subscription> subscriptions = new ArrayList<>();
    private final Map<String, User> ownersBySubscriptionId = new HashMap<>();
    private final List<ChangeObserver> observers = new ArrayList<>();
    private final ChangeDetector changeDetector;

    public WebsiteMonitor(ChangeDetector changeDetector) {
        this.changeDetector = changeDetector;
    }

    @Override
    public void attachObserver(ChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(ChangeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Subscription subscription, User owner, String changeInfo) {
        for (ChangeObserver observer : observers) {
            observer.update(subscription, owner, changeInfo);
        }
    }

    public void addSubscription(Subscription subscription, User owner) {
        subscriptions.add(subscription);
        ownersBySubscriptionId.put(subscription.getSubscriptionId(), owner);
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
        ownersBySubscriptionId.remove(subscription.getSubscriptionId());
    }

    public void checkForUpdates() {
        long now = System.currentTimeMillis();

        for (Subscription subscription : subscriptions) {
            long lastChecked = subscription.getLastChecked().getTime();
            if (now - lastChecked < subscription.getFrequencyMillis()) {
                System.out.println("Skipping " + subscription.getUrl() + " — not due yet");
                continue;
            }

            try {
                if (changeDetector.hasChanged(subscription.getUrl())) {
                    User owner = ownersBySubscriptionId.get(subscription.getSubscriptionId());
                    notifyObservers(subscription, owner, "Content changed");
                    subscription.updateLastChecked(new Date());
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Failed to check " + subscription.getUrl() + ": " + e.getMessage());
            }
        }
    }
}
