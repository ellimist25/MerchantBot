package merchantbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Item;
import model.MerchantCustomizations;
import model.Weapon;

public class App {

	private static Merchant merchant;
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		Merchant pat = populateMerchant();
		game(pat);
	}

	private static void game(Merchant merchant) {
		boolean endGame = false;
		while (!endGame) {
			printMenu(merchant.getShopName());
			int choice = scanner.nextInt(); scanner.nextLine();
			switch(choice) {
				case 1:
					merchant.printInventory();
					System.out.println("\n==========================================");
					break;
				case 2:
					// try to haggle something
					haggleInterface(merchant);
					break;
				case 3:
					// purchase something
					purchaseSomething(merchant);
					break;
				case 4:
					// sell something
					break;
				case 5:
					printMenu(merchant.getShopName());
					break;
				case 6:
					System.out.println("Thanks for shopping at " + merchant.getShopName());
					endGame = true;
					break;
			}
		}
	}

	private static void printMenu(String shopName) {
		System.out.println("Welcome to " + shopName + "! What would you like to do?");
		System.out.println("1: Print out list of available items.");
		System.out.println("2: Try to haggle with the shopkeeper.");
		System.out.println("3: Purchase something!");
		System.out.println("4: Sell something.");
		System.out.println("5: Print menu.");
		System.out.println("6: End conversation and quit.");
	}

	private static void testDice() {
		for (int i = 0; i <= 2_000_000; i++) {
			int roll = Dice.roll("d20", false);
			if (roll > 20 || roll < 1) {
				System.out.println("Something is wrong: " + roll);
			}
		}
	}

	private static void purchaseSomething(Merchant merchant) {
		printMerchantInventorySimple(merchant);
		System.out.println("What would you like to purchase?");
		int choice = scanner.nextInt() - 1; scanner.nextLine();
		Item chosenItem = merchant.getInventory().get(choice);
		MerchantCustomizations mc = merchant.getEnhancedInventory().get(chosenItem);
		if (mc.getQuantity() > 0) {
			mc.setQuantity( mc.getQuantity() - 1 );
			System.out.println("Purchase complete! Enjoy your new " + chosenItem.getName());
			System.out.println();
		} else {
			System.out.println("Cannot purchase: merchant is out of stock.");
		}
	}

	private static void haggleInterface(Merchant merchant) {
		System.out.println("What item would you like to haggle with?");
		printMerchantInventorySimple(merchant);
		int itemChoice = scanner.nextInt() - 1; scanner.nextLine();
		int finalBargainingDC = calculateBargainDC(merchant.getBarteringDC());
		if ( finalBargainingDC == -1 ) {
			printMenu(merchant.getShopName());
			return;
		}
		System.out.println(merchant.getName() + "'s bargaining DC is " + finalBargainingDC);
		System.out.println("What did the PC roll?");
		int pcRoll = scanner.nextInt(); scanner.nextLine();
		ArrayList<Item> inventory = merchant.getInventory();
		Item chosenItem = inventory.get(itemChoice);
		MerchantCustomizations chosenItemCustomization = merchant.getEnhancedInventory().get(chosenItem);

		// bartering failed or maximum haggle attempts reached
		if (chosenItemCustomization.getTimesHaggled() >= Merchant.MAX_HAGGLE_TIMES || pcRoll < merchant.getBarteringDC()) {
			System.out.println(merchant.getName() + " says: \"I'm sorry friend, but this is as low as I can go.\"");
			System.out.println("The price remains unchanged: " + chosenItemCustomization.getMerchantPrice());
		}

		// bartering was successful. reduce price of item by 25% and update the current merchant price
		if (pcRoll >= merchant.getBarteringDC() && chosenItemCustomization.getTimesHaggled() < Merchant.MAX_HAGGLE_TIMES) {
			chosenItemCustomization.incrementTimesHaggled();
			double marketValue = chosenItem.getMarketValue();
			double currentPrice = chosenItemCustomization.getMerchantPrice() - (marketValue * Merchant.PRICE_POINT);
			chosenItemCustomization.setMerchantPrice(currentPrice);
			System.out.println("PC bargained successfully! Price of item is now " + currentPrice);
		}
		return;
	}

	private static void printMerchantInventorySimple(Merchant merchant) {
		ArrayList<Item> inventory = merchant.getInventory();
		for ( int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			System.out.println((i+1) + " " + item.getName() + " - " + merchant.getMerchantPrice(item) + " gp");
		}
	}

	private static int calculateBargainDC(int merchantBargainingDC) {
		System.out.println("Modify the DC?");
		System.out.println("1. No modification.");
		System.out.println("2. Caught in a Lie?");
		System.out.println("3. Insulting Offer? (50%)");
		System.out.println("4. Enticing Offer? (PC offered something I want?)");

		int dcModification = scanner.nextInt(); scanner.nextLine();

		if ( dcModification < 1 || dcModification > 4 ) {
			System.out.println("Invalid option");
			printMenu(merchant.getShopName());
			return -1;
		}

		if (dcModification == 2) { merchantBargainingDC += Merchant.CAUGHT_IN_A_LIE; }
		if (dcModification == 3) { merchantBargainingDC += Merchant.INSULTING_OFFER; }
		if (dcModification == 4) { merchantBargainingDC -= Merchant.ENTICING_OFFER; }
		return merchantBargainingDC;
	}

	private static Merchant populateMerchant() {
		Merchant pat = new Merchant("Patrick Shamtul", "The Fortuitous Purlieu", 0.75, 1.25);
		Item dragonSlayer = new Weapon("Dragon Slayer", 1000, "rare", "weapon");
		Item ringOfWarmth = new Item("Ring of Warmth", 750, "uncommon", "ring",
				"While wearing this ring, you have resistance to cold damage. " +
						"In addition, you and everything you wear and carry are " +
						"unharmed by temperatures as low as -50??.");
		dragonSlayer.setDescription("You gain a +1 bonus to attack and damage rolls when using this weapon." +
				"\nWhen you hit a dragon with this weapon, the dragon takes an extra 3d6 points of slashing damage." +
				"This applies to dragons dragon turtles and wyverns." +
				"\nThe weapon is cursed. Every attack has a chance to summon an extra-dimensional creature, hell-bent " +
				"on killing the wielder, unless the curse is lifted.");
		pat.addToInventory(dragonSlayer);
		pat.addToInventory(ringOfWarmth);
		pat.setBarteringDC(15);
		return pat;
	}
}
