package merchantbot;

import java.util.ArrayList;

import model.Item;

public class Merchant {
	private String name;
	private String shopName;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int barteringDC;
	private double lowestPrice;
	private double startingPrice;

	public static final int CAUGHT_IN_A_LIE = 10;
	public static final int INSULTING_OFFER = 5;
	public static final int ENTICING_OFFER = -5;

	public Merchant(String name, String shopName) {
		this.name = name;
		this.shopName = shopName;
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

	public double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public void printInventory() {
		for (Item i : this.inventory) {
			System.out.println(i.toString());
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public boolean addToInvntory(Item item) {
		return this.inventory.add(item);
	}
}
