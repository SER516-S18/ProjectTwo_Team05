/**
 * @SER516 Project2_Team05
 */

package Client;

import java.awt.Color;
import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * PlotGraph class plots the graph for the number of channels
 * selected. This would specify colors being used for the graph.
 **/
public class PlotGraph extends ApplicationFrame {
	XYSeries plotValues[];
	Color colorlist[] = new Color[] { Color.RED, Color.GREEN, Color.YELLOW, 
			Color.BLACK, Color.PINK };
	
	public PlotGraph(String title) {
		super(title);
	}
	
	/**
	 * This method is used to plot the graph orientation add colors. 
	 * 
	 * @param numberOfChannels, inputValues[]
	 * @return chartPanel
	 **/
	public ChartPanel PlotGraphMethod(int numberOfChannels, int inputValues[]) {
		JFreeChart xyLineChart = ChartFactory.createXYLineChart("", "", "",
				createDataset(numberOfChannels, inputValues),
		PlotOrientation.VERTICAL, true, false, false);
		xyLineChart.removeLegend();
		ChartPanel chartPanel = new ChartPanel(xyLineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(520, 520));
		final XYPlot plot = xyLineChart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		for (int i = 0; i < numberOfChannels; i++) {
			renderer.setSeriesPaint(i, colorlist[i]);
			renderer.setSeriesStroke(i, new BasicStroke(1.0f));
		}
		plot.setRenderer(renderer);
		setContentPane(chartPanel);
		return chartPanel;
	}

   /**
    * This method creates the data set to plot the graph for the number of 
    * channels selected.
    * 
    * @param numberOfChannels inputValues[]
    * @return dataset
    */
	private XYDataset createDataset(int numberOfChannels, int inputValues[]) {
		final XYSeriesCollection dataset = new XYSeriesCollection();
		plotValues = new XYSeries[numberOfChannels];
		for (int i = 0; i < numberOfChannels; i++) {
			plotValues[i] = new XYSeries("Channel " + (i + 1));
		}
		for (int i = 0; i < (inputValues.length - 1); i++) {

			int x = i % numberOfChannels;
			plotValues[x].add(inputValues[i], inputValues[i + 1]);
		}
		for (int i = 0; i < numberOfChannels; i++) {
			dataset.addSeries(plotValues[i]);
		}
		return dataset;
	}

}
