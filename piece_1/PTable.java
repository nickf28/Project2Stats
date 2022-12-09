package StatsLib;

// Objects created from this class represent a bivariate probability table for the discrete case. (ex: section 5.2, example 5.1)

public class PTable {

	private double[][] table;
	private int rows;
	private int cols;

	public PTable(int nRows, int nCols) {
		rows = nRows;
		cols = nCols;
		table = initTable(rows, cols);
	}

	public int getNumRows() {
		return rows;
	}

	public int getNumCols() {
		return cols;
	}

	public void add(int r, int c, double val) {
		table[r - 1][c - 1] = val;
	}

	public void reset(int r, int c) {
		table[r - 1][c - 1] = 0;
	}

	public double marginalProbX(int X) {
		double sum = 0;

		for (int i = 0; i < getNumCols(); i++) {
			sum += getTable()[X][i];
		}

		return sum;
	}

	public double marginalProbY(int Y) {
		double sum = 0;

		for (int i = 0; i < getNumRows(); i++) {
			sum += getTable()[i][Y];
		}

		return sum;
	}

	public void printTable() {
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumCols(); j++) {
				System.out.print(getTable()[i][j] + " ");
			}
			System.out.println();
		}
	}

	private double[][] getTable() {
		return table;
	}

	private double[][] initTable(int r, int c) {

		double[][] out = new double[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				out[i][j] = 0;
			}
		}

		return out;

	}

}
