package merchantbot;

import java.util.Random;

public class Dice {

	public static int roll(String die) {
		return roll(die, true);
	}

	public static int roll(String die, boolean printOutput) {
		int desiredDie = determineDie(die);
		if (desiredDie == -1) {
			return -1;
		}
		int roll = (new Random().nextInt(desiredDie - 1)) + 1;
		if (printOutput) { System.out.println(roll); }
		return roll;
	}

	private static int determineDie(String die) {
		// only allow d1-d999
		String regexTwoDigit = "[dD][0-9]";
		String regexThreeDigit = "[dD][0-9][0-9]";
		String regexFourDigit = "[dD][0-9][0-9][0-9]";
		if ( !(die.matches(regexTwoDigit) || die.matches(regexThreeDigit) || die.matches(regexFourDigit)) ) {
			System.out.println("Invalid die format.");
			return -1;
		}
		return Integer.parseInt( die.substring(1) );
	}
}
