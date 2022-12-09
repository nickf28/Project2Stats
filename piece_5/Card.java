package poker;

public class Card {

	private int rank;
	private String suit;

	// rank: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
	// (1 = ace, 11 j, 12 q, 13 k)
	// suit: Hearts, Diamonds, Spades, Clubs

	public Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

	// checks if hand is all of same suit
	public static boolean isSameSuit(Card[] hand) {
		boolean out = false;

		int a = 0;

		for (int i = 1; i < hand.length; i++) {
			if (hand[i].getSuit() == hand[i - 1].getSuit())
				a++;
		}

		if (a == hand.length - 1)
			out = true;

		return out;
	}

	// checks if hand is all of same rank
	public static boolean isSameRank(Card[] hand) {
		boolean out = false;

		int a = 0;

		for (int i = 1; i < hand.length; i++) {
			if (hand[i].getRank() == hand[i - 1].getRank())
				a++;
		}

		if (a == hand.length - 1)
			out = true;

		return out;
	}

	public String toString() {
		String out = "";

		out += getSuit() + " | " + rank;

		return out;
	}

}
