package model;

public class MerchantCustomizations {
	private int quantity;
	private double merchantPrice;
	private int timesHaggled;

	public MerchantCustomizations() {
		this.quantity = 1;
		this.merchantPrice = 0.0;
		this.timesHaggled = 0;
	}

	public MerchantCustomizations(int quantity, double merchantPrice) {
		this.quantity = quantity;
		this.merchantPrice = merchantPrice;
		this.timesHaggled = 0;
	}

	public int getTimesHaggled() {
		return timesHaggled;
	}

	public void setTimesHaggled(int timesHaggled) {
		this.timesHaggled = timesHaggled;
	}

	public void incrementTimesHaggled() {
		this.timesHaggled++;
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
