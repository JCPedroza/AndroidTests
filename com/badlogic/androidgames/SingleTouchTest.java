package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

// We let our activity implement the OnTouchListener interface. We also have two 
// members: one for the TextView, and a StringBuilder we’ll use to construct our 
// event strings.
public class SingleTouchTest extends Activity implements OnTouchListener {
	
	StringBuilder builder = new StringBuilder();
	TextView      textView;
	
	// The onCreate() method is pretty self-explanatory. The only novelty is the 
	// call to TextView.setOnTouchListener(), where we register our activity with the 
	// TextView so that it receives MotionEvents.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("Touch and drag (one finger only)!");
		textView.setOnTouchListener(this);
		setContentView(textView);
	}
	
	// What’s left is the onTouch() method implementation itself. We ignore the view 
	// argument, as we know that it must be the TextView. All we are interested in is 
	// getting the touch event type, appending a string identifying it to our StringBuilder, 
	// appending the touch coordinates, and updating the TextView text. That’s it. We also 
	// log the event to LogCat so that we can see the order in which the events happen, as 
	// the TextView will only show the last event that we processed (we clear the StringBuilder 
	// every time onTouch() is called).
	public boolean onTouch(View v, MotionEvent event) {
		builder.setLength(0);  // here is where we clear the StringBuilder 
		switch (event.getAction()) { 
		case MotionEvent.ACTION_DOWN:
			builder.append("down, ");
			break; 
		case MotionEvent.ACTION_MOVE:
			builder.append("move, ");
			break; 
		case MotionEvent.ACTION_CANCEL:
			builder.append("cancel, ");
			break; 
		case MotionEvent.ACTION_UP:
			builder.append("up, "); break;
			} 
		builder.append(event.getX()); 
		builder.append(", "); 
		builder.append(event.getY()); 
		String text = builder.toString(); 
		Log.d("TouchTest", text); 
		textView.setText(text); 
		return true;
			
	}

}
