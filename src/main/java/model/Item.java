package model;

public class Item {
	private String name;
	private double marketValue;
	private String rarity;
	private String type;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Item(String name, double marketValue, String rarity, String type) {
		this.name = name;
		this.marketValue = marketValue;
		this.rarity = rarity;
		this.type = type;
	}

	public Item(String name, double marketValue, String rarity, String type, String description) {
		this.name = name;
		this.marketValue = marketValue;
		this.rarity = rarity;
		this.type = type;
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder item = new StringBuilder("~");
		item.append(name).append("~\n(").append(type).append(", ").append(rarity).
				append(" item, ").append(marketValue).append(" gp").append(")");
		if (this.description != null) {
			item.append("\nDescription: ").append(description);
		}
		return item.toString();
	}
}
