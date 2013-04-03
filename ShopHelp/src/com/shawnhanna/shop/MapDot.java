package com.shawnhanna.shop;

import android.widget.ImageButton;

public class MapDot {
	String itemName;
	double itemLocation;
	ImageButton imageButton;
	
	public MapDot(String n,double l)
	{
		itemName = n;
		itemLocation = l;
	}

	public void SetImageButton(ImageButton img)
	{
		imageButton = img;
	}
}
