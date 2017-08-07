import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Created by steven on 8/6/2017.
 */
public class ScatterChartExample extends Application{

    private static Scene root;

    private static Scanner s;

    private final static int SCREENSIZEX = 400;
    private final static int SCREENSIZEY = 400;

    private static double[] xData;
    private static double[] yData;

    @Override
    public void start(Stage window){

        window.setTitle("graph");
        window.setScene(root);

        window.show();

    }

    public static void init(int size, String domain, String range, int tick, boolean populate, int rate){

        int x = Integer.valueOf(domain.split("-")[0]);
        int x2 = Integer.valueOf(domain.split("-")[1]);

        int y = Integer.valueOf(range.split("-")[0]);
        int y2 = Integer.valueOf(range.split("-")[1]);

        final NumberAxis xAxis = new NumberAxis(x,x2,tick);
        final NumberAxis yAxis = new NumberAxis(y,y2,tick);

        xData = new double[size];
        yData = new double[size];

        final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis, yAxis);

        sc.setTitle("LinearRegression Example");

        sc.setPrefSize(SCREENSIZEX,SCREENSIZEY);

        XYChart.Series series = new XYChart.Series();

        if(populate) {

            for (int i = 0; i < size; i++) {

                double xValue = (Math.random() * (x2 - x) + x);
                double yValue = (Math.random() * (y2 - y) + y);
                series.getData().add(new XYChart.Data(xValue,yValue));
                xData[i] = xValue;
                yData[i] = yValue;

            }
        }
        else{

            for(int i = 0; i < size; i++){

                System.out.println("Input X and  Y. Format = x y");

                double xValue = s.nextDouble();
                double yValue = s.nextDouble();

                series.getData().add(new XYChart.Data(xValue,yValue));

                xData[i] = xValue;
                yData[i] = yValue;
            }

        }

        sc.getData().addAll(series);

        GradientDescent solve = new GradientDescent(size,rate,xData,yData);

        double[] answer = solve.returnAnswer();

        XYChart.Series series2 = new XYChart.Series();

        for(int i = 0; i < size; i++){

            double ySum = answer[0] + answer[1] * xData[i];

            series2.getData().add(new XYChart.Data(xData[i],ySum));

        }

        series2.setName("Predicted Value");
        series.setName("Input Value");

        sc.getData().addAll(series2);

        root = new Scene(sc,SCREENSIZEX,SCREENSIZEY);

    }


    public static void main(String args[]){

        s = new Scanner(System.in);

        System.out.println("Input the Range of Data. Format = y-y2");

        String range = s.next();

        System.out.println("Input the Domain of Data Format = x-x2");

        String domain = s.next();

        System.out.println("Input the Size of Data");

        int size = s.nextInt();

        System.out.println("Select Tick Unit. Suggested Value = 1");

        int tick = s.nextInt();

        System.out.println("Randomly popluate the data? Format = true or false");

        boolean populate = Boolean.valueOf(s.next());

        System.out.println("Input the Learning Rate. Suggested Value = 0.001");

        int rate = s.nextInt();

        init(size, domain, range, tick, populate, rate);

        launch(args);

    }

}
