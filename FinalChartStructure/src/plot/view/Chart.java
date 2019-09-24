/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot.view;

import plot.data.AxisData;
import plot.data.DataSeries;
import plot.data.LabelData;
import plot.data.LineData;
import plot.data.TitleData;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import plot.view.Axis.AxisType;

/**
 *
 * @author tobak11
 */
public class Chart extends BorderPane {

    //Containers
    private FigureContainer plotContainer;
    private PlotGroup plotGroup;
    
    //Possible nodes
    private Axis xAxis;
    private Axis yAxis;
    private Series series;
    private Legend legend;
    
    //-----Node DATA-----
    //xAxis Data
    private AxisData xAxisData;
    private AxisType xAxisType;
    private double xWidth;
    
    //yAxis Data
    private AxisData yAxisData;
    private AxisType yAxisType;
    private double yHeight;
    
    //Series data
    private ArrayList<DataSeries> dsList;
    
    //Title data
    private TitleData titleData;
    
    //Legend data
    private boolean legendOn;
    
    
    public Chart() {
        plotContainer = new FigureContainer();       
        setCenter(plotContainer);   

        plotGroup = new PlotGroup();
        plotContainer.addPlot(plotGroup);
        
        legendOn = false;
    }

    public Chart xAxis(){
        xAxisData = new AxisData(
                new TitleData("xAxis"), new LabelData(), 
                1, 0.5, new LineData(Color.BLACK, 1, LineData.Type.SOLID));
        xAxisType = AxisType.HORIZONTAL;
        xWidth = 500;
        
        return this;
    }
    
    public Chart yAxis(){
        yAxisData = new AxisData(
                new TitleData("yAxis"), new LabelData(), 
                1, 0.5, new LineData(Color.BLACK, 1, LineData.Type.SOLID));
        yAxisType = AxisType.VERTICAL;
        yHeight = 300;
        
        return this;
    }
    
    
    public Chart xAxis(AxisData axisData){
        xAxisData = axisData;
        xAxisType = AxisType.HORIZONTAL;
        xWidth = 500;      
        
        return this;
    }
    
    public Chart yAxis(AxisData axisData){
        yAxisData = axisData;
        yAxisType = AxisType.VERTICAL;
        yHeight = 300;
                
        return this;
    }
    
    public Chart series(ArrayList<DataSeries> dsList){
        this.dsList=dsList;
        
        return this;
    }

    public Chart title(TitleData titleData) {
        this.titleData = titleData;

        return this;
    }
    
    public Chart legend(){
        this.legendOn = true;
        
        return this;
    }
    
    public Chart build(){
        if(dsList != null){
            series = new Series(dsList, plotGroup.widthProperty(), plotGroup.heightProperty());
            plotGroup.getChildren().add(series);
            
            if(xAxisData != null){
                xAxis = new Axis(xAxisData, xAxisType, dsList, xWidth, yHeight);
                xAxis.bind(plotGroup.widthProperty(), plotGroup.heightProperty());
                
                plotGroup.getChildren().add(xAxis);
                
                Text xAxisTitle = new Text(xAxisData.getTitle().getValue());
                xAxisTitle.setFont(xAxisData.getTitle().getFont());
                BorderPane.setAlignment(xAxisTitle, Pos.CENTER);
                
                plotContainer.setBottom(xAxisTitle);
            }
            if(yAxisData != null){
                yAxis = new Axis(yAxisData, yAxisType, dsList, xWidth, yHeight);
                yAxis.bind(plotGroup.widthProperty(), plotGroup.heightProperty());
            
                plotGroup.getChildren().add(yAxis);
                
                Text yAxisTitle = new Text(yAxisData.getTitle().getValue());
                yAxisTitle.setFont(yAxisData.getTitle().getFont());
                yAxisTitle.setRotate(-90);
                BorderPane.setAlignment(yAxisTitle, Pos.CENTER);
                
                plotContainer.setLeft(yAxisTitle);
            }
            if(titleData != null){
                Text title = new Text(titleData.getValue());
                title.setFont(titleData.getFont());
                setAlignment(title, Pos.CENTER);
                
                plotContainer.setTop(title);
            }
            if(legendOn){
                legend = new Legend(dsList);
                
                plotContainer.setRight(legend);
            }
        }else{
            throw new NullPointerException("No data provided");
        }
        
        return this;
    }
}
