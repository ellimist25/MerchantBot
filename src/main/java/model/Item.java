package model;

public class Item {
	private String name;
	private int marketValue;
	private String rarity;
	private String type;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Item(String name, int marketValue, String rarity, String type) {
		this.name = name;
		this.marketValue = marketValue;
		this.rarity = rarity;
		this.type = type;
	}

	public Item(String name, int marketValue, String rarity, String type, String description) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(int marketValue) {
		this.marketValue = marketValue;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
