package com.shawnhanna.shop;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class SearchActivity  extends ListActivity {
	
	static final String TAG="ListActivity";
	
	private ListView resultListView;
	private ArrayList<Item> resultList;	
	private ResultAdapter resultAdapter;
	private Button searchButton;
	private Button listButton;
	private Button barcodeButton;
	private Button mapButton;
	private Button backButton;

//-----------------------------------------------------------------------------------------------------------------------------
//-- ONCREATE
//-----------------------------------------------------------------------------------------------------------------------------
				
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		//the static items must be created
		super.onCreate(savedInstanceState);
		
		//initializes views and buttons
        initializeViewItems();
		
		//NOTE: this is just temporary until we get the DB set up
		resultList = new ArrayList<Item>();
        resultAdapter = new ResultAdapter(this, R.layout.search_list_entry, resultList);
		for(int i = 0; i < 15; i++)	resultList.add(new Item(""+i,""+i,i,i,i));
		
        setListAdapter(resultAdapter);  
        
        initializeButtonListeners();
	}

//-----------------------------------------------------------------------------------------------------------------------------
//-- UTILITY FUNCTIONS
//-----------------------------------------------------------------------------------------------------------------------------

	private void initializeViewItems()
	{
		setContentView(R.layout.activity_search);
		resultListView = (ListView) findViewById( R.id.result_list_view );

		backButton = (Button) findViewById(R.id.back_button);
		searchButton = (Button) findViewById(R.id.search_button);
		listButton = (Button) findViewById(R.id.listMenuButton);
		barcodeButton = (Button) findViewById(R.id.barcodeMenuButton);
		mapButton = (Button) findViewById(R.id.mapMenuButton);
	}
	private void initializeButtonListeners() 
	{
		backButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
			    startActivity(intent);
			}
		});		
		searchButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				//do nothing for now
			}
		});		
		listButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
			    startActivity(intent);
			}
		});
		barcodeButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(SearchActivity.this, BarcodeActivity.class);
			    startActivity(intent);
			}
		});
		mapButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{		
				Intent intent = new Intent(SearchActivity.this, MapActivity.class);
			    startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onListItemClick(ListView listview, View view, int position, long id) 
	{

		Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
	    startActivity(intent);
	}
	
//-----------------------------------------------------------------------------------------------------------------------------
//-- RESULT ADAPTER PRIVATE CLASS
//-----------------------------------------------------------------------------------------------------------------------------
			
	private class ResultAdapter extends ArrayAdapter<Item> 
	{
		private ArrayList<Item> items;
	
	    public ResultAdapter(Context context, int textViewResourceId, ArrayList<Item> items) 
	    {
	            super(context, textViewResourceId, items);
	            this.items = items;
	    }
	
	    @SuppressWarnings("unchecked")
		@Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
			
	    	View view = convertView;
	        if (view == null) 
	        {
	        	LayoutInflater inflator = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        	view = inflator.inflate(R.layout.search_list_entry, null);
	        }
	        
	        Item item = items.get(position);
	        if (item != null) 
	        {
	        	//button creation
	        	TextView nameField = (TextView) view.findViewById(R.id.item_name);
	        	Button plusbutton = (Button) view.findViewById(R.id.increment_quantity);
	        	TextView QuantityFielld = (TextView) view.findViewById(R.id.item_quantity);
	        	Button minusButton = (Button) view.findViewById(R.id.decrement_quantity);
	        	Button addItemButton = (Button) view.findViewById(R.id.search_add_button);
	        	
	        	
	        	//all fields are set using the data from each item in the item array
	        	if (nameField != null) nameField.setText(""+item.getShortName());
	        	if (QuantityFielld != null) QuantityFielld.setText(""+item.getQuantity());

	        }
	        return view;
	    }
	}
}
