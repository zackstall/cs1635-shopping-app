package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class BarcodeActivity extends ShopActivity {
	static final String TAG="BarcodeActivity";
	private ArrayList<Item> itemList;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barcode);
		intent = getIntent();
		itemList = (ArrayList<Item>)intent.getSerializableExtra("com.shawnhanna.shop.LIST");
		setupMenuBarButtons(this);
		Log.d(TAG, "Created BarcodeActivity");
	}
	protected void setupMenuBarButtons(BarcodeActivity activity) {
		ImageButton listMenuButton = (ImageButton) activity.findViewById(R.id.listMenuButton);
		ImageButton barcodeMenuButton = (ImageButton) activity.findViewById(R.id.scanMenuButton);
		ImageButton mapMenuButton = (ImageButton) activity.findViewById(R.id.mapMenuButton);

		listMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(BarcodeActivity.this, ShopListActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
				startActivity(intent);
			}});
		
		barcodeMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("CLICKED", "CLICKED");
				Intent intent = new Intent(BarcodeActivity.this, BarcodeActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
			    startActivity(intent);
			}});
		
		mapMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(BarcodeActivity.this, MapActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
			    startActivity(intent);
			}});
	}
}
