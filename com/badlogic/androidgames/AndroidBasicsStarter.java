package com.badlogic.androidgames;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AndroidBasicsStarter extends ListActivity {
	
	// String array that holds the names of all of the test activities that our starter
	// application should display.
	// Note that the names in this array are the exact Java class names of the activity 
	// classes we are going to implement later on.
	String tests[] = {"LifeCycleTest", "SingleTouchTest", "MultiTouchTest", "KeyTest", 
			"AccelerometerTest", "AssetsTest", "ExternalStorageTest", "SoundPoolTest", 
			"MediaPlayerTest", "FullScreenTest", "RenderViewTest", "ShapeTest", 
			"BitmapTest", "FontTest", "SurfaceViewTest"};
	
	// Called when the activity is created. We must call the onCreate() method of the 
	// base class of our activity. It's the first thing we must do in the onCreate()
	// method of our own Activity implementation. If we don't, an exception will be
	// thrown and the activity will not be displayed.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// This method is provided to us by the ListActivity class. It lets us specify 
		// the list items we want the ListActivity class to display for us. These need 
		// to be passed to the method in the form of a class instance that implements
		// the ListAdapter interface. We use the convenient ArrayAdapter class to do this.
		// The constructor of this class takes three arguments: Our activity, something 
		// else (will explain it later), and the array of items that the ListActivity 
		// should display. 
		// The second argument: each item in the list is displayed via a View. The 
		// argument defines the layout of each view, along with the type of each view. 
		// The value android.R.layout.simple_list_item_1 is a predefined constant 
		// provided by the UI API for getting up and running quickly. It stands for a
		// standard list item View that will display text. A View is a UI widget on 
		// Android, such as a button, a text field, or a slider. 
		setListAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, tests));
	}
	
	// The ListActivity class has a protected method called onListItemClick() that will 
	// be called when an item is tapped. All we need to do is override that method.
	// The arguments to this method are the ListView that the ListActivity uses to 
	// display the items, the View that got touched and that's contained in that 
	// ListView, the position of the touched item in the list, and an ID, which doesn't
	// interest us all that much. All we really care about is the position argument.
	@Override
	protected void onListItemClick(ListView list, View view, int position, 
			long id) {
		// We call the base class method first. This is always a good thing to do if we
		// override methods of an activity.
		super.onListItemClick(list, view, position, id);
		// Next, we fetch the class name from the tests array, based on the position 
		// argument.
		String testName = tests[position];

		try {
			
			// To get the Class instance representing the activity we want to start, we use a
			// little reflection. Reflection allows us to programmatically inspect, instantiate, 
			// and call classes at runtime. The static method Class.forName() takes a string containing 
			// the fully qualified name of a class for which we want to create a Class instance. 
			// All of the test activities we’ll implement later will be contained in the 
			// com.badlogic.androidgames package. Concatenating the package name with the class 
			// name we fetched from the tests array will give us the fully qualified name of the
			// activity class we want to start. We pass that name to Class.forName() and get a nice
			// Class instance that we can pass to the Intent constructor.
			Class clazz = Class
					.forName("com.badlogic.androidgames." + testName);
			
			// We can start activities that we defined in the manifest file programmatically
			// via an intent. The Intent class has a nice and simple constructor to do this, 
			// which takes two arguments: a Context instance and a Class instance. The latter
			// represents the Java class of the activity we want to start.
			// Context is an interface that provides us with global information about our 
			// application. It is implemented by the Activity class, so we simply pass this
			// reference to the Intent constructor.
			// (for the explanation of the second argument see Class clazz info)
			Intent intent = new Intent(this, clazz);
			
			// Once the Intent instance is constructed, we can start it with a call to the 
			// startActivity() method. This method is also defined in the Context interface. 
			// Because our activity implements that interface, we just call its implementation of
			// that method.
			startActivity(intent);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

// So how will our application behave? First, the starter activity will be displayed. Each time we touch
// an item on the list, the corresponding activity will be started. The starter activity will be
// paused and will go into the background. The new activity will be created by the intent we send
// out and will replace the starter activity on the screen. When we press the back button on the
// Android device, the activity is destroyed and the starter activity is resumed, taking back the
// screen.

// falta: ListActivity, reflection, intent
