package com.shawnhanna.shop;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class ShopListActivity extends ShopActivity {
	static final String TAG = "ListActivity";

	private ArrayList<Item> itemList;
	private Button searchButton;

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- ONCREATE
	// -----------------------------------------------------------------------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// the static items must be created
		super.onCreate(savedInstanceState);

		// initializes views and buttons
		initializeViewItems();
		itemList = DataService.getInstance().getCart();
		// NOTE: this is just temporary until we get the DB set up
		if (itemList == null)
			itemList = new ArrayList<Item>();

		ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.list_entry, itemList);
		ListView lv = (ListView) findViewById(R.id.listList);
		lv.setAdapter(itemAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				Intent intent = new Intent(ShopListActivity.this,
						ItemMenuActivity.class);
				startActivity(intent);
				Log.d("----NOTE", "were in");
				if (view.getTag() == null) {
					Log.d("----NOTE", "tag is null");
					startActivity(intent);
				} else if (((String) view.getTag()).equals("MENU")) {
				} else if (((String) view.getTag()).equals("CBOX")) {
					// do nothing for now
				} else if (((String) view.getTag()).equals("ADD")) {
					itemList.get(position).incrementQuantity();
				} else if (((String) view.getTag()).equals("SUB")) {
					itemList.get(position).decrementQuantity();
				} else {
					Log.d("----NOTE", "no valid tag");
				}
			}
		});
		setupMenuBarButtons(this);
		// define button listeners
		initializeButtonListeners();
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- UTILITY FUNCTIONS
	// -----------------------------------------------------------------------------------------------------------------------------

	private void initializeViewItems() {
		setContentView(R.layout.activity_list);
		searchButton = (Button) findViewById(R.id.add_item_button);
	}

	private void initializeButtonListeners() {
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ShopListActivity.this,
						SearchActivity.class);
				startActivity(intent);
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// -- ITEM ADAPTER PRIVATE CLASS
	// -----------------------------------------------------------------------------------------------------------------------------

	private class ItemAdapter extends ArrayAdapter<Item> {
		private ArrayList<Item> items;

		public ItemAdapter(Context context, int textViewResourceId,
				ArrayList<Item> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = convertView;
			if (view == null) {
				LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflator.inflate(R.layout.list_entry, null);
			}

			Item item = items.get(position);
			if (item != null) {
				// button creation
				CheckBox inCartCheckBox = (CheckBox) view
						.findViewById(R.id.in_cart);
				inCartCheckBox.setTag("CBOX");
				TextView nameField = (TextView) view
						.findViewById(R.id.item_name);
				TextView QuantityFielld = (TextView) view
						.findViewById(R.id.item_quantity);

				Button plusButton = (Button) view
						.findViewById(R.id.increment_quantity);
				plusButton.setTag("ADD");
				Button minusButton = (Button) view
						.findViewById(R.id.decrement_quantity);
				minusButton.setTag("SUB");
				Button itemActionButton = (Button) view
						.findViewById(R.id.item_action_button);
				itemActionButton.setTag("MENU");

				// all fields are set using the data from each item in the item
				// array
				inCartCheckBox.setSelected(DataService.getInstance().inCart(
						item));
				if (nameField != null)
					nameField.setText("" + item.getShortName());
				if (QuantityFielld != null)
					QuantityFielld.setText("" + item.getQuantity());

			}
			return view;
		}
	}

}
