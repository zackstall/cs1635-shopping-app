package com.shawnhanna.shop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;
import android.util.Log;

/**
 * 
 * @author Shawn -
 * @brief This is a singleton storage class that stores to the internal file
 *        storage
 */
public class DataService {
	private static final String TAG = "DataService";

	private Lock cartLock = new ReentrantLock();
	private Lock dbLock = new ReentrantLock();
	private ArrayList<Item> db = new ArrayList<Item>(50);
	private ArrayList<Item> cartList = new ArrayList<Item>(50);

	private static DataService singleton;

	private DataService() {
	}

	public static synchronized DataService getInstance() {
		if (DataService.singleton == null) {
			singleton = new DataService();
			singleton.load();
		}
		return singleton;
	}

	public ArrayList<Item> getCart() {
		cartLock.lock();
		@SuppressWarnings("unchecked")
		ArrayList<Item> retList = (ArrayList<Item>) cartList.clone();
		cartLock.unlock();
		return retList;
	}

	public ArrayList<Item> getDB() {
		dbLock.lock();
		@SuppressWarnings("unchecked")
		ArrayList<Item> retList = (ArrayList<Item>) db.clone();
		dbLock.unlock();
		return retList;
	}
	
	//this is not very efficient, feel free to change it if you have time - john 3/30
	public ArrayList<Item> getDBMinusCart(){
		ArrayList<Item> newArray = new ArrayList<Item>();
		for(int i = 0; i < db.size(); i++){
			if(!cartList.contains(db.get(i))) newArray.add(db.get(i));
		}
		return newArray;
	}

	public void addToCart(Item item) {
		cartLock.lock();
		if (!this.inCart(item))
			cartList.add(item);
		cartLock.unlock();
	}

	protected void addToDB(Item item) {
		dbLock.lock();
		if (!this.inDB(item))
			db.add(item);
		dbLock.unlock();
	}
	
	public void removeFromCart(Item item) {
		cartLock.lock();
		if (this.inCart(item)){
			item.setQuantity(1);
			cartList.remove(item);
		}
		cartLock.unlock();
	}

	private boolean inDB(Item item) {
		for (int i = 0; i < db.size(); i++) {
			if (db.get(i).equals(item)) {
				return true;
			}
		}
		return false;
	}

	public boolean inCart(Item item) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).equals(item)) {
				return true;
			}
		}
		return false;
	}

	public boolean incrementItem(Item item) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).equals(item)) {
				cartList.get(i).incrementQuantity();
				return true;
			}
		}
		return false;
	}

	public boolean decrementItem(Item item) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).equals(item)) {
				cartList.get(i).decrementQuantity();
				return true;
			}
		}
		return false;
	}
	
	public boolean replaceInCart(Item item) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).equals(item)) {
				cartList.set(i, item);
				return true;
			}
		}
		return false;
	}
	
	public String getCartPriceAsString(){
		double price = 0;

		for (int i = 0; i < cartList.size(); i++) {
			price+=(cartList.get(i).getPrice()*cartList.get(i).getQuantity());
		}

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(price);
	}

	/**
	 * saves the data so it is persistent even after being destroyed by the OS
	 * Call this when the application is about to be destroyed
	 */
	public void save() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(ShopActivity.getContext());
		Editor editor = prefs.edit();
		editor.putString("cartList", objectToString(cartList));
		editor.putString("db", objectToString(db));
		editor.commit();
	}

	protected void load() {
		addToDB(new Item("Ruffles Potato Chips", "Potato Chips", 3.99, 123456,
				4));
		addToDB(new Item("Schneiders 2% Milk", "2% Milk", 3.59, 123457, 9));
		addToDB(new Item("Jiffy Peanut Butter", "Peanut Butter", 4.25, 123458,
				3));
		addToDB(new Item("Coca-Cola", "Pop", 2.24, 123459, 2));

		// SharedPreferences prefs =
		// PreferenceManager.getDefaultSharedPreferences(ShopActivity.getContext());
		// if (prefs.getString("cartList", null) != null)
		// cartList = (ArrayList<Item>)
		// stringToObject(prefs.getString("cartList", null));
		// db = (ArrayList<Item>) stringToObject(prefs.getString("db", null));
	}

	public Editor getPreferenceEditor() {
		return PreferenceManager.getDefaultSharedPreferences(
				ShopActivity.getContext()).edit();
	}

	public void syncToServer() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public ArrayList<Item> searchDBByName(String string) {
		ArrayList<Item> ret = new ArrayList<Item>(db.size());
		for (int i = 0; i < db.size(); i++) {
			Item item = db.get(i);
			if (item.getName().toUpperCase().contains((string.toUpperCase()))
					|| item.getShortName().toUpperCase().contains(string.toUpperCase())) {
				ret.add(item);
			}
		}
		return ret;
	}

	public ArrayList<Item> searchDBByNameIgnoreCart(String string) {
		ArrayList<Item> availableItems = new ArrayList<Item>(getDBMinusCart());
		ArrayList<Item> ret = new ArrayList<Item>(availableItems.size());
		for (int i = 0; i < availableItems.size(); i++) {
			Item item = availableItems.get(i);
			if (item.getName().toUpperCase().contains((string.toUpperCase()))
					|| item.getShortName().toUpperCase().contains(string.toUpperCase())) {
				ret.add(item);
			}
		}
		return ret;
	}
	
	

	private static String objectToString(Serializable object) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			new ObjectOutputStream(out).writeObject(object);
			byte[] data = out.toByteArray();
			out.close();
			out = new ByteArrayOutputStream();
			Base64OutputStream b64 = new Base64OutputStream(out, 0);
			b64.write(data);
			b64.close();
			out.close();
			return new String(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object stringToObject(String encodedObject) {
		if (encodedObject == null) {
			Log.d(TAG, "could not create object... string is null");
			return null;
		}
		try {
			return new ObjectInputStream(new Base64InputStream(
					new ByteArrayInputStream(encodedObject.getBytes()), 0))
					.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
