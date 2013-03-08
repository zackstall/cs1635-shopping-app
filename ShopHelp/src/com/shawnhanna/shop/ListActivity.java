package com.shawnhanna.shop;

import android.os.Bundle;
import android.util.Log;

public class ListActivity extends ShopActivity {
	static final String TAG="ListActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created ListActivity");
	}
}
