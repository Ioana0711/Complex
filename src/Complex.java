public class Complex {
    private double re, im;
    String operator;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getReal() {
        return re;
    }

    public double getImaginary() {
        return im;
    }

    public Complex addition(Complex nr1){
        if (nr1 != null) {
            this.re = this.re + nr1.re;
            this.im = this.im + nr1.im;
        }
        return new Complex(re, im);
    }

    public Complex subtraction(Complex nr1){
        this.re = this.re - nr1.re;
        this.im = this.im - nr1.im;
        return new Complex(re, im);
    }

    public Complex multiplication(Complex nr1){
        this.re = this.re * nr1.re - this.im * nr1.im;
        this.im = this.re * nr1.im + this.im * nr1.re;
        return new Complex(re, im);
    }

    public Complex division(Complex nr1) throws MyExceptions {
        if((this.re == 0 && nr1.re == 0) || (nr1.re == 0 && nr1.im == 0)){
            throw new MyExceptions("Division by zero");
        }
        this.re = (this.re * nr1.re + this.im * nr1.im) / (nr1.re * nr1.re + nr1.im * nr1.im);
        this.im = (this.im * nr1.re - this.re * nr1.im) / (nr1.re * nr1.re + nr1.im * nr1.im);
        return new Complex(re, im);
    }

    public Complex conjugate(Complex nr1){
        return new Complex(re, -im);
    }

    @Override
    public String toString() {
        if(this.re == 0){
            if(this.im == 0){
                return "0";
            }
            else if(this.im>0){
                return (this.im + "*i");
            }
            else{
                return ("(" + this.im + ")" + "*i");
            }
        }
        else{
            if(this.im==0){
                return String.valueOf(this.re);
            }
            else if(this.im>0){
                return (String.valueOf(this.re) + "+" + this.im +("*i"));
            }
            else{
                return (String.valueOf(this.re) + "+" + "(" + this.im + ")" + "*i");
            }
        }
    }

}
