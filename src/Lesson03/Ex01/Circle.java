package Lesson03.Ex01;

public final class Circle extends Figure{

    private double radius;

    public Circle(double y, double x, double radius) {
        super(y, x);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRadius: " + radius;
    }
}
