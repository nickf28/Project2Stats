package Graph;

import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MyFunction {

	private double h;
	private double k;
	private int range;
	private JFreeChart graph;
	private ArrayList<Double> saltedVals;

	public MyFunction(double h, double k, int range) {
		this.h = h;
		this.k = k;
		this.range = range;
		graph = ChartFactory.createXYLineChart("Function", "X", "Y", generateData(), PlotOrientation.VERTICAL, true,
				false, false);
		saltedVals = new ArrayList<>();
	}

	public void displayAllGraphs(double saltRange) {
		displayGraph();
		displaySaltedGraph(saltRange);
		displaySmoothGraph();
	}

	private void displayGraph() {
		ChartFrame frame = new ChartFrame("Function", graph);

		frame.setVisible(true);
		frame.setSize(600, 500);
	}

	private void displaySaltedGraph(double saltRange) {
		graph = ChartFactory.createXYLineChart("Salted Function", "X", "Y", generateSaltedData(saltRange),
				PlotOrientation.VERTICAL, true, false, false);

		ChartFrame frame = new ChartFrame("Salted Function", graph);

		frame.setVisible(true);
		frame.setSize(600, 500);
		frame.setLocation(100, 100);
	}

	private void displaySmoothGraph() {
		graph = ChartFactory.createXYLineChart("Smooth Function", "X", "Y", generateSmoothData(),
				PlotOrientation.VERTICAL, true, false, false);

		ChartFrame frame = new ChartFrame("Smooth Function", graph);

		frame.setVisible(true);
		frame.setSize(600, 500);
		frame.setLocation(200, 200);
	}

	private XYDataset generateData() {
		XYSeries xy = new XYSeries("series");

		double y;

		for (int i = -range; i <= range; i++) {

			y = (i - h) * (i - h) + k;
			xy.add(i, y);

		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(xy);

		return dataset;
	}

	private XYDataset generateSaltedData(double saltRange) {
		XYSeries xy = new XYSeries("series");

		double y;

		int c = 1;

		for (int i = -range; i <= range; i++) {

			y = (i - h) * (i - h) + k;

			if (c % 2 == 0)
				y -= saltRange;
			else
				y += saltRange;

			xy.add(i, y);
			saltedVals.add(y);

			c++;

		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(xy);

		return dataset;
	}

	private XYDataset generateSmoothData() {
		XYSeries xy = new XYSeries("series");

		DescriptiveStatistics d = new DescriptiveStatistics();
		d.setWindowSize(2);

		for (int i = -range; i <= range; i++) {
			d.addValue(saltedVals.remove(0));
			xy.add(i, d.getMean());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(xy);

		return dataset;
	}

}
