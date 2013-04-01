package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class AisleViewActivity extends ShopActivity {
	static final String TAG="BarcodeActivity";
	private Button backButton;
	private ImageButton leftArrow;
	private ImageButton rightArrow;
	private RelativeLayout layout;
	private Intent intent;
	private ArrayList<Item> itemList;
	private DataService dataService;
	private Item item;
	private int itemIndex;
	private String prefix;
	private int imageIndex;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aisle);
		setupMenuBarButtons(this);
		initializeViewItems();
		
		dataService = DataService.getInstance();
		intent = getIntent();
		itemIndex = (Integer) intent.getSerializableExtra("com.shawnhanna.shop.ITEM_INDEX");
		itemList = dataService.getCart();
		item = itemList.get(itemIndex);
		imageIndex = 1;
		setPrefix();
		
		int resID = getResources().getIdentifier(prefix+imageIndex, "drawable", "com.shawnhanna.shop");
		layout.setBackgroundResource(resID);
		getWindow().getDecorView().setBackgroundResource(resID);
		
		initializeButtonListeners();
	}
	
	
	
	
	//UTILITIES
	private void initializeViewItems(){
	   layout = (RelativeLayout) findViewById(R.id.aisle_layout);
		backButton = (Button) findViewById(R.id.return_button);
		leftArrow = (ImageButton) findViewById(R.id.arr_left);
		rightArrow = (ImageButton) findViewById(R.id.arr_right);
		
	}
	private void setPrefix(){
		int value = (int)item.getLocationID(); 
		if ((value == 2 )||(value==4))
			prefix = "cs_";
		
		if(value== 3)
			prefix = "pb_";
		
		if(value ==9)
			prefix = "milk_";
	}
	
	
	private void initializeButtonListeners(){
		backButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(AisleViewActivity.this, ItemMenuActivity.class);
				intent.putExtra("com.shawnhanna.shop.ITEM_INDEX",itemIndex);
				startActivity(intent);
			}
		});	
	 leftArrow.setOnClickListener(new OnClickListener(){
		public void onClick(View arg0){
			imageIndex = (imageIndex+2)%3;
			int resID = getResources().getIdentifier(prefix+imageIndex, "drawable", "com.shawnhanna.shop");
			layout.setBackgroundResource(resID);
		}
	 });
	 rightArrow.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				imageIndex = (imageIndex+1)%3;
				int resID = getResources().getIdentifier(prefix+imageIndex, "drawable", "com.shawnhanna.shop");
				layout.setBackgroundResource(resID);
			}
		 });
	}

}
