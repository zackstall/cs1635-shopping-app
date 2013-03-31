package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ItemMenuActivity extends ShopActivity {
	static final String TAG="ItemMenuActivity";
	
	private Button removebutton;
	private Button findButton;
	private Button aisleButton;
	private Button backButton;
	private Button incrementButton;
	private Button decrementButton;
	private TextView quantity;
	private TextView price;
	private TextView totalPrice;
	private Intent intent;
	private ArrayList<Item> itemList;
	private DataService dataService;
	private Item item;
	private int itemIndex;
	

//-----------------------------------------------------------------------------------------------------------------------------
//-- ONCREATE
//-----------------------------------------------------------------------------------------------------------------------------
					
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		intent = getIntent();
		
		dataService = DataService.getInstance();
		
		initializeViewItems();
		
		initializeButtonListeners();
		setupMenuBarButtons(this);
		receiveItem();
		//totalPrice.setText(""+(item.getPrice()*item.getQuantity()));
	}

	private void receiveItem(){
		itemIndex = (Integer) intent.getSerializableExtra("com.shawnhanna.shop.ITEM_INDEX");//no need to pass a whole item
		itemList = dataService.getCart();
		item = itemList.get(itemIndex);
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
		quantity = (TextView) findViewById(R.id.item_quantity);
		price = (TextView) findViewById(R.id.item_price);
		totalPrice = (TextView) findViewById(R.id.total_price);
	}

	private void initializeButtonListeners() 
	{
		removebutton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				dataService.removeFromCart(item);						
				//TODO: fix this desperate workaround and force refresh
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
				intent.putExtra("com.shawnhanna.shop.ITEM_INDEX",itemIndex);
			    startActivity(intent);
			}
		});	
		aisleButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(ItemMenuActivity.this, AisleViewActivity.class);
				intent.putExtra("com.shawnhanna.shop.ITEM_INDEX",itemIndex);
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
				quantity.setText(""+item.getQuantity());
				//totalPrice.setText(""+(item.getPrice()*item.getQuantity()));
			}
		});
		decrementButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				item.decrementQuantity();
				quantity.setText(""+item.getQuantity());
				//totalPrice.setText(""+(item.getPrice()*item.getQuantity()));
					
				if(item.getQuantity()==0){
					dataService.removeFromCart(item);	
					//TODO: fix this desperate workaround and force refresh
					Intent intent = new Intent(ItemMenuActivity.this, ShopListActivity.class);
					startActivity(intent);
				}
			}
		});
	}
}
