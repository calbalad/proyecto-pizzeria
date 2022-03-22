package com.pizzaiolo.authorization.events;

import org.springframework.context.ApplicationEvent;

import com.pizzaiolo.authorization.models.entities.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private User user;

    public OnRegistrationCompleteEvent(User user) {
        super(user);

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public OnRegistrationCompleteEvent setUser(User user) {
        this.user = user;
        return this;
    }
}
