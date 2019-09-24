/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot.utils;

import java.util.ArrayList;

/**
 *
 * @author tobak11
 */
public class VisualUtility {
    public static ArrayList<Double> xTickCoordinateGenerator(double interval, double stepX, int length, double xWidth, double yHeight){
        ArrayList<Double> coordinates = new ArrayList<>();
        
        int numOfTicks = (int)Math.floor(interval/stepX);
        double pixIncrement = xWidth/numOfTicks;
        
        for(int i=0;i<numOfTicks+1;i++){
            coordinates.add(i*pixIncrement+48);
            coordinates.add(0.0);
            coordinates.add(i*pixIncrement+48);
            coordinates.add((-1)*(double)length);
        }
        
        return coordinates;
    }
    
    public static ArrayList<Double> yTickCoordinateGenerator(double interval, double stepY, int length, double xWidth, double yHeight){
        ArrayList<Double> coordinates = new ArrayList<>();
        
        int numOfTicks = (int)Math.floor(interval/stepY);
        double pixIncrement = yHeight/numOfTicks;
        
        for(int i=0;i<numOfTicks+1;i++){
            coordinates.add((double)48);
            coordinates.add(i*pixIncrement);
            coordinates.add(48+(double)length);
            coordinates.add(i*pixIncrement);
        }
        
        return coordinates;
    }
}