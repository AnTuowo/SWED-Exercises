package com.websitemonitor.monitor;

import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;
import com.websitemonitor.observer.ChangeObserver;
import com.websitemonitor.observer.ChangeSubject;

import java.util.*;

public class WebsiteMonitor implements ChangeSubject {

    private List<Subscription>      subscriptionList   = new ArrayList<>();
    private Map<String, User>       subscriptionOwners = new HashMap<>();
    private List<ChangeObserver>    observers          = new ArrayList<>();

    // ChangeSubject

    @Override
    public void addObserver(ChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ChangeObserver observer) {
        observers.remove(observer);
    }
////////////////////////////////////////////////////////////////////////
    @Override
    public void notifyObservers(Subscription sub, User owner, String changeInfo) {
        for (ChangeObserver observer : observers) {
            observer.onChangeDetected(sub, owner, changeInfo);
        }
    }

    // Monitor logic

    public void addSubscription(Subscription sub, User owner) {
        subscriptionList.add(sub);
        subscriptionOwners.put(sub.getSubscriptionId(), owner);
    }

    public void checkForUpdates() {
        for (Subscription sub : subscriptionList) {
            String newContent = fetchContent(sub.getUrl());
            if (detectChange(sub.getUrl(), newContent)) {
                User owner = subscriptionOwners.get(sub.getSubscriptionId());
                notifyObservers(sub, owner, "Content changed");
                sub.updateLastChecked(new Date());
            }
        }
    }

    private String fetchContent(String url) {
        return "sample content from " + url;
    }

    public boolean detectChange(String url, String newContent) {
        //  always returns true for demonstration
        return true;
    }
}