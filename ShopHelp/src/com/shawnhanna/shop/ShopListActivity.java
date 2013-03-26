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

public class ShopListActivity extends ListActivity{
	static final String TAG="ListActivity";
	
	private ListView itemListView;
	private ArrayList<Item> itemList;	
	private ProgressDialog m_ProgressDialog = null; 
	private ItemAdapter itemAdapter;
	private Runnable viewItems;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		//setupMenuBarButtons(this);
		
		Log.d(TAG, "Created ListActivity");
		
		itemList = new ArrayList<Item>();

        this.itemAdapter = new ItemAdapter(this, R.layout.list_entry, itemList);
        setListAdapter(this.itemAdapter);
		
        viewItems = new Runnable(){
            @Override
            public void run() {
        		for(int i = 0; i < 15; i++)	itemList.add(new Item(""+i,""+i,i,i,i));
                runOnUiThread(returnRes);
            }
        };
        
        Thread thread =  new Thread(null, viewItems, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(ShopListActivity.this, "Please wait...", "Retrieving data ...", true);
        
		itemListView = (ListView) findViewById(R.id.itemListView);
	}
	
	//update the list by adding the array contents to the adapter
	private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(itemList != null && itemList.size() > 0){
            	itemAdapter.notifyDataSetChanged();
            	/*
            	Log.w("myApp", ""+itemList.size());
                for(int i=0;i<itemList.size();i++)
                {
                	Log.w("myApp", ""+itemList.size()+" : "+i);
                	itemAdapter.add(itemList.get(i));
                }*/
                for(int i=0;i<15;i++) 	itemAdapter.add(itemList.get(i));
            }
            m_ProgressDialog.dismiss();
            itemAdapter.notifyDataSetChanged();
        }
      };
	

	private class ItemAdapter extends ArrayAdapter<Item> {
		private ArrayList<Item> items;
	
	    public ItemAdapter(Context context, int textViewResourceId, ArrayList<Item> items) {
	            super(context, textViewResourceId, items);
	            this.items = items;
	    }
	
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
	        if (view == null) {
	        	LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        	view = vi.inflate(R.layout.list_entry, null);
	        }
	        Item item = items.get(position);
	        if (item != null) {
	        	CheckBox inCartCheckBox = (CheckBox) view.findViewById(R.id.in_cart);
	        	TextView nameField = (TextView) view.findViewById(R.id.item_name);
	        	Button plusbutton = (Button) view.findViewById(R.id.increment_quantity);
	        	TextView QuantityFielld = (TextView) view.findViewById(R.id.item_quantity);
	        	Button minusButton = (Button) view.findViewById(R.id.decrement_quantity);
	        	Button itemActionButton = (Button) view.findViewById(R.id.item_action_button);
	        	
	        	//all fields are set using the data from each item in the item array
	        	inCartCheckBox.setSelected(item.inCart());
	        	if (nameField != null) nameField.setText(""+item.getShortName());
	        	if (QuantityFielld != null) QuantityFielld.setText(""+item.getQuantity());

	        }
	        return view;
	    }
	}
}
