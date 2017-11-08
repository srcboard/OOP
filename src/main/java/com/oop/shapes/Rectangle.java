package com.oop.shapes;

public class Rectangle implements Shape {

    private double width;
    private double length;

    Point p1;
    Point p2;
    Point p3;
    Point p4;

    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        initial(p1, p2, p3, p4);
    }

    public double getPerimeter() {
        return 2 * (width + length);
    }

    private void initial(Point p1, Point p2, Point p3, Point p4) {

        int x1 = p1.getX();
        int x2 = p2.getX();
        int x3 = p3.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        int y3 = p3.getY();

        double width = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double length = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));

    }

}
