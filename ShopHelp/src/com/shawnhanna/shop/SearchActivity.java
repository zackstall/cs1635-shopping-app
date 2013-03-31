package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends ShopActivity {
	static final String TAG = "ListActivity";

	private Button searchButton;
	private Button backButton;
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
		currentResults = new ArrayList<Item>(db.getDB());
		resultAdapter = new ResultAdapter(this, R.layout.search_list_entry, currentResults);

		listView = (ListView) findViewById(R.id.searchList);
		listView.setAdapter(resultAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				ArrayList<Item> resultList = DataService.getInstance().getDB();
				Log.d("POS", "POS " + position);
				Log.d("POS", "POS " + resultList.get(position).getName());
				DataService.getInstance().addToCart(resultList.get(position));

				Intent intent = new Intent(SearchActivity.this,	ShopListActivity.class);
				startActivity(intent);
			}
		});

		initializeButtonListeners();
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- UTILITY FUNCTIONS
	// -----------------------------------------------------------------------------------------------------------------------------

	private void initializeViewItems() {
		setContentView(R.layout.activity_search);

		backButton = (Button) findViewById(R.id.back_button);
		searchButton = (Button) findViewById(R.id.search_button);
		searchBar = (EditText)findViewById(R.id.search_bar);
	}

	private void initializeButtonListeners() {
			searchBar.addTextChangedListener(new TextWatcher() {
				
				//only one of these is necessary, but they all nned to be implemented
				@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

				@Override
				public void afterTextChanged(Editable s) {
					currentResults.clear();
					currentResults.addAll(DataService.getInstance().searchDBByName(searchBar.getText().toString()));	
					resultAdapter.notifyDataSetChanged();				
				}
	        });
		
		
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SearchActivity.this.finish();
			}
		});
		
		//relic, still deciding ono its fate -john 3/30
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

			Item item = items.get(position);
			if (item != null) {
				// button creation
				TextView nameField = (TextView) view
						.findViewById(R.id.item_name);
				TextView QuantityFielld = (TextView) view
						.findViewById(R.id.item_quantity);

				// all fields are set using the data from each item in the item
				// array
				if (nameField != null)
					nameField.setText("" + item.getShortName());
				if (QuantityFielld != null)
					QuantityFielld.setText("" + item.getQuantity());
			}
			return view;
		}
	}
}
