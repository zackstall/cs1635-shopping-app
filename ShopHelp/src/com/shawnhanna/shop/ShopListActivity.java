package com.shawnhanna.shop;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View.OnClickListener;

public class ShopListActivity extends ListActivity implements Serializable
{
	static final String TAG="ListActivity";
	
	private ListView itemListView;
	private ArrayList<Item> itemList;	
	private ItemAdapter itemAdapter;
	private Button searchButton;
	
	//private Button listButton;
	//private Button barcodeButton;
	//private Button mapButton;

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
        Intent intent = getIntent();
		itemList = (ArrayList<Item>)intent.getSerializableExtra("com.shawnhanna.shop.LIST");
		//NOTE: this is just temporary until we get the DB set up
		if(itemList == null)itemList = new ArrayList<Item>();
		
        itemAdapter = new ItemAdapter(this, R.layout.list_entry, itemList);
        //itemList.add(new Item("Ruffles Potato Chips","Potato Chips",3.99,123456, 4));
		//itemList.add(new Item("Shneiders 2% Milk","2% Milk",3.59,123457,9));
		//itemList.add(new Item("Jiffy Peanut Butter","Peanut Butter",4.25,123458, 3));
		//itemList.add(new Item("Coca-Cola","Pop",2.24,123459, 2));
		setListAdapter(itemAdapter);  
        ShopActivity.setupMenuBarButtons(this);
        //define button listeners
        initializeButtonListeners();
	}
	
//-----------------------------------------------------------------------------------------------------------------------------
//-- UTILITY FUNCTIONS
//-----------------------------------------------------------------------------------------------------------------------------
			
	private void initializeViewItems()
	{
		setContentView(R.layout.activity_list);
		//itemListView = (ListView) findViewById( R.id.item_list_view );
		itemListView = getListView();

		searchButton = (Button) findViewById(R.id.add_item_button);
		//listButton = (Button) findViewById(R.id.listMenuButton);
		//barcodeButton = (Button) findViewById(R.id.barcodeMenuButton);
		//mapButton = (Button) findViewById(R.id.mapMenuButton);
	}
	
	private void initializeButtonListeners() 
	{
		searchButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				Intent intent = new Intent(ShopListActivity.this, SearchActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
				startActivity(intent);
			}
		});		
	}

	@Override
	protected void onListItemClick(ListView listview, View view, int position, long id)
	{
		Intent intent = new Intent(ShopListActivity.this, ItemMenuActivity.class);
	    startActivity(intent);
		Log.d("----NOTE", "were in");
		if(view.getTag()==null)
		{
			Log.d("----NOTE", "tag is null");
			intent.putExtra("com.shawnhanna.shop.ITEM", itemList.get(position));
			startActivity(intent);
		}
		else
		{
			Log.d("----NOTE", "no valid tag");
		}
	}
	

//-----------------------------------------------------------------------------------------------------------------------------
//-- ITEM ADAPTER PRIVATE CLASS
//-----------------------------------------------------------------------------------------------------------------------------
	
	private class ItemAdapter extends ArrayAdapter<Item>
	{
		private ArrayList<Item> items;
	
	    public ItemAdapter(Context context, int textViewResourceId, ArrayList<Item> items) 
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
	        	view = inflator.inflate(R.layout.list_entry, null);
	        }
	        
	        Item item = items.get(position);
	        if (item != null) 
	        {
	        	//button creation
	        	CheckBox inCartCheckBox = (CheckBox) view.findViewById(R.id.in_cart);
	        	inCartCheckBox.setTag("CBOX");
	        	TextView nameField = (TextView) view.findViewById(R.id.item_name);
	        	TextView QuantityFielld = (TextView) view.findViewById(R.id.item_quantity);
	        	
	        	Button plusButton = (Button) view.findViewById(R.id.increment_quantity);
	        	plusButton.setTag("ADD");
	        	Button minusButton = (Button) view.findViewById(R.id.decrement_quantity);
	        	minusButton.setTag("SUB");
	        	Button itemActionButton = (Button) view.findViewById(R.id.item_action_button);
	        	itemActionButton.setTag("MENU");
	        	
	        	//all fields are set using the data from each item in the item array
	        	inCartCheckBox.setSelected(item.inCart());
	        	if (nameField != null) nameField.setText(""+item.getShortName());
	        	if (QuantityFielld != null) QuantityFielld.setText(""+item.getQuantity());

	        }
	        return view;
	    }
	}
}
