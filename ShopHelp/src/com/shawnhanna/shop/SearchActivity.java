package com.shawnhanna.shop;

import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends ListActivity {
	static final String TAG = "ListActivity";

	private ArrayList<Item> resultList;
	private ResultAdapter resultAdapter;
	private Button searchButton;
	private Button backButton;
	private ArrayList<Item> itemList;

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
		// for(int i = 0; i < 15; i++) resultList.add(new
		// Item(""+i,""+i,i,i,i));
		DataService db = DataService.getInstance();
		resultAdapter = new ResultAdapter(this, R.layout.search_list_entry,
				db.getDB());

		itemList = db.getCart();
		setListAdapter(resultAdapter);

		initializeButtonListeners();
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- UTILITY FUNCTIONS
	// -----------------------------------------------------------------------------------------------------------------------------

	private void initializeViewItems() {
		setContentView(R.layout.activity_search);

		backButton = (Button) findViewById(R.id.back_button);
		searchButton = (Button) findViewById(R.id.search_button);
	}

	private void initializeButtonListeners() {
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SearchActivity.this,
						ShopListActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
				startActivity(intent);
			}
		});
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// do nothing for now
			}
		});

	}

	protected void setupMenuBarButtons(SearchActivity activity) {
		ImageButton listMenuButton = (ImageButton) activity
				.findViewById(R.id.listMenuButton);
		ImageButton barcodeMenuButton = (ImageButton) activity
				.findViewById(R.id.scanMenuButton);
		ImageButton mapMenuButton = (ImageButton) activity
				.findViewById(R.id.mapMenuButton);

		listMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SearchActivity.this,
						ShopListActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
				startActivity(intent);
			}
		});

		barcodeMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SearchActivity.this,
						BarcodeActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
				startActivity(intent);
			}
		});

		mapMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SearchActivity.this,
						MapActivity.class);
				intent.putExtra("com.shawnhanna.shop.LIST", itemList);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onListItemClick(ListView listview, View view, int position,
			long id) {
		ArrayList<Item> resultList = DataService.getInstance().getDB();
		Log.d("POS", "POS " + position);
		//TODO: change to reflect search results
		Log.d("POS", "POS " + resultList.get(position).getName());
		DataService.getInstance().addToCart(resultList.get(position));

		Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
		startActivity(intent);
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
