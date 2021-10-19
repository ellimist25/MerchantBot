package model;

public class Weapon extends Item {
	public Weapon(String name, int marketValue, String rarity, String type) {
		super(name, marketValue, rarity, type);
	}

	public Weapon(String name, int marketValue, String rarity, String type, String description) {
		super(name, marketValue, rarity, type, description);
	}
}
