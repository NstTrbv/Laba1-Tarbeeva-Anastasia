public class ComplexNumber {
    private double re;
    private double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public ComplexNumber add(ComplexNumber b) {
        return new ComplexNumber(re + b.re, im + b.im);
    }

    public ComplexNumber subtract(ComplexNumber b) {
        return new ComplexNumber(re - b.re, im - b.im);
    }

    public ComplexNumber multiply(ComplexNumber b) {
        double real = re * b.re - im * b.im;
        double imag = re * b.im + im * b.re;

        return new ComplexNumber(real, imag);
    }

    public ComplexNumber divide(ComplexNumber b) {
        double d = b.re * b.re + b.im * b.im;

        if (d == 0) {
            throw new ArithmeticException("Деление на ноль");
        }

        double real = (re * b.re + im * b.im) / d;
        double imag = (im * b.re - re * b.im) / d;

        return new ComplexNumber(real, imag);
    }

    public String toString() {
        if (Math.abs(im) < 0.000000001) {
            return String.valueOf(re);
        }

        if (Math.abs(re) < 0.000000001) {
            return im + "i";
        }

        if (im < 0) {
            return re + " - " + (-im) + "i";
        }

        return re + " + " + im + "i";
    }
}