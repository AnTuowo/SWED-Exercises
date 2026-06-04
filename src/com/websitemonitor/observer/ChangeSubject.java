package com.websitemonitor.observer;

public interface ChangeSubject {
    void addObserver(ChangeObserver observer); // attach
    void removeObserver(ChangeObserver observer); // detach
    void notifyObservers( // notify
        com.websitemonitor.domain.Subscription subscription,
        com.websitemonitor.domain.User owner,
        String changeInfo
    );
}