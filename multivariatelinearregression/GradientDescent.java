
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * This algorithm works for any multivariate linear regression problems.
 * Created by steven on 8/6/2017.
 */
public class GradientDescent {

    private int size;

    private int featureSize;

    private LinearRegression reg;

    private double rate;

    private RealMatrix input;

    private double[] output;

    GradientDescent(int n, int m, double learning, double[][] data, double[] y){
        size = m;
        featureSize = n;
        reg = new LinearRegression(n+1);
        rate = learning;

        output = y;

        input = MatrixUtils.createRealMatrix(m,n+1);

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
