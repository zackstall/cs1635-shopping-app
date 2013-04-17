package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MapActivity extends ShopActivity {
	static final String TAG = "MapActivity";
	public Item item;
	public Item currItem;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	DataService ds = DataService.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created MapActivity");

		// ==========================================================================================//
		// pull item (cart?) list and iterate through them all, creating new
		// dots w/ appropriate info//
		// ==========================================================================================//
		
		RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.map_activity_rel_layout);
		itemList = ds.getCart();
		int itemCount = itemList.size();
		for(int i = 0; i< itemCount; i++)
		{
			currItem = itemList.get(i);
			final MapDot itemDot = new MapDot(MapActivity.getContext(), currItem);
			itemDot.setImageResource(R.drawable.item_point);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(55, 55);
			setCoords((int)currItem.getLocationID(), params);
			itemDot.setBackgroundColor(Color.TRANSPARENT);
			itemDot.setLayoutParams(params);
			rLayout.addView(itemDot);
			itemDot.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					ds.setSelectedItem(itemDot.getItem());
					Intent intent = new Intent(MapActivity.this,ItemMenuActivity.class);
					startActivity(intent);
				}
			});
			itemDot.setClickable(true);
			itemDot.setVisibility(1);
			ds.addDot(itemDot);
		}
	}

	private void setCoords(int loc, RelativeLayout.LayoutParams param) 
	{
		float density = (MapActivity.getContext()).getResources().getDisplayMetrics().density;
		if (loc == 1) {
			int leftMarginDp = 28;
			int topMarginDp = 195;
			float leftMargin = leftMarginDp * density;
			float topMargin = topMarginDp * density;
			param.setMargins((int)leftMargin, (int)topMargin, 0, 0);
		} else if (loc == 2) {
			int leftMarginDp = 96;
			int topMarginDp = 180;
			float leftMargin = leftMarginDp * density;
			float topMargin = topMarginDp * density;
			param.setMargins((int)leftMargin, (int)topMargin, 0, 0);
		} else if (loc == 3) {
			param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			int rightMarginDp = 90;
			int topMarginDp = 75;
			float rightMargin = rightMarginDp * density;
			float topMargin = topMarginDp * density;
			param.setMargins(0, (int)topMargin, (int)rightMargin, 0);
		} else if (loc == 4) {
			int leftMarginDp = 61;
			int topMarginDp = 93;
			float leftMargin = leftMarginDp * density;
			float topMargin = topMarginDp * density;
			param.setMargins((int)leftMargin, (int)topMargin, 0, 0);
		} else if (loc == 5) {
			int leftMarginDp = 124;
			int topMarginDp = 121;
			float leftMargin = leftMarginDp * density;
			float topMargin = topMarginDp * density;
			param.setMargins((int)leftMargin, (int)topMargin, 0, 0);
		} else if (loc == 6) {
			param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			int leftMarginDp = 69;
			int bottomMarginDp = 122;
			float leftMargin = leftMarginDp * density;
			float bottomMargin = bottomMarginDp * density;
			param.setMargins((int)leftMargin, 0, 0, (int)bottomMargin);
		} else if (loc == 7) {
			param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			int rightMarginDp = 84;
			int bottomMarginDp = 120;
			float rightMargin = rightMarginDp * density;
			float bottomMargin = bottomMarginDp * density;
			param.setMargins(0, 0, (int)rightMargin, (int)bottomMargin);
		} else if (loc == 8) {
			param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			int leftMarginDp = 60;
			int bottomMarginDp = 83;
			float leftMargin = leftMarginDp * density;
			float bottomMargin = bottomMarginDp * density;
			param.setMargins((int)leftMargin, 0, 0, (int)bottomMargin);
		} else if (loc == 9) {
			param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			int rightMarginDp = 125;
			int bottomMarginDp = 138;
			float rightMargin = rightMarginDp * density;
			float bottomMargin = bottomMarginDp * density;
			param.setMargins(0, 0, (int)rightMargin, (int)bottomMargin);
		}
	}

}
