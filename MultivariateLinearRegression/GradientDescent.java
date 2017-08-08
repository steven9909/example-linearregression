
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;


/**
 * This algorithm works for any multivariate linear regression problems.
 * Created by steven on 8/6/2017.
 */
public class GradientDescent {

    private int size; //size of the training set

    private int featureSize; //size of the features

    private LinearRegression reg; //linear regression object

    private double rate; //learning rate of the algorithm

    private RealMatrix input; //input

    private double[] output; //output

    private double[] sum; //array of doubles containing the numbers necessary for scaling
    private double[] min; //array of doubles containing the numbers necessary for scaling
    private double[] max; //array of doubles containing the numbers necessary for scaling

    RealMatrix outputMatrix;

    GradientDescent(int n, int m, double learning, double[][] data, double[] y){
        size = m;
        featureSize = n;
        reg = new LinearRegression(n+1);
        rate = learning;

        output = y;

        scale(data);

        input = MatrixUtils.createRealMatrix(m,n+1);

        outputMatrix = MatrixUtils.createColumnRealMatrix(y);

        for(int i = 0; i < m; i++){

            for(int j = 0; j < n+1; j++){

                if(j == 0){
                    input.setEntry(i,j,1);
                }
                else{
                    input.setEntry(i,j,data[i][j-1]);
                }

            }

        }

    }

    public double test(double[] inputX){

        double[] input = new double[inputX.length+1];

        input[0] = 1;

        for(int i = 1; i < input.length; i++){

            input[i] = (inputX[i-1] - sum[i-1])/(max[i-1]-min[i-1]);

        }

        RealMatrix temp = MatrixUtils.createColumnRealMatrix(input);

        return reg.h(temp);

    }

    public RealMatrix solveWithNormalEquation(){

        RealMatrix transposed = input.transpose();

        transposed = transposed.multiply(input);

        transposed = MatrixUtils.inverse(transposed);

        transposed = transposed.multiply(input.transpose());

        transposed = transposed.multiply(outputMatrix);

        return transposed;

    }

    public void scale(double[][] data){

        sum = new double[data[0].length];
        min = new double[data[0].length];
        max = new double[data[0].length];

        for(int i = 0; i < data[0].length; i++){

            min[i] = Double.MAX_VALUE;
            max[i] = Double.MIN_VALUE;

        }

        for(int i = 0; i < data[0].length; i++){

            for(int j = 0; j < data.length; j++){

                sum[i] += data[j][i];
                min[i] = Math.min(min[i],data[j][i]);
                max[i] = Math.max(max[i],data[j][i]);

            }

            sum[i] = sum[i]/data.length;

        }


        for(int i = 0; i < data[0].length; i++){

            for(int j = 0; j < data.length; j++){

                data[j][i] = (data[j][i] - sum[i])/(max[i]-min[i]);

            }

        }

    }

    public double getAnswer(int feature){

        return reg.h((input.getRowMatrix(feature)).transpose());

    }


    public void iterate(){

        double[] temp = new double[featureSize+1];

        for(int i = 0; i < featureSize+1; i++){

            temp[i]= reg.getEntry(0,i)-(rate*sum(i)/size);

        }

        reg.setTheta(temp);

    }

    public double costFunction(){

        return squaredError()/(2*size);

    }

    public double squaredError(){

        double sum = 0;

        for(int i = 0; i < size; i++){

            sum += (getAnswer(i) - output[i]);

        }

        return sum*sum;

    }

    public RealMatrix getTheta(){

        return reg.getTheta();

    }

    public double sum(int j){

        double sum = 0;

        for(int i = 0; i < size; i++){

            sum += (getAnswer(i) - output[i]) * input.getEntry(i,j);

        }

        return sum;

    }





}
