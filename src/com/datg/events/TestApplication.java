package com.datg.events;

import android.app.Application;
import com.squareup.otto.*;
import com.datg.events.modules.Tracker;

public class TestApplication extends Application
{
	private static MainThreadBus mEventBus;
	private static Tracker tracker;
	
	public static MainThreadBus getEventBus() {
		return mEventBus;
    }
	public static Tracker getTracker() {
		return tracker;
    }
    @Override
    public void onCreate() {
    	super.onCreate();
    	tracker = new Tracker();
    	mEventBus = new MainThreadBus();
    }
}
