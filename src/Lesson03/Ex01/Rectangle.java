package Lesson03.Ex01;
public final class Rectangle extends Figure {
    private double width;
    private double length;

    public Rectangle(double x, double y, double width, double length) {
        super(x,y);
        this.width = width;
        this.length = length;
    }

    @Override
    public double area() {
        return width * length;
    }

    @Override
    public String toString() {
        return super.toString() + "\nWidth: " + width + "\nLength: " + length;
    }
}
