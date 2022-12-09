package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private ArrayList<Card> deck;

	public Deck() {
		deck = populateList();
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public Card[] getHand() {
		Card[] out = new Card[5];

		ArrayList<Card> shuffledDeck = new ArrayList<>(getDeck());

		Collections.shuffle(shuffledDeck);

		Random r = new Random();

		int deckSize = 52;

		for (int i = 0; i < 5; i++) {
			int rand = r.nextInt(deckSize);

			out[i] = shuffledDeck.remove(rand);

			deckSize--;
		}

		return sortHand(out);
	}

	public Card[] getCustomHand(Card a, Card b, Card c, Card d, Card e) {
		Card[] out = new Card[5];

		out[0] = a;
		out[1] = b;
		out[2] = c;
		out[3] = d;
		out[4] = e;

		return sortHand(out);
	}

	public double[] monteCarlo() {
		double[] out = new double[10];

		Card[] hand;

		double highCard = 0, pair = 0, twoPair = 0, threeKind = 0, straight = 0, flush = 0, fullHouse = 0, fourKind = 0,
				straightFlush = 0, royalFlush = 0;

		int trials = 100000;

		for (int i = 0; i < trials; i++) {

			hand = getHand();

			int[] win = evaluateHand(hand);

			highCard += boolToInt(isHighCard(hand));
			pair += win[1];
			twoPair += win[2];
			threeKind += win[3];
			straight += win[4];
			flush += win[5];
			fullHouse += win[6];
			fourKind += win[7];
			straightFlush += win[8];
			royalFlush += win[9];

		}

		out[0] = highCard / trials;
		out[1] = pair / trials;
		out[2] = twoPair / trials;
		out[3] = threeKind / trials;
		out[4] = straight / trials;
		out[5] = flush / trials;
		out[6] = fullHouse / trials;
		out[7] = fourKind / trials;
		out[8] = straightFlush / trials;
		out[9] = royalFlush / trials;

		System.out.println("Probability of each type of win from pair - royal flush:");
		return out;
	}

	public int[] evaluateHand(Card[] hand) {
		int[] list = new int[10];

		list[0] = hand[4].getRank();
		list[1] = boolToInt(isPair(hand));
		list[2] = boolToInt(isTwoPair(hand));
		list[3] = boolToInt(isThreeOfKind(hand));
		list[4] = boolToInt(isStraight(hand));
		list[5] = boolToInt(isFlush(hand));
		list[6] = boolToInt(isFullHouse(hand));
		list[7] = boolToInt(isFourofKind(hand));
		list[8] = boolToInt(isStraightFlush(hand));
		list[9] = boolToInt(isRoyalFlush(hand));

		return list;

	}

	Card[] sortHand(Card[] hand) {

		ArrayList<Card> temp = new ArrayList<>(Arrays.asList(hand));

		temp.sort((card1, card2) -> card1.getRank() - card2.getRank());

		Card[] out = new Card[5];

		for (int i = 0; i < 5; i++)
			out[i] = temp.get(i);

		return out;
	}

	private boolean isRoyalFlush(Card[] hand) {
		boolean b = false;

		Card c1 = hand[0];
		Card c2 = hand[1];
		Card c3 = hand[2];
		Card c4 = hand[3];
		Card c5 = hand[4];

		if (isFlush(hand)) {

			if (c1.getRank() == 1 && c2.getRank() == 10 && c3.getRank() == 11 && c4.getRank() == 12
					&& c5.getRank() == 13)
				b = true;

		}

		return b;
	}

	private boolean isStraightFlush(Card[] hand) {
		boolean b = false;

		if (isFlush(hand) && isStraight(hand))
			b = true;

		return b;
	}

	private boolean isFourofKind(Card[] hand) {
		boolean b = false;

		Card c1 = hand[0];
		Card c2 = hand[1];
		Card c4 = hand[3];
		Card c5 = hand[4];

		if (c1.getRank() == c4.getRank() || c2.getRank() == c5.getRank())
			b = true;

		return b;
	}

	private boolean isFullHouse(Card[] hand) {
		boolean b = false;

		Card c1 = hand[0];
		Card c2 = hand[1];
		Card c3 = hand[2];
		Card c4 = hand[3];
		Card c5 = hand[4];

		Card[] h1 = new Card[3];
		h1[0] = c1;
		h1[1] = c2;
		h1[2] = c3;

		Card[] h2 = new Card[3];
		h2[0] = c2;
		h2[1] = c3;
		h2[2] = c4;

		Card[] h3 = new Card[3];
		h3[0] = c3;
		h3[1] = c4;
		h3[2] = c5;

		if (isThreeOfKindCustom(h1)) {

			Card[] p = { c4, c5 };

			if (isPair(p)) {
				b = true;
			}

		} else if (isThreeOfKindCustom(h2)) {

			Card[] p = { c1, c5 };

			if (isPair(p)) {
				b = true;
			}

		} else if (isThreeOfKindCustom(h3)) {

			Card[] p = { c1, c2 };

			if (isPair(p)) {
				b = true;
			}

		}

		return b;
	}

	private boolean isFlush(Card[] hand) {
		return Card.isSameSuit(hand);
	}

	private boolean isStraight(Card[] hand) {
		boolean b = false;

		Card c1 = hand[0];
		Card c2 = hand[1];
		Card c3 = hand[2];
		Card c4 = hand[3];
		Card c5 = hand[4];

		int initialRank = c1.getRank();

		if (initialRank > 9)
			b = false;
		else {
			int c2shouldBe = initialRank + 1;
			int c3shouldBe = c2shouldBe + 1;
			int c4shouldBe = c3shouldBe + 1;
			int c5shouldBe = c4shouldBe + 1;

			if (c2.getRank() == c2shouldBe && c3.getRank() == c3shouldBe && c4.getRank() == c4shouldBe
					&& c5.getRank() == c5shouldBe) {
				b = true;
			}

		}

		return b;
	}

	private boolean isThreeOfKind(Card[] hand) {
		boolean b = false;

		Card c1 = hand[0];
		Card c2 = hand[1];
		Card c3 = hand[2];
		Card c4 = hand[3];
		Card c5 = hand[4];

		if (c1.getRank() == c3.getRank() || c2.getRank() == c4.getRank() || c3.getRank() == c5.getRank())
			b = true;

		return b;
	}

	// used for isfullHouse method
	private boolean isThreeOfKindCustom(Card[] hand) {
		boolean b = false;

		Card c1 = hand[0];
		Card c3 = hand[2];

		if (c1.getRank() == c3.getRank())
			b = true;

		return b;
	}

	private boolean isTwoPair(Card[] hand) {
		boolean b = false;

		int c = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 5; j++) {

				if (hand[i].getRank() == hand[j].getRank())
					c++;

			}

		}

		if (c == 2)
			b = true;

		return b;
	}

	private boolean isPair(Card[] hand) {
		boolean b = false;

		int c = 0;

		for (int i = 0; i < hand.length; i++) {
			for (int j = i + 1; j < hand.length; j++) {

				if (hand[i].getRank() == hand[j].getRank())
					c++;

			}
		}

		if (c == 1)
			b = true;

		return b;
	}

	private boolean isHighCard(Card[] hand) {
		boolean b = false;

		if (!isPair(hand) && !isTwoPair(hand) && !isThreeOfKind(hand) && !isStraight(hand) && !isFlush(hand)
				&& !isFullHouse(hand) && !isFourofKind(hand) && !isStraightFlush(hand) && !isRoyalFlush(hand))
			b = true;

		return b;

	}

	private static int boolToInt(boolean b) {
		int out = 0;

		if (b)
			out = 1;

		return out;

	}

	private static ArrayList<Card> populateList() {

		int[] rank = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
		String[] suit = { "Heart", "Diamond", "Spade", "Club" };

		ArrayList<Card> out = new ArrayList<>();

		for (int i = 0; i < 13; i++)
			out.add(new Card(rank[i], suit[0]));

		for (int i = 0; i < 13; i++)
			out.add(new Card(rank[i], suit[1]));

		for (int i = 0; i < 13; i++)
			out.add(new Card(rank[i], suit[2]));

		for (int i = 0; i < 13; i++)
			out.add(new Card(rank[i], suit[3]));

		return out;

	}

}
