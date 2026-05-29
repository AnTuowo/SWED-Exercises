package com.websitemonitor.observer;

import com.websitemonitor.domain.Subscription;
import com.websitemonitor.domain.User;

public interface ChangeObserver {
    void onChangeDetected(Subscription subscription, User owner, String changeInfo);
}