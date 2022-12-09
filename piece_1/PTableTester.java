package StatsLib;

public class PTableTester {

	public static void main(String[] args) {
		PTable p = new PTable(3, 3);

		/*
		 * I understand that these numbers should less than or equal to 1, but I chose
		 * to use ints for simplicity.
		 */

		p.add(1, 1, 1);
		p.add(2, 1, 2);
		p.add(3, 1, 1);

		p.add(1, 2, 2);
		p.add(2, 2, 2);
		p.add(3, 2, 0);

		p.add(1, 3, 1);
		p.add(2, 3, 0);
		p.add(3, 3, 0);

		p.printTable();

		System.out.println();

		System.out.println(p.marginalProbX(0));
		System.out.println(p.marginalProbY(2));

	}

}
