package com.shawnhanna.shop;

import android.os.Bundle;
import android.util.Log;

public class ItemMenuActivity  extends ShopActivity {
	static final String TAG="ItemMenuActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created ItemMenuActivity");
	}

}
