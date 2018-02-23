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
	
	XYSeries[] plotValues;
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
		//XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer( );
		//TODO Modify setSeriesPaint to incorporate different colors for different channel plots
	    renderer.setSeriesPaint( 0 , Color.RED );
	    renderer.setSeriesPaint( 1 , Color.GREEN );
	      renderer.setSeriesPaint( 2 , Color.YELLOW );
	      renderer.setSeriesPaint( 1 , Color.BLACK );
	      renderer.setSeriesPaint( 2 , Color.PINK);
	      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
	      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
	    renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
	    renderer.setSeriesStroke( 1 , new BasicStroke( 5.0f ) );
	    renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
	    plot.setRenderer( renderer ); 
	    //renderer.setSeriesPaint( 1 , Color.BLUE);
	    //renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
	   // plot.setRenderer( renderer2 ); 
	    setContentPane( chartPanel );
	    return chartPanel;
	}
	
	
	//Generates the dataset for plotting the graphs
	private XYDataset createDataset(int selectedValue, int inputValues[] ) {
		int i;
		final XYSeriesCollection dataset = new XYSeriesCollection( ); 
		plotValues = new XYSeries[selectedValue];//TODO Create different XYSeries objects for different channels input data and modify channel numbers
	    for(i=0;i<selectedValue;i++)
	    {
	    		plotValues[i] = new XYSeries( "Channel " +i ); 
	    }
	    	for(i=0; i < (inputValues.length-1); i++ )
	    {
	    		int x = i%selectedValue;
	    		plotValues[x].add(inputValues[i], inputValues[i+1]);
	    		
	    		//plotValues2.add(inputValues[i+1], inputValues[i+2]);
	    }
	    	for(i=0;i<selectedValue;i++)
	  	{
	    		dataset.addSeries( plotValues[i]);
	  	}
	            
	       
	   // dataset.addSeries( plotValues2 ); 
	 
	    return dataset;
	}

	
}

