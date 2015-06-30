package mandelbrotSet;

public class Complex {
    double coefficientA;
    double coefficientB;
    public Complex(double a, double b){
        coefficientA = a;
        coefficientB = b;   
    }
    
    public void display() {
        if(coefficientB >= 0) {
            System.out.println(coefficientA + " + " + coefficientB+ "i");
        }
        else{
            System.out.println(coefficientA + " - " + Math.abs(coefficientB) + "i");
        }              
    }
    
    public Complex add(Complex Complex) {
        double newCoefficientA= coefficientA + Complex.coefficientA;
        double newCoefficientB= coefficientB+ Complex.coefficientB;
        Complex add = new Complex(newCoefficientA, newCoefficientB);
        return add;
    }
    
    public void multiplyByScalar(double s) {
        double newCoefficientA= coefficientA*s;
        double newCoefficientB= coefficientB*s;
        Complex scalar = new Complex(newCoefficientA, newCoefficientB);
        scalar.display();
    }
    
    public Complex multiplyByComplex(Complex complex2) {
        double newCoefficientA = coefficientA*complex2.coefficientA + coefficientB*complex2.coefficientB*-1;
        double newCoefficientB = coefficientA*complex2.coefficientB + coefficientB*complex2.coefficientA;
        return new Complex(newCoefficientA, newCoefficientB);
    }
    
    public double absolute() {
         return Math.sqrt(Math.pow(coefficientA, 2) + Math.pow(coefficientB, 2));
    }
    
    public double absoluteSquared(){
        return (Math.pow(coefficientA, 2) + Math.pow(coefficientB, 2));
    }
    
    public Complex conj() {
        double newcoefficientB=coefficientB*-1;
        return new Complex(coefficientA, newcoefficientB);
    }
    
    public Complex divideByComplex(Complex complex2) {        
        Complex top = multiplyByComplex(complex2.conj());
        double bottom = Math.pow(complex2.coefficientA,2) + Math.pow(complex2.coefficientB,2);
        return new Complex(top.coefficientA/bottom,top.coefficientB/bottom);
    }
    
    public Complex exponent(int exp) {
        Complex sameNum = new Complex(coefficientA, coefficientB);
        for(int i=2; i<=exp; i++) {
          sameNum = multiplyByComplex(sameNum); 
        }
        return sameNum;       
    }
}