package com.websitemonitor.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final String email;
    private final String name;
    private final List<Subscription> subscriptions = new ArrayList<>();

    public User(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getName() { return name; }

    public void addSubscription(Subscription subscription) { subscriptions.add(subscription); }
    public void removeSubscription(Subscription subscription) { subscriptions.remove(subscription); }
    public List<Subscription> getSubscriptions() { return subscriptions; }
}