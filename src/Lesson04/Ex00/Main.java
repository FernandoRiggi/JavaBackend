package Lesson04.Ex00;

import Lesson03.Ex01.*;
public class Main {
    public static void main(String[] args) {
        Figure[] figures = new Figure[150];
        double total =0;
        int j = 1;
        for(int i=0; i<150; i+=3){
            figures[i] = new Circle(j,j,j);
            figures[i+1] = new Rectangle(j,j,j,j);
            figures[i+2] = new Triangle(j,j,j,j,j);
            j++;
        }
        for(Figure figure : figures){
            total += figure.area();
        }
        System.out.println(STR."Total area: \{total}");
    }
}
