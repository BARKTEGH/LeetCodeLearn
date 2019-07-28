package offer;

import org.junit.Test;

public class Power {
    public double Power(double base, int exponent) {
        return Math.pow(base,exponent);
  }

  public double power(double base, int exponent){
        if(exponent==0){
            return 1;
        }
        if(exponent==1){
            return base;
        }
        boolean isPlus = true;
        if(exponent<0){
            isPlus =false;
            exponent = -exponent;
        }

        double pow = power(base*base, exponent/2);
        if(exponent%2!=0){
            pow = pow*base;
        }

        return isPlus?pow:1/pow;

  }

  @Test
  public void test(){

        System.out.println(Power(6.2,2));
      System.out.println(power(2,10));
  }
}

