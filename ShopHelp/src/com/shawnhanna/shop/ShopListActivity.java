package com.shawnhanna.shop;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class ShopListActivity extends ListActivity {
	static final String TAG = "ListActivity";

	private ArrayList<Item> itemList;
	private ItemAdapter itemAdapter;
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

		itemAdapter = new ItemAdapter(this, R.layout.list_entry, itemList);
		setListAdapter(itemAdapter);
		ShopActivity.setupMenuBarButtons(this);
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

	@Override
	protected void onListItemClick(ListView listview, View view, int position,
			long id) {
		Intent intent = new Intent(ShopListActivity.this,
				ItemMenuActivity.class);
		intent.putExtra("com.shawnhanna.shop.ITEM", itemList.get(position));
		startActivity(intent);
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
