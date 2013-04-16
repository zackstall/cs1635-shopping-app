package com.shawnhanna.shop;

import java.util.ArrayList;

import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.ViewfinderView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

public class BarcodeActivity extends CaptureActivity{
	static final String TAG = "BarcodeActivity";

	private String barcodeId;
	private Bitmap barcode;
	private ArrayList<Item> cartList;
	private ArrayList<Item> checkedList;
	private ArrayList<Item> dbList;
	private DataService db;
	
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		
		db = DataService.getInstance();
		dbList = db.getDB();
		Log.d("DEBUG", "CART : "+cartList);
		Log.d("DEBUG", "LIST : "+checkedList);
		Log.d("DEBUG", "DB   : "+ dbList);
	}
	@SuppressWarnings("unused")
	public void handleDecodeExternally(Result rawResult, Bitmap barcode) {
		this.barcode = barcode;
		barcodeId = rawResult.toString();
		Item item = searchDatabase(barcodeId);
		if (barcode != null) {
			viewfinderView.drawResultBitmap(barcode);
		}
		
		if (item != null){
			statusView.setText(item.getName());
			itemRecognized(barcodeId , item);
		}
		else{
			statusView.setText(barcodeId);
			itemNotRecognized(barcodeId);
		}
		
	}
	private void itemRecognized(String barcode , Item it){
		Item item = null;
		boolean inCart = false;
		boolean isChecked = false;
		cartList = db.getCart();
		checkedList = db.getChecked();
		for(Item i : cartList){
			if(i.getBarcode().equals(barcode)){
				inCart = true;
				item = i;
				break;
			}
		}
		if(inCart){
			if(checkedList.contains(item)){
				itemAlreadyChecked(item);
			}
			else{
				addItemToChecked(item);
			}
		}
		else{
			addItemToCart(it);
		}
	}
	
	private void itemAlreadyChecked(Item item){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Scan Results");
		alertDialogBuilder
			.setMessage("Item already placed in cart")
			.setCancelable(false)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id){
					dialog.cancel();
					restartPreviewAfterDelay(0);
				}
			});
	
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	private void addItemToChecked(Item item){
		db.addToChecked(item);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Scan Results");
		alertDialogBuilder
			.setMessage(item.getShortName()+" placed in cart.")
			.setCancelable(false)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id){
					dialog.cancel();
					restartPreviewAfterDelay(0);
				}
			});
	
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();		
	}
	
	private void addItemToCart(Item item){
		db.addToCart(item);
		db.addToChecked(item);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Scan Results");
		alertDialogBuilder
			.setMessage(item.getShortName()+" placed in cart.")
			.setCancelable(false)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id){
					dialog.cancel();
					restartPreviewAfterDelay(0);
				}
			});
	
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();		
	}
	
	private void itemNotRecognized(String barcode){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Scan Results");
		
		alertDialogBuilder
			.setMessage("Item not found in database")
			.setCancelable(false)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id){
					dialog.cancel();
					restartPreviewAfterDelay(0);
				}
			});
		
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	public Item searchDatabase(String barcode){
		for(Item item : dbList){
			if(item.getBarcode().equals(barcode)){
				return item;
			}
		}
		return null;
	}
}
