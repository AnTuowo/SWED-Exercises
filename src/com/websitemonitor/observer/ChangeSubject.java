package com.websitemonitor.observer;

public interface ChangeSubject {
    void addObserver(ChangeObserver observer);
    void removeObserver(ChangeObserver observer);
    void notifyObservers(
        com.websitemonitor.domain.Subscription subscription,
        com.websitemonitor.domain.User owner,
        String changeInfo
    );
}