package com.shawnhanna.shop;

import android.os.Bundle;
import android.util.Log;

public class SearchActivity  extends ShopActivity {
	static final String TAG="SearchActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created SearchActivity");
	}

}
