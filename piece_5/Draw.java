package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Draw {

	static Deck d = new Deck();
	static ArrayList<Card> shuffledDeck = new ArrayList<>(d.getDeck());

	public static String determineWinner(Card[] h1, Card[] h2) {

		int[] w1 = d.evaluateHand(h1);
		int[] w2 = d.evaluateHand(h2);

		int highestHandIndexH1 = 0;
		int highestHandIndexH2 = 0;

		int highCardH1 = w1[0];
		int highCardH2 = w2[0];

		for (int i = 9; i >= 1; i--) {
			if (w1[i] == 1) {
				highestHandIndexH1 = i;
				break;
			}
		}

		for (int i = 9; i >= 1; i--) {
			if (w2[i] == 1) {
				highestHandIndexH2 = i;
				break;
			}
		}

		String winner = "";
		String winningHand = "";

		if (highestHandIndexH1 == 0 && highestHandIndexH2 == 0) {
			winningHand = "High card";

			if (highCardH1 == highCardH2)
				winner = "DRAW";
			else if (highCardH1 > highCardH2)
				winner = "P1";
			else
				winner = "P2";

		} else if (highestHandIndexH1 > highestHandIndexH2) {
			winner = "P1";
			winningHand = getWinningHandName(highestHandIndexH1);
		} else if (highestHandIndexH2 > highestHandIndexH1) {
			winner = "P2";
			winningHand = getWinningHandName(highestHandIndexH2);
		} else if (highestHandIndexH1 == highestHandIndexH2) {
			winner = "DRAW";
			winningHand = getWinningHandName(highestHandIndexH1);
		}

		return winner + ", " + winningHand;

	}

	public static void simulate1PlayerGame(Card[] playerHand) {
		Card[] cpuHand = d.getHand();

		System.out.println("YOU: " + Arrays.toString(playerHand));
		System.out.println("CPU: " + Arrays.toString(cpuHand) + "\n");

		System.out.println("YOUR outcome: " + Arrays.toString(d.evaluateHand(playerHand)));
		System.out.println("CPU outcome: " + Arrays.toString(d.evaluateHand(cpuHand)));

		System.out.println("\nWINNER: " + Draw.determineWinner(playerHand, cpuHand));
	}

	public static void simulate2PlayerGame() {
		ArrayList<Card[]> hands = Draw.get2Hands();

		System.out.println("2 random cards drawn:");
		System.out.println("P1: " + Arrays.toString(hands.get(0)));
		System.out.println("P2: " + Arrays.toString(hands.get(1)) + "\n");

		System.out.println("P1 outcome: " + Arrays.toString(d.evaluateHand(hands.get(0))));
		System.out.println("P2 outcome: " + Arrays.toString(d.evaluateHand(hands.get(1))));

		System.out.println("\nWINNER: " + Draw.determineWinner(hands.get(0), hands.get(1)));
	}

	public static ArrayList<Card[]> get2Hands() {
		ArrayList<Card[]> hands = new ArrayList<>();

		Card[] out1 = new Card[5];

		Collections.shuffle(shuffledDeck);

		Random r = new Random();

		int deckSize = 52;

		for (int i = 0; i < 5; i++) {
			int rand = r.nextInt(deckSize);

			out1[i] = shuffledDeck.remove(rand);
			deckSize--;
		}

		hands.add(d.sortHand(out1));

		Card[] out2 = new Card[5];

		for (int i = 0; i < 5; i++) {
			int rand = r.nextInt(deckSize);

			out2[i] = shuffledDeck.remove(rand);

			deckSize--;
		}

		hands.add(d.sortHand(out2));

		return hands;

	}

	public static Card[] removeCardsfromHand(Card[] hand, int[] indicesToRemove) {

		ArrayList<Card> newHand = new ArrayList<>(Arrays.asList(hand));

		for (int i = 0; i < indicesToRemove.length; i++)
			shuffledDeck.add(newHand.remove(indicesToRemove[i]));

		return null;

	}

	public static Card[] refillHand(Card[] hand) {

		Collections.shuffle(shuffledDeck);

		ArrayList<Card> newHand = new ArrayList<>(Arrays.asList(hand));

		Random r = new Random();

		for (int i = 0; i < 5 - hand.length; i++) {
			newHand.add(shuffledDeck.remove(r.nextInt(shuffledDeck.size())));
		}

		Card[] out = new Card[5];

		for (int i = 0; i < 5; i++)
			out[i] = newHand.get(i);

		return out;

	}

	private static String getWinningHandName(int index) {
		String out = "";

		switch (index) {
		case 1:
			out = "Pair";
			break;
		case 2:
			out = "Two pair";
			break;
		case 3:
			out = "Three of kind";
			break;
		case 4:
			out = "Straight";
			break;
		case 5:
			out = "Flush";
			break;
		case 6:
			out = "Full house";
			break;
		case 7:
			out = "Four of kind";
			break;
		case 8:
			out = "Straight flush";
			break;
		case 9:
			out = "Royal flush";
			break;
		default:
			out = "";
			break;
		}

		return out;

	}

}
