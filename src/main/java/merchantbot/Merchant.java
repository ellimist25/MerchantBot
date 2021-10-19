package merchantbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Item;
import model.MerchantCustomizations;

public class Merchant {
	public static final int CAUGHT_IN_A_LIE = 10;
	public static final int INSULTING_OFFER = 5;
	public static final int ENTICING_OFFER = -5;
	public static final double PRICE_POINT = 0.25;

	private String name;
	private String shopName;
	private ArrayList<Item> inventory;
	public Map<Item, MerchantCustomizations> enhancedInventory;
	private int barteringDC;
	private double lowestPercentage;
	private double startingPercentage;

	public Merchant(String name, String shopName) {
		this.name = name;
		this.shopName = shopName;
		this.inventory = new ArrayList<Item>();
		this.enhancedInventory = new HashMap<Item, MerchantCustomizations>();
		this.lowestPercentage = 1;
		this.startingPercentage = 1.5;
	}

	public Merchant(String name, String shopName, double lowestPercentage, double startingPercentage ) {
		this.name = name;
		this.shopName = shopName;
		this.lowestPercentage = lowestPercentage;
		this.startingPercentage = startingPercentage;
		this.inventory = new ArrayList<Item>();
		this.enhancedInventory = new HashMap<Item, MerchantCustomizations>();
	}

	public void printInventory() {
		for (Item i : this.inventory) {
			System.out.println( i.toString() );
			System.out.println("Current vendor price: " + calculateStartingPrice(i));
			System.out.println();
		}
	}

	public Item findItem(String itemName) {
		for (Map.Entry<Item, MerchantCustomizations> pair : this.enhancedInventory.entrySet()) {
			if (pair.getKey().getName().equals(itemName)) {
				return pair.getKey();
			}
		}
		return null;
	}

	public double calculateStartingPrice(Item item) {
		return item.getMarketValue() *  this.startingPercentage;
	}

	public double getMerchantPrice(Item item) {
		return this.enhancedInventory.get(item).getMerchantPrice();
	}

	public boolean addToInventory(Item item, int quantity) {
		MerchantCustomizations mc = new MerchantCustomizations(quantity, calculateStartingPrice(item));
		this.enhancedInventory.put(item, mc);
		return this.inventory.add(item);
	}

	public boolean addToInventory(Item item) {
		return addToInventory(item, 1);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public Map<Item, MerchantCustomizations> getEnhancedInventory() {
		return enhancedInventory;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public int getBarteringDC() {
		return barteringDC;
	}

	public void setBarteringDC(int barteringDC) {
		this.barteringDC = barteringDC;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public double getLowestPercentage() {
		return lowestPercentage;
	}

	public void setLowestPercentage(double lowestPercentage) {
		this.lowestPercentage = lowestPercentage;
	}

	public double getStartingPercentage() {
		return startingPercentage;
	}

	public void setStartingPercentage(double startingPercentage) {
		this.startingPercentage = startingPercentage;
	}
}
