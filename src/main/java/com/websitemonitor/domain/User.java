package com.websitemonitor.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String email;
    private String name;
    private List<Subscription> subscriptions = new ArrayList<>();

    public User(String userId, String email, String name) {
        this.userId = userId;
        this.email  = email;
        this.name   = name;
    }

    public String getUserId() { return userId; }
    public String getEmail()  { return email; }
    public String getName()   { return name; }

    public void addSubscription(Subscription s)    { subscriptions.add(s); }
    public void removeSubscription(Subscription s) { subscriptions.remove(s); }
    public List<Subscription> getSubscriptions()   { return subscriptions; }
}