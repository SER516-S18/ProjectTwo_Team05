package Client;

import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer; 

//PlotGraph class plots the graph for one channel of input
public class PlotGraph extends ApplicationFrame{
	
	XYSeries plotValues[];
	Color colorlist[] = new Color[]{Color.RED, Color.GREEN, Color.YELLOW, Color.BLACK, Color.PINK};
	int i;
	public PlotGraph(String title) {
		super(title);
	}


	public ChartPanel PlotGraphMethod(int selectedValue, int inputValues[]) {
		JFreeChart xyLineChart = ChartFactory.createXYLineChart("", "", "", 
				createDataset(selectedValue,inputValues), PlotOrientation.VERTICAL, true, false, false);
		ChartPanel chartPanel = new ChartPanel(xyLineChart);
		chartPanel.setPreferredSize( new java.awt.Dimension( 520 , 520 ) );
		final XYPlot plot = xyLineChart.getXYPlot();
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
	    renderer.setSeriesPaint( 0 , Color.RED );
	    renderer.setSeriesPaint( 1 , Color.GREEN );
	    renderer.setSeriesPaint( 2 , Color.YELLOW );
	    renderer.setSeriesPaint( 1 , Color.BLACK );
	    renderer.setSeriesPaint( 2 , Color.PINK);
	    for(i = 0; i < selectedValue; i++) 
	    {
	    	 	renderer.setSeriesPaint( i, colorlist[i] );
	    		renderer.setSeriesStroke( i, new BasicStroke( 1.0f ) );
	    }
	    plot.setRenderer( renderer ); 
	    setContentPane( chartPanel );
	    return chartPanel;
	}
	
	
	//Generates the dataset for plotting the graphs
	private XYDataset createDataset(int selectedValue, int inputValues[] ) {
		final XYSeriesCollection dataset = new XYSeriesCollection(); 
		plotValues = new XYSeries[selectedValue];
	    for(i = 0; i < selectedValue; i++)
	    {
	    		plotValues[i] = new XYSeries( "Channel " + (i + 1)); 
	    }
	    	for(i = 0; i < (inputValues.length); i++ )
	    {
	    		int x = i%selectedValue;
	    		plotValues[x].add(inputValues[i], inputValues[i]);
	    }
	    	for(i = 0; i < selectedValue; i++)
	  	{
	    		dataset.addSeries( plotValues[i]);
	  	}
	    return dataset;
	}
	
}

