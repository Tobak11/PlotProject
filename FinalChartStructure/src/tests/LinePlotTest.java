/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import plot.data.DataSeries;
import plot.data.LineData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import plot.data.AxisData;
import plot.data.TitleData;
import plot.utils.SignalUtil;
import plot.view.Chart;

/**
 *
 * @author BRAIN-1
 */
public class LinePlotTest extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // generate input data
        double timeWindow = 2.0;
        double samplingFreq = 64;

        float[] cosSignal = SignalUtil.generateCosine(1.0, 2.0, samplingFreq, timeWindow);
        float[] sinSignal = SignalUtil.generateSine(1.0, 2.0, samplingFreq, timeWindow);

        // generate data series from input data
        DataSeries cosSeries = new DataSeries("Cos(x)", 
                new LineData(Color.RED, 1, LineData.Type.SOLID), 
                samplingFreq, cosSignal, timeWindow, 0.0);
        DataSeries sinSeries = new DataSeries("Sin(x)", 
                new LineData(Color.BLUE, 1, LineData.Type.DASH), 
                samplingFreq, sinSignal, timeWindow, 0.0);

        ArrayList<DataSeries> ds = new ArrayList<>(Arrays.asList(cosSeries, sinSeries));

        Chart chart = new Chart()
                .series(ds)
                .title(new TitleData("Trigonometric functions", Font.font(24)))
                .xAxis(new AxisData(new TitleData("Time", Font.font(16)), null, 1, 0.25, new LineData(Color.BLACK, 1.0, LineData.Type.SOLID)))
                .yAxis(new AxisData(new TitleData("Values", Font.font(16)), null, 1, 0.10, new LineData(Color.BLACK, 1.0, LineData.Type.SOLID)))
                .legend()
            .build();
        
        Scene scene = new Scene(chart, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
