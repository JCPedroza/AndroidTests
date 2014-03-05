package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleTest extends Activity {
	
	// We define two members: a StringBuilder, which will hold all the messages 
	// we have produced so far, and the TextView, which we use to display those messages 
	// directly in the Activity.
	StringBuilder builder = new StringBuilder();
	TextView textView;
	
	// We define a little private helper method that will log text to LogCat, append it 
	// to our StringBuilder, and update the TextView text. For the LogCat output, we use the 
	// static Log.d() method, which takes a tag as the first argument and the actual message 
	// as the second argument.
	private void log(String text) {
		Log.d("LifeCycleTest", text);
		builder.append(text);
		builder.append("\n");
		textView.setText(builder.toString());
	}
	
	// In the onCreate() method, we call the superclass method first, as always. We create the 
	// TextView and set it as the content view of our activity. It will fill the complete space of 
	// the activity. Finally, we log the message created to LogCat and update the TextView text with 
	// our previously defined helper method log().
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText(builder.toString());
		setContentView(textView);
		log("created");
	}
	
	// Next, we override the onResume() method of the activity. As with any activity methods that 
	// we override, we first call the superclass method. All we do is call log() again with resumed 
	// as the argument.
	@Override
	protected void onResume() {
		super.onResume();
		log("resumed");
	}
	
	// The overridden onPause() method looks much like the onResume() method. We log the message as 
	// “paused” first. We also want to know whether the activity is going to be destroyed after the onPause() 
	// method call, so we check the Activity.isFinishing() method. If it returns true, we log the finishing 
	// event as well. Of course, we won’t be able to see the updated TextView text because the activity will 
	// be destroyed before the change is displayed on the screen. Thus, we also output everything to LogCat, 
	// as discussed earlier.
	@Override
	protected void onPause(){
		super.onPause();
		log("paused");
		
		if (isFinishing()) {
			log("finishing");
		}
	}

}
