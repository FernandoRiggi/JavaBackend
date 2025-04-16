package Aula08.Ex01;

public final class Triangle extends Figure {
    private double a;
    private double b;
    private double c;

    public Triangle(double x, double y, double a, double b, double c) {
        super(x, y);
//        if (!isValidTriangle(a, b, c)) {
//            throw new IllegalArgumentException("Os lados fornecidos não formam um triângulo válido.");
//        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean isValidTriangle(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    @Override
    public double area() {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public String toString() {
        return super.toString() + "\nA:" + a + "\nB:" + b + "\nC:" + c;
    }
}
