package com.datg.events.modules;

import com.datg.events.*;
import de.greenrobot.event.EventBus;
import android.util.Log;

/* Fake analytics class to demonstrate tracking */
public class Tracker {
	public Tracker (){
		
	}
	public void trackIt (String message){
		Log.d ("TRACK", "PREPARING " + message + " EVENT TO TRACK");
		Log.d("TRACK", "thread = " + Thread.currentThread().getName());

		/* Try sending an event to the bus via a seperate thread */
		Thread thread = new Thread() {
		    @Override
		    public void run() {
		       	Log.d("TRACK", "thread = " + Thread.currentThread().getName());
		       	Log.d("BUS","Get on bus: SENDing... ");
		       	TestApplication.getEventBus().post(new ServiceEvent(ServiceEvent.Type.TRACK,"SENDING..."));
		       	Log.d("TEST","EVENTBUS");
				EventBus.getDefault().post(new ServiceEvent(ServiceEvent.Type.TRACK,"SENDING..."));
		    }
		};
		thread.start();
		
		/* simulate tracking complete and triggering an ServiceEvent "Sent" */
		try {
			Log.d("TRACK", "Fake Sending");
			Thread.sleep(5000);
			Log.d("BUS","Get on bus: SENT!");
			TestApplication.getEventBus().post(new ServiceEvent(ServiceEvent.Type.TRACK,"SENT"));
			EventBus.getDefault().post(new ServiceEvent(ServiceEvent.Type.TRACK,"SENT"));
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
}
