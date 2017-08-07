import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by steven on 8/6/2017.
 */
public class LinearRegression {

    private RealMatrix theta;

    LinearRegression(int size){

        theta = MatrixUtils.createRowRealMatrix(new double[size]);

    }


    public double h(RealMatrix matrix){

        RealMatrix p = theta.multiply(matrix);

        return p.getEntry(0,0);

    }

    public RealMatrix getTheta(){
        return theta;
    }

    public double getEntry(int i, int j){
        return theta.getEntry(i,j);
    }


    public void setTheta(double[] temp){
        theta = MatrixUtils.createRowRealMatrix(temp);
    }


}
