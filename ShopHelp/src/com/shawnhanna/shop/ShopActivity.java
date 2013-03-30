package com.shawnhanna.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public abstract class ShopActivity extends Activity {
	private static Context context;
	private static DataService service = DataService.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShopActivity.context = getApplicationContext();
		
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

	protected static void setupMenuBarButtons(final Activity activity) {
		ImageButton listMenuButton = (ImageButton) activity
				.findViewById(R.id.listMenuButton);
		ImageButton barcodeMenuButton = (ImageButton) activity
				.findViewById(R.id.scanMenuButton);
		ImageButton mapMenuButton = (ImageButton) activity
				.findViewById(R.id.mapMenuButton);

		listMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(activity, ShopListActivity.class);
				activity.startActivity(intent);
			}
		});

		barcodeMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.d("CLICKED", "CLICKED");
				Intent intent = new Intent(activity, BarcodeActivity.class);
				activity.startActivity(intent);
			}
		});

		mapMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(activity, MapActivity.class);
				activity.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	// Add a title bar to the top of the screen
	protected void addTitle(String string) {
	}

	public static Context getContext() {
		return ShopActivity.context;
	}
}
