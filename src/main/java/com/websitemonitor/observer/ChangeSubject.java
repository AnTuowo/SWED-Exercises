package com.websitemonitor.observer;

public interface ChangeSubject {
    void attachObserver(ChangeObserver observer); // attach
    void detachObserver(ChangeObserver observer); // detach
    void notifyObservers( // notify
        com.websitemonitor.domain.Subscription subscription,
        com.websitemonitor.domain.User owner,
        String changeInfo
    );
}