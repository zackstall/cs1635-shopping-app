package com.shawnhanna.shop;

import android.content.Context;
import android.widget.ImageButton;

public class MapDot extends ImageButton{
	private String itemName;
	int itemLocation;
	private Item currItem;
	
	public MapDot(Context context){
		super(context);
	}
	public MapDot(Context context, Item item) {
		super(context);
		currItem = item;
		itemName = currItem.getName();
		itemLocation = (int)currItem.getLocationID();
	}
	
	public String getName()
	{
		return itemName;
	}
	
	public int getLocationID()
	{
		return itemLocation;
	}
	
	public Item getItem()
	{
		return currItem;
	}
}
