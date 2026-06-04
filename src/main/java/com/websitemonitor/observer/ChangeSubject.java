package com.websitemonitor.observer;

import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;

public interface ChangeSubject {
    void attachObserver(ChangeObserver observer);

    void detachObserver(ChangeObserver observer);

    void notifyObservers(Subscription subscription, User owner, String changeInfo);
}