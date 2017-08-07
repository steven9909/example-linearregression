import java.util.HashMap;
import java.util.Map;

/**
 * Created by steven on 8/6/2017.
 */
public class LinearRegression {

    private double thetaZero;
    private double thetaOne;

    LinearRegression(){

        thetaZero = 0;
        thetaOne = 0;

    }

    public double getThetaZero() {
        return thetaZero;
    }

    public void setThetaZero(double thetaZero) {
        this.thetaZero = thetaZero;
    }

    public double getThetaOne() {
        return thetaOne;
    }

    public void setThetaOne(double thetaOne) {
        this.thetaOne = thetaOne;
    }

    public double h(double x){
        return thetaZero + thetaOne*x;
    }



}
