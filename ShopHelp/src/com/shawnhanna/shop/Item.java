package com.shawnhanna.shop;


/*
 * CLASS DESCRIPTION
 * This class is used to store item information and status after a query is performed.
 * quantity MUST be tracked and pushed back to the server. The other class
 * variables only have accessors, and not manipulators because their values must match
 * the server values
 */
public class Item {
	private String name;
	private String shortName;
	private double price;
	private double barcode; // NOTE: I dont actually know how barcodes are
							// stored - john, 3/25
	private int quantity;
	private int locationID;

	// all items are initialized from the parameters, but quantity is set to 0,
	// and inCart is set to false
	public Item(String newName, String newShortName, double newPrice,
			double newBarcode, int newLocationID) {
		name = newName;
		shortName = newShortName;
		price = newPrice;
		barcode = newBarcode;
		locationID = newLocationID;
		quantity = 1;
	}

	public Item() {
		name = "NO NAME";
		shortName = "NO NAME";
		price = -1;
		barcode = -1;
		locationID = -1;
		quantity = -1;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public double getLocationID() {
		return locationID;
	}

	public double getBarcode() {
		return barcode;
	}

	public double getPrice() {
		return price;
	}

	public int incrementQuantity() {
		return quantity++;
	}

	public int decrementQuantity() {
		return quantity--;
	}

	public int setQuantityToZero() {
		return quantity = 0;
	}

	public int getQuantity() {
		return quantity;
	}

	public int setQuantity(int newQuantity) {
		return quantity = newQuantity;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Item))
			return false;
		Item otherItem = (Item) other;
		if (otherItem.getPrice() != getPrice())
			return false;
		if (otherItem.getBarcode() != getBarcode())
			return false;
		if (otherItem.getLocationID() != getLocationID())
			return false;
		if (otherItem.getName() != getName())
			return false;
		return true;
	}
}
