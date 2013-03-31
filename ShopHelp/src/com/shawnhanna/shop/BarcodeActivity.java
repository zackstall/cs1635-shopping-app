package com.shawnhanna.shop;

import android.os.Bundle;
import android.util.Log;

public class BarcodeActivity extends ShopActivity {
	static final String TAG = "BarcodeActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barcode);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created BarcodeActivity");
	}
}
