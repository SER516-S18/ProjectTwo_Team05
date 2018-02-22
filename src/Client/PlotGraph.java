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
	
	
	public PlotGraph(String title) {
		super(title);
	}


	public ChartPanel PlotGraphMethod(int inputValues[]) {
		JFreeChart xyLineChart = ChartFactory.createXYLineChart("", "", "", 
				createDataset(inputValues), PlotOrientation.VERTICAL, false, true, false);
		ChartPanel chartPanel = new ChartPanel(xyLineChart);
		chartPanel.setPreferredSize( new java.awt.Dimension( 520 , 520 ) );
		final XYPlot plot = xyLineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		//TODO Modify setSeriesPaint to incorporate different colors for different channel plots
	    renderer.setSeriesPaint( 0 , Color.RED );
	    renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
	    plot.setRenderer( renderer ); 
	    setContentPane( chartPanel );
	    return chartPanel;
	}
	
	
	//Generates the dataset for plotting the graphs
	private XYDataset createDataset(int inputValues[] ) {
		int i;
		//TODO Create different XYSeries objects for different channels input data and modify channel numbers
	    final XYSeries plotValues = new XYSeries( "Channel1" ); 
	    for(i=0; i < (inputValues.length-1); i++ )
	    {
	    		plotValues.add(inputValues[i], inputValues[i+1]);
	    }
	      
	    final XYSeriesCollection dataset = new XYSeriesCollection( );          
	    dataset.addSeries( plotValues );          
	    return dataset;
	}

	
}

