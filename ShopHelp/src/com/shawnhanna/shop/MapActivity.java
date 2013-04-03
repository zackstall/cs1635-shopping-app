package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MapActivity extends ShopActivity {
	static final String TAG="MapActivity";
	public Item item;
	public Item currItem;
	public Node currNode;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Node> nodes = new ArrayList<Node>();
	DataService ds = DataService.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		setupMenuBarButtons(this);
		Log.d(TAG, "Created MapActivity");
		
		//==========================================================================================//
		//pull item (cart?) list and iterate through them all, creating new dots w/ appropriate info//
		//==========================================================================================//
		itemList = ds.getCart();
		nodes = ds.getNodes();
		int itemCount = itemList.size();
		
		for(int i=0;i<itemCount;i++)
		{
			currItem = itemList.get(i);//set current item to i'th item in list
			MapDot itemDot = new MapDot(currItem.getName(), currItem.getLocationID());//new dot with name and location
			ImageButton itemButton = (ImageButton) this.findViewById(R.id.itemButton);
			itemButton.setClickable(true);
			itemButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					ds.setSelectedItem(currItem);
					Intent intent = new Intent(MapActivity.this, ItemMenuActivity.class);
					//DataService.getInstance().setSelectedItem();
					startActivity(intent);
				}
			});
			
			itemDot.SetImageButton(itemButton);
			
			for(int j = 0; j < 9; j++)
			{
				currNode = nodes.get(j);
				if(currItem.getLocationID()==currNode.getId())
				{
					currNode.addDot(itemDot);
				}
			}
		}
	}
}
