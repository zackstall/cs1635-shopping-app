package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends ShopActivity {
	static final String TAG = "ListActivity";

	private ImageButton searchButton;
	private ImageButton backButton;
	private ListView listView;
	private ArrayList<Item> currentResults;
	private ResultAdapter resultAdapter;
	private EditText searchBar;

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- ONCREATE
	// -----------------------------------------------------------------------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// the static items must be created
		super.onCreate(savedInstanceState);

		// initializes views and buttons
		initializeViewItems();
		setupMenuBarButtons(this);
		DataService db = DataService.getInstance();

		//the initial result list should be empty
		currentResults = new ArrayList<Item>(db.getDBMinusCart());
		resultAdapter = new ResultAdapter(this, R.layout.search_list_entry, currentResults);

		listView = (ListView) findViewById(R.id.searchList);
		listView.setAdapter(resultAdapter);

		initializeButtonListeners();
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- UTILITY FUNCTIONS
	// -----------------------------------------------------------------------------------------------------------------------------

	private void initializeViewItems() {
		setContentView(R.layout.activity_search);

		backButton = (ImageButton) findViewById(R.id.button_back_arrow);
		searchButton = (ImageButton) findViewById(R.id.search_button);
		searchBar = (EditText)findViewById(R.id.search_bar);
	}

	private void initializeButtonListeners() {
			searchBar.addTextChangedListener(new TextWatcher() {
				
				//only one of these is necessary, but they all need to be implemented
				@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

				@Override
				public void afterTextChanged(Editable s) {
					currentResults.clear();
					//TODO: ignore items in that are in the cart
					ArrayList<Item> resultList = DataService.getInstance().searchDBByNameIgnoreCart(searchBar.getText().toString());
					currentResults.addAll(resultList);	
					resultAdapter.notifyDataSetChanged();				
				}
	        });
		
		
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SearchActivity.this.finish();
			}
		});
		
		//relic, still deciding on its fate -john 3/30
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//get the text, search it, and replace
//				currentResults.clear();
//				currentResults.addAll(DataService.getInstance().searchDBByName(searchBar.getText().toString()));	
//				resultAdapter.notifyDataSetChanged();
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- RESULT ADAPTER PRIVATE CLASS
	// -----------------------------------------------------------------------------------------------------------------------------

	private class ResultAdapter extends ArrayAdapter<Item> {
		private ArrayList<Item> items;
		Item item;

		public ResultAdapter(Context context, int textViewResourceId,
				ArrayList<Item> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflator.inflate(R.layout.search_list_entry, null);
			}

			item = items.get(position);
			if (item != null) {
				DataService.getInstance().setSelectedItem(item);
				
				// view creation
				TextView nameField = (TextView) view.findViewById(R.id.item_name);
				TextView quantityField = (TextView) view.findViewById(R.id.item_quantity);
				ImageButton incrementButton = (ImageButton) view.findViewById(R.id.increment_quantity);
				ImageButton decrementButton = (ImageButton) view.findViewById(R.id.decrement_quantity);
				ImageButton addButton = (ImageButton) view.findViewById(R.id.search_add_button);
				
				nameField.setTag(position);//CAUTION: the tag has to be a number or shit will break
				
				setUpAdapterListeners(incrementButton, decrementButton, addButton, nameField);

				// all fields are set using the data from each item in the item
				// array
				if (nameField != null)
					nameField.setText("" + item.getShortName());
				if (quantityField != null)
					quantityField.setText("" + item.getQuantity());
				
			}
			return view;
		}

		private void setUpAdapterListeners(ImageButton incrementButton, ImageButton decrementButton, ImageButton addButton,  final TextView nameField) {
			
			incrementButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if((Integer) nameField.getTag()<items.size()){
						items.get((Integer) nameField.getTag()).incrementQuantity();
						notifyDataSetChanged();
					}
				}
			});

			decrementButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if((Integer) nameField.getTag()<items.size()){
						if(items.get((Integer) nameField.getTag()).getQuantity()>1){
							items.get((Integer) nameField.getTag()).decrementQuantity();
						}
						notifyDataSetChanged();
					}
				}
			});
			
			addButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					DataService db = DataService.getInstance();
					
					if((Integer) nameField.getTag()<items.size()){
						db.addToCart(items.get((Integer) nameField.getTag()));
						
						//TODO: fix this desperate workaround and force refresh in shoplist
						//SearchActivity.this.finish();
						Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
						startActivity(intent);
					}
				}
			});
		}
	}
}
