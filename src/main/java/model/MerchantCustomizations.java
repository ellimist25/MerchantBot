package model;

public class MerchantCustomizations {
	private int quantity;
	private double merchantPrice;

	public MerchantCustomizations() {
		this.quantity = 1;
		this.merchantPrice = 0.0;
	}

	public MerchantCustomizations(int quantity, double merchantPrice) {
		this.quantity = quantity;
		this.merchantPrice = merchantPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getMerchantPrice() {
		return merchantPrice;
	}

	public void setMerchantPrice(double merchantPrice) {
		this.merchantPrice = merchantPrice;
	}
}
