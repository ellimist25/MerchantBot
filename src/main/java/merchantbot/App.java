package merchantbot;

import java.util.ArrayList;
import java.util.Scanner;

import model.Item;
import model.Weapon;

public class App {

	private static Merchant merchant;

	public static void main(String[] args) {
		Merchant pat = new Merchant("Patrick Shamtul", "The Fortuitous Purlieu");
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
		pat.addToInventory(dragonSlayer);
		pat.addToInventory(ringOfWarmth);
		pat.setStartingPrice(1.25);
		pat.setLowestPrice(0.75);
		pat.setBarteringDC(15);

		game(pat);
	}

	private static void game(Merchant merchant) {
		Scanner scanner = new Scanner(System.in);
		boolean endGame = false;
		printMenu(merchant);
		while (!endGame) {
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
				case 0:
					System.out.println("Thanks for shopping at " + merchant.getShopName());
					endGame = true;
					break;
				case 1:
					merchant.printInventory();
					System.out.println("\n==========================================");
					printMenu(merchant);
					break;
				case 2:
					haggleInterface(merchant);
					break;
				case 3:
					// purchase something
					break;
				case 4:
					// sell something
					break;
				case 5:
					printMenu(merchant);
					break;
			}
		}
	}

	private static void printMenu(Merchant merchant) {
		System.out.println("Welcome to " + merchant.getShopName() + "! What would you like to do?");
		System.out.println("1: Print out list of available items.");
		System.out.println("2: Try to haggle with the shopkeeper.");
		System.out.println("3: Purchase something!");
		System.out.println("4: Sell something.");
		System.out.println("5: Print menu.");
		System.out.println("0: End conversation and quit.");
	}

	private static void testDice() {
		for (int i = 0; i <= 2_000_000; i++) {
			int roll = Dice.roll("d20", false);
			if (roll > 20 || roll < 1) {
				System.out.println("Something is wrong: " + roll);
			}
		}
	}

	/**
	 * Haggle interface
	 * Ask if there should be any modifications to the DC (caught in a lie, etc)
	 * Print out the DC
	 * Ask if the PC succeeded or failed the check
	 * Print out the current price, after any bargaining changes
	 */
	private static void haggleInterface(Merchant merchant) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What item would you like to haggle with?");

		ArrayList<Item> inventory = merchant.getInventory();
		for ( int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			StringBuilder stuff = new StringBuilder();
			stuff.append(i+1).append(" ").append(item.getName()).append(" - ").
					append(merchant.calculateStartingPrice(item)).append(" gp");
			System.out.println(stuff.toString());
		}
		int itemChoice = scanner.nextInt() - 1;
		scanner.nextLine();
		System.out.println("Modify the DC?");
		System.out.println("0. No modification.");
		System.out.println("1. Caught in a Lie?");
		System.out.println("2. Insulting Offer? (50%)");
		System.out.println("3. Enticing Offer? (PC offered something I want?)");
		int dcModification = scanner.nextInt();
		scanner.nextLine();
		int barteringDC = merchant.getBarteringDC();
		if ( dcModification < 0 || dcModification > 3 ) {
			System.out.println("Invalid option");
			printMenu(merchant);
			return;
		} else if (dcModification == 0) {
			System.out.println(merchant.getName() + "'s bargaining DC is " + barteringDC);
		} else {
			System.out.println(merchant.getName() + "'s adjusted bargaining DC is " + barteringDC);
			if (dcModification == 1) { barteringDC += 10; }
			if (dcModification == 2) { barteringDC += 5; }
			if (dcModification == 3) { barteringDC -= 10; }
		}

		System.out.println("What did the PC roll?");
		int pcRoll = scanner.nextInt();
		scanner.nextLine();
		if (pcRoll >= merchant.getBarteringDC()) {
			double marketValue = inventory.get(itemChoice).getMarketValue();
			double startingPrice = marketValue * merchant.getStartingPrice();
			double currentPrice = startingPrice - (marketValue * Merchant.PRICE_POINT);
			System.out.println("PC bargained successfully! Price of item is now " + currentPrice);
		}
	}
}
