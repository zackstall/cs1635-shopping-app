package com.shawnhanna.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public abstract class ShopActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void onStart() {
		super.onStart();
	}

	protected void onRestart() {
		super.onRestart();
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onPause() {
		super.onPause();
	}

	protected void onStop() {
		super.onStop();
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	protected void setupMenuBarButtons(ShopActivity activity) {
		ImageButton listMenuButton = (ImageButton) activity.findViewById(R.id.listMenuButton);
		ImageButton barcodeMenuButton = (ImageButton) activity.findViewById(R.id.scanMenuButton);
		ImageButton mapMenuButton = (ImageButton) activity.findViewById(R.id.mapMenuButton);

		listMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ShopActivity.this, ShopListActivity.class);
			    startActivity(intent);
			}});
		
		barcodeMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("CLICKED", "CLICKED");
				Intent intent = new Intent(ShopActivity.this, BarcodeActivity.class);
			    startActivity(intent);
			}});
		
		mapMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ShopActivity.this, MapActivity.class);
			    startActivity(intent);
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected (MenuItem item) {
		return false;
	}
}
