package com.datg.events;

public class UserEvent extends AbstractEvent {
    public enum Type {
        CLICK,
        KEYPRESS,
        TOUCH,
        ROTATE,
        TRACK
    }
    public UserEvent(Type eventType, String message) {
        super(eventType, message);
    }
}