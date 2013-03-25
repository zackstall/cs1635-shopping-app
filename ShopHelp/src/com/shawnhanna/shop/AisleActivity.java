package com.shawnhanna.shop;

import android.os.Bundle;
import android.util.Log;

public class AisleActivity  extends ShopActivity {
	static final String TAG="AisleActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aisle);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created AisleActivity");
	}

}
