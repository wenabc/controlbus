package com.datg.events;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.datg.events.R;
import com.squareup.otto.*;

import de.greenrobot.event.EventBus;

public class ControlsActivity extends ActionBarActivity {
	private Button btnSubmit;
	private EditText edittext;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		addKeyListener();
		/*register the event bus in this class, this is required*/
		TestApplication.getEventBus().register(this);
		EventBus.getDefault().register(this);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d ("USER","CLICKED");
				/* send the click event to the bus*/
				TestApplication.getEventBus().post(new UserEvent(UserEvent.Type.CLICK,"CLICK EVENT"));
			}
		});
	}

	@Override
	protected void onPause() {
		 super.onPause();
		 /* make sure bus is unregistered*/
		 TestApplication.getEventBus().unregister(this);
	}
	
	/**
	 * This is the subscriber for all UserEvents
	 */
	@Subscribe
	public void onUserEvent(UserEvent e) {
		Log.d("BUS","Get off Bus: " + e.getClass().getName()  + " Message: " + e.getMessage());
		if (e.getEventType() == UserEvent.Type.KEYPRESS){
			 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		/* if a click UserEvent happend, the current event handler also initiates the fake Tracker service */
		if (e.getEventType() == UserEvent.Type.CLICK){
			 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			 TestApplication.getTracker().trackIt(e.getMessage());
		}
		if (e.getEventType() == UserEvent.Type.TRACK){
			 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * This is the subscriber for all ServiceEvents
	 */
	@Subscribe
	public void onServiceEvent(ServiceEvent e) {
		Log.d("BUS","Get off Bus: " + e.getClass().getName()  + " Message: " + e.getMessage());
		if (e.getEventType() == ServiceEvent.Type.TRACK){
			 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	public void onEvent(ServiceEvent e){
		Log.d("GREENROBOT BUS", e.getMessage());
		
	}
	
	/* Key stroke listener */
	public void addKeyListener() {
		edittext = (EditText) findViewById(R.id.editText);
		edittext.setOnKeyListener(new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if ((event.getAction() == KeyEvent.ACTION_UP)) {
				Log.d("BUS","Get on bus: " + edittext.getText().toString());
				/* send the keystrok to the event bus via an UserEvent */
				TestApplication.getEventBus().post(new UserEvent(UserEvent.Type.KEYPRESS,edittext.getText().toString()));
			} 
			return false;
		}
	 });
	}
}
