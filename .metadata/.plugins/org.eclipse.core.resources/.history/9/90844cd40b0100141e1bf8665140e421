package com.littleturn.todo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView l1;
	public static final String[] titles = new String[] { "Strawberry",
        "Banana", "Orange", "Mixed" };

public static final String[] times = new String[] {
        "02:00 PM",
        "03:00 PM", "04:00 PM",
        "05:00 PM" };

public static final int[] images = { R.drawable.ic_launcher,
        R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        l1=(ListView)findViewById(R.id.list);
       CustomAdapter adapter = new CustomAdapter(this, titles, times, images);
        l1.setAdapter(adapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
