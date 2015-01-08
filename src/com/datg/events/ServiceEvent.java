package com.datg.events;

public class ServiceEvent extends AbstractEvent {
    public enum Type {
        TRACK,
        RECEIVED_CONFIG
    }
    public ServiceEvent(Type eventType, String message) {
        super(eventType, message);
    }
}