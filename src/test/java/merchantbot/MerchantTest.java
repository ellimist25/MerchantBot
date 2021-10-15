package merchantbot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Item;
import model.Weapon;

public class MerchantTest {

	private Merchant merchant;

	@Before
	public void setup() {
		this.merchant = new Merchant("Patrick Shamtul", "The Fortuitous Purlieu");
		Item dragonSlayer = new Weapon("Dragon Slayer", 1000.00, "rare", "weapon");
		Item ringOfWarmth = new Item("Ring of Warmth", 750.00, "uncommon", "ring",
				"While wearing this ring, you have resistance to cold damage. " +
						"In addition, you and everything you wear and carry are " +
						"unharmed by temperatures as low as -50°.");
		dragonSlayer.setDescription("You gain a +1 bonus to attack and damage rolls when using this weapon." +
				"\nWhen you hit a dragon with this weapon, the dragon takes an extra 3d6 points of slashing damage." +
				"This applies to dragonsm dragon turtles and wyverns." +
				"\nThe weapon is cursed. Every attack has a chance to summon an extra-dimensional creature, hell-bent " +
				"on killing the wielder, unless the curse is lifted.");
		merchant.addToInventory(dragonSlayer);
		merchant.addToInventory(ringOfWarmth);
		merchant.setStartingPercentage(1.25);
		merchant.setLowestPercentage(0.75);
		merchant.setBarteringDC(15);
	}

	@Test
	public void printInventory() {
	}

	@Test
	public void findItem() {
	}

	@Test
	public void calculateStartingPrice() {
	}

	@Test
	public void testToString() {
	}
}