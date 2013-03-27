package com.shawnhanna.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ItemMenuActivity  extends ShopActivity {
	static final String TAG="ItemMenuActivity";
	
	private Button removebutton;
	private Button findButton;
	private Button aisleButton;
	private Button backButton;

//-----------------------------------------------------------------------------------------------------------------------------
//-- ONCREATE
//-----------------------------------------------------------------------------------------------------------------------------
					
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		setupMenuBarButtons(this);
		
		initializeViewItems();
		
		initializeButtonListeners();
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
		
	}
}
