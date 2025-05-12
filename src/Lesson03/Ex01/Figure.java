package Lesson03.Ex01;

public sealed abstract class Figure permits Circle, Rectangle, Triangle{
    private final double x,y;

    public Figure(double y, double x) {
        this.y = y;
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public abstract double area();

    @Override
    public String
    toString() {
        return String.format("%s (x=%s, y=%s) Area: %.2f", getClass().getSimpleName(), x, y, area());
    }
}
