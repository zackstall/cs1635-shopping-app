package com.shawnhanna.shop;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MapActivity extends ShopActivity implements Serializable {
	static final String TAG="MapActivity";
	public Item item;
	private ArrayList<Item> itemList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Intent intent = getIntent();
		itemList = (ArrayList<Item>)intent.getSerializableExtra("com.shawnhanna.shop.LIST");
		setupMenuBarButtons(this);
		Log.d(TAG, "Created MapActivity");
		item = new Item("Ruffles Potato Chips","Potato Chips", 3.99, 123456,4);
		ImageButton itemButton = (ImageButton) this.findViewById(R.id.itemButton);
		itemButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MapActivity.this, ItemMenuActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
			    intent.putExtra("com.shawnhanna.shop.ITEM",item);
				startActivity(intent);
				
			}});
	}
	
	protected void setupMenuBarButtons(MapActivity activity) {
		ImageButton listMenuButton = (ImageButton) activity.findViewById(R.id.listMenuButton);
		ImageButton barcodeMenuButton = (ImageButton) activity.findViewById(R.id.scanMenuButton);
		ImageButton mapMenuButton = (ImageButton) activity.findViewById(R.id.mapMenuButton);

		listMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MapActivity.this, ShopListActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
				startActivity(intent);
			}});
		
		barcodeMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("CLICKED", "CLICKED");
				Intent intent = new Intent(MapActivity.this, BarcodeActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
			    startActivity(intent);
			}});
		
		mapMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MapActivity.this, MapActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
			    startActivity(intent);
			}});
	}
	
	
	
	
}
