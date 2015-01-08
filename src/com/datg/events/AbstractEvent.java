package com.datg.events;

public abstract class AbstractEvent {
    private Enum _eventType;
    private String _message;
    
    protected AbstractEvent(Enum eventType, String message){
        this._eventType = eventType;
        this._message = message;
    }
    public Enum getEventType(){
        return this._eventType;
    }
    public String getMessage(){
    	return this._message;
    }
}