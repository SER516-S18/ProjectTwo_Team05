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


	public ChartPanel PlotGraphMethod() {
		JFreeChart xyLineChart = ChartFactory.createXYLineChart("", "", "", 
				createDataset(), PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(xyLineChart);
		chartPanel.setPreferredSize( new java.awt.Dimension( 520 , 520 ) );
		final XYPlot plot = xyLineChart.getXYPlot();
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		//XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer( );
		//TODO Modify setSeriesPaint to incorporate different colors for different channel plots
	    renderer.setSeriesPaint( 0 , Color.RED );
	    renderer.setSeriesPaint( 1 , Color.GREEN );
	      renderer.setSeriesPaint( 2 , Color.YELLOW );
	      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
	      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
	    renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
	    plot.setRenderer( renderer ); 
	    renderer.setSeriesPaint( 1 , Color.BLUE);
	    renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
	   // plot.setRenderer( renderer2 ); 
	    setContentPane( chartPanel );
	    return chartPanel;
	}
	
	
	//Generates the dataset for plotting the graphs
	private XYDataset createDataset( ) {
		//int i;
		//TODO Create different XYSeries objects for different channels input data and modify channel numbers
	    final XYSeries plotValues = new XYSeries( "Channel1" ); 
	    plotValues.add(1,1);
	    plotValues.add(2,2);
	    plotValues.add(3,3);
	    final XYSeries plotValues2 = new XYSeries( "Channel2" );
	    plotValues2.add(1,3);
	    plotValues2.add(2,4);
	    plotValues2.add(5,5);
	   // for(i=0; i < (inputValues.length); i++ )
	    //{
	    	//	plotValues.add(inputValues[i], inputValues[i+1]);
	    		//plotValues2.add(inputValues[i+1], inputValues[i+2]);
	    //}
	      
	    final XYSeriesCollection dataset = new XYSeriesCollection( );          
	    dataset.addSeries( plotValues );   
	    dataset.addSeries( plotValues2 ); 
	    return dataset;
	}

	
}

