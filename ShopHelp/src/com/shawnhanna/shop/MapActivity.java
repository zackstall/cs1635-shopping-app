package com.shawnhanna.shop;

import android.os.Bundle;
import android.util.Log;

public class MapActivity extends ShopActivity {
	static final String TAG="MapActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created MapActivity");
	}
}
