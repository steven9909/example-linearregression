import javafx.scene.chart.ScatterChart;

/**
 * This algorithm only works for solving linear regression problem with two attributes (ThetaOne and ThetaZero)
 * Created by steven on 8/6/2017.
 */
public class GradientDescent {

    private int size; //Size of the training set

    private LinearRegression reg;

    private double rate; //Learning Rate of the Algorithm

    private double xData[]; //X Data of the training set
    private double yData[]; //Y Data of the training set

    GradientDescent(int m, double learning, double[] xData, double[] yData){
        size = m;
        reg = new LinearRegression();
        rate = learning;

        this.xData = new double[m];
        this.yData = new double[m];

        this.xData = xData;
        this.yData = yData;

    }

    /**
     * This method solves for ThetaOne and ThetaZero by applying gradient descent algorithm. One call to this method = one iteration.
     * This method simultaneously updates the values of ThetaOne and ThetaZero.
     * @return
     */
    public void solveTheta(){

        double thetaZero = reg.getThetaZero() - rate * returnSum(0)/size;

        double thetaOne = reg.getThetaOne() - rate * returnSum(1)/size;

        reg.setThetaOne(thetaOne);
        reg.setThetaZero(thetaZero);

    }

    /**
     * Returns ThetaZero and ThetaOne
     * @return
     */
    public double[] returnAnswer(){
        double[] answer = {reg.getThetaZero(), reg.getThetaOne()};

        return answer;
    }


    public double returnSquaredError(){
        return Math.pow(returnSum(0),2);
    }

    /**
     * Num denotes the subscript of theta
     * @param num
     * @return
     */
    public double returnSum(int num){

        double sum = 0;

        if(num == 0){
            for(int i = 0; i < size; i++){

                sum += (reg.h(xData[i]) - yData[i]);

            }
        }
        else if(num == 1){
            for(int i = 0; i < size; i++){

                sum += ((reg.h(xData[i]) - yData[i])*xData[i]);

            }
        }

        return sum;
    }

}
