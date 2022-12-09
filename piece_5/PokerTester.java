package poker;

import java.util.Arrays;

public class PokerTester {

	public static void main(String[] args) {
		Deck d = new Deck();
		System.out.println(Arrays.toString(d.monteCarlo()) + "\n");

		Draw.simulate2PlayerGame();

		System.out.println("------------------------------------------------------------");

		Card c1 = new Card(2, "Heart");
		Card c2 = new Card(3, "Heart");
		Card c3 = new Card(4, "Heart");
		Card c4 = new Card(5, "Heart");
		Card c5 = new Card(6, "Heart");

		Card[] customHand = d.getCustomHand(c1, c2, c3, c4, c5);

		Draw.simulate1PlayerGame(customHand);

	}

}
