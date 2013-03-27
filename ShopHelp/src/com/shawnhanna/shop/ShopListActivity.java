package com.shawnhanna.shop;

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

public class ShopListActivity extends ListActivity
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
		
		//NOTE: this is just temporary until we get the DB set up
		itemList = new ArrayList<Item>();
        itemAdapter = new ItemAdapter(this, R.layout.list_entry, itemList);
		for(int i = 0; i < 15; i++)	itemList.add(new Item(""+i,""+i,i,i,i));
        
		setListAdapter(itemAdapter);  
        setupMenuBarButtons(this);
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
			    startActivity(intent);
			}
		});		
	}

protected void setupMenuBarButtons(ShopListActivity activity) {
		ImageButton listMenuButton = (ImageButton) activity.findViewById(R.id.listMenuButton);
		ImageButton barcodeMenuButton = (ImageButton) activity.findViewById(R.id.scanMenuButton);
		ImageButton mapMenuButton = (ImageButton) activity.findViewById(R.id.mapMenuButton);

		listMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ShopListActivity.this, ShopListActivity.class);
			    startActivity(intent);
			}});
		
		barcodeMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("CLICKED", "CLICKED");
				Intent intent = new Intent(ShopListActivity.this, BarcodeActivity.class);
			    startActivity(intent);
			}});
		
		mapMenuButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ShopListActivity.this, MapActivity.class);
			    startActivity(intent);
			}});
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
		}
		else if(((String)view.getTag()).equals("MENU"))
		{
			//Intent intent = new Intent(ShopListActivity.this, ItemMenuActivity.class);
		    //startActivity(intent);
		}
		else if(((String)view.getTag()).equals("CBOX"))
		{
			//do nothing for now
		}
		else if(((String)view.getTag()).equals("ADD"))
		{
			itemList.get(position).incrementQuantity();
		}
		else if(((String)view.getTag()).equals("SUB"))
		{
			itemList.get(position).decrementQuantity();
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
