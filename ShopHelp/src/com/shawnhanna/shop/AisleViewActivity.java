package com.shawnhanna.shop;

import java.util.Hashtable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class AisleViewActivity extends ShopActivity {
	static final String TAG = "AisleViewActivity";

	private Button backButton;
	private ImageButton leftArrow;
	private ImageButton rightArrow;
	private ImageButton upArrow;
	private RelativeLayout layout;
	private DataService dataService;
	private Item item;
	private AisleInfo info;
	private String position;
	private Hashtable<String, AisleInfo> mapping;
	private int imageIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aisle);
		setupMenuBarButtons(this);
		initializeViewItems();

		dataService = DataService.getInstance();
		item = dataService.getSelectedItem();
		imageIndex = 1;
		setPrefix();

		int resID = getResources().getIdentifier(position + imageIndex,
				"drawable", "com.shawnhanna.shop");
		layout.setBackgroundResource(resID);
		

		initializeButtonListeners();
	}

	// UTILITIES
	private void initializeViewItems() {
		layout = (RelativeLayout) findViewById(R.id.aisle_layout);
		backButton = (Button) findViewById(R.id.return_button);
		leftArrow = (ImageButton) findViewById(R.id.arr_left);
		rightArrow = (ImageButton) findViewById(R.id.arr_right);
		upArrow = (ImageButton) findViewById(R.id.arr_up);
	}

	private void setPrefix() {
		int value = (int) item.getLocationID();
		if (value == 10) 
			position = "e_0";

		else	if (value == 11)
			position = "a_0";

		else	if (value == 12)
			position = "d_1";
		
		else if (value ==6)
			position = "b_3";
		else
			position = "f_3";
	}

	private void initializeButtonListeners() {
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(AisleViewActivity.this,
						ItemMenuActivity.class);
				startActivity(intent);
			}
		});
		leftArrow.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			position =	mapping.get(position).left;
			info = mapping.get(position);
			position = info.name;
			if(info.hasUp){
				upArrow.setVisibility(View.VISIBLE);
			}
			else{
				upArrow.setVisibility(View.GONE);
			}
				int resID = getResources().getIdentifier(position,
						"drawable", "com.shawnhanna.shop");
				layout.setBackgroundResource(resID);
			}
		});
		rightArrow.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				position =	mapping.get(position).right;
				info = mapping.get(position);
				position = info.name;
				if(info.hasUp){
					upArrow.setVisibility(View.VISIBLE);
				}
				else{
					upArrow.setVisibility(View.GONE);
				}
				int resID = getResources().getIdentifier(position,
						"drawable", "com.shawnhanna.shop");
				layout.setBackgroundResource(resID);
			}
		});
		upArrow.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				position =	mapping.get(position).up;
				info = mapping.get(position);
				position = info.name;
				if(info.hasUp){
					upArrow.setVisibility(View.VISIBLE);
				}
				else{
					upArrow.setVisibility(View.GONE);
				}
				int resID = getResources().getIdentifier(position,
						"drawable", "com.shawnhanna.shop");
				layout.setBackgroundResource(resID);
			}
		});
	}
	
	public void loadView(){
		
		mapping.put("a_0", new AisleInfo("a_0", "a_3","a_1"));
		mapping.put("a_1", new AisleInfo("a_1", "a_0","a_2"));
		mapping.put("a_2", new AisleInfo("a_2", "a_1","a_3"));
		mapping.put("a_3", new AisleInfo("a_3", "a_2","a_0", "b_2"));
		mapping.put("b_0", new AisleInfo("b_0", "b_1","b_2"));
		mapping.put("b_1", new AisleInfo("b_1", "b_3","b_0", "a_1"));
		mapping.put("b_2", new AisleInfo("b_2", "b_0","b_3", "c_0"));
		mapping.put("b_3", new AisleInfo("b_3", "b_2","b_1"));
		mapping.put("c_0", new AisleInfo("c_0", "c_1","a_2"));
		mapping.put("c_1", new AisleInfo("c_1", "c_3","c_0", "d_0"));
		mapping.put("c_2", new AisleInfo("c_2", "c_0","c_3", "e_0"));
		mapping.put("c_3", new AisleInfo("c_3", "c_2","c_1", "b_1"));
		mapping.put("d_0", new AisleInfo("d_0", "d_1","d_3"));
		mapping.put("d_1", new AisleInfo("d_1", "d_2","d_0"));
		mapping.put("d_2", new AisleInfo("d_2", "d_2","d_1", "c_2"));
		mapping.put("d_3", new AisleInfo("d_3", "d_0","d_2"));
		mapping.put("e_0", new AisleInfo("e_0", "e_2","e_1"));
		mapping.put("e_1", new AisleInfo("e_1", "e_0","e_3", "f_1"));
		mapping.put("e_2", new AisleInfo("e_2", "e_3","e_0"));
		mapping.put("e_3", new AisleInfo("e_3", "e_1","e_2", "c_1"));
		mapping.put("f_0", new AisleInfo("f_0", "f_2","f_1"));
		mapping.put("f_1", new AisleInfo("f_1", "f_0","f_3"));
		mapping.put("f_2", new AisleInfo("f_2", "f_3","f_0", "e_2"));
		mapping.put("f_3", new AisleInfo("f_3", "f_1","f_2", "b_0"));
		
		
	}

}
