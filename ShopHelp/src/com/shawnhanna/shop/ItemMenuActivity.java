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
	}


//-----------------------------------------------------------------------------------------------------------------------------
//-- UTILITY FUNCTIONS
//-----------------------------------------------------------------------------------------------------------------------------

	private void initializeViewItems()
	{
		setContentView(R.layout.activity_item);
	}
	private void initializeButtonListeners() 
	{
		
	}
}
