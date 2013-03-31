package com.shawnhanna.shop;

import android.os.Bundle;

public class AisleViewActivity extends ShopActivity {
	static final String TAG="BarcodeActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aisle);
		setupMenuBarButtons(this);
	}
}
