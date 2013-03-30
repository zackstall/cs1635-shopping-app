package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ItemMenuActivity  extends ShopActivity {
	static final String TAG="ItemMenuActivity";
	
	private Button removebutton;
	private Button findButton;
	private Button aisleButton;
	private Button backButton;
	private Button incrementButton;
	private Button decrementButton;
	private Item item;
	private Intent intent;
	private ArrayList<Item> itemList;

//-----------------------------------------------------------------------------------------------------------------------------
//-- ONCREATE
//-----------------------------------------------------------------------------------------------------------------------------
					
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		intent = getIntent();
		
		initializeViewItems();
		
		initializeButtonListeners();
		setupMenuBarButtons(this);
		receiveItem();
	}

	private void receiveItem(){
		item = (Item)intent.getSerializableExtra("com.shawnhanna.shop.ITEM");
		itemList = DataService.getInstance().getCart();
		TextView price = (TextView) findViewById(R.id.item_price);
		TextView quantity = (TextView) findViewById(R.id.item_quantity);
		TextView itemName = (TextView) findViewById(R.id.item_name);
		price.setText(""+item.getPrice());
		quantity.setText(""+item.getQuantity());
		itemName.setText(""+item.getName());
	}
//-----------------------------------------------------------------------------------------------------------------------------
//-- UTILITY FUNCTIONS
//-----------------------------------------------------------------------------------------------------------------------------	
	private void initializeViewItems()
	{
		setContentView(R.layout.activity_item);

		removebutton = (Button) findViewById(R.id.remove_button);
		findButton = (Button) findViewById(R.id.find_button);
		aisleButton = (Button) findViewById(R.id.aisle_button);
		backButton = (Button) findViewById(R.id.back_button);
		incrementButton =(Button) findViewById(R.id.increment_quantity);
		decrementButton =(Button) findViewById(R.id.decrement_quantity);
	}

	private void initializeButtonListeners() 
	{
		removebutton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(ItemMenuActivity.this, ShopListActivity.class);
			    startActivity(intent);
			}
		});	
		findButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(ItemMenuActivity.this, MapActivity.class);
			    startActivity(intent);
			}
		});	
		aisleButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(ItemMenuActivity.this, AisleViewActivity.class);
			    startActivity(intent);
			}
		});	
		backButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(ItemMenuActivity.this, ShopListActivity.class);
			    startActivity(intent);
			}
		});	
		incrementButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				item.incrementQuantity();
				for(int i=0; i< itemList.size(); i++){
					if(item.equals(itemList.get(i))){
						itemList.get(i).incrementQuantity();
					}
				}
				TextView quantity = (TextView) findViewById(R.id.item_quantity);
				quantity.setText(""+item.getQuantity());
			}
		});
		decrementButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				item.decrementQuantity();
				for(int i=0; i< itemList.size(); i++){
					if(item.getBarcode() == itemList.get(i).getBarcode()){
						itemList.get(i).decrementQuantity();
					}
				}
				TextView quantity = (TextView) findViewById(R.id.item_quantity);
				quantity.setText(""+item.getQuantity());
			}
		});
	}
}
