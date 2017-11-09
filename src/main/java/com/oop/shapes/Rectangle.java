package com.oop.shapes;

import com.oop.ColorType;
import com.oop.coordinates.Point;

public class Rectangle implements Shape {


    private Point p1, p2, p3, p4;
    private ColorType color;

    public Rectangle(Point p1, Point p2, Point p3, Point p4, ColorType color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.color = color;
    }

    public double getPerimeter() {

        int x1 = p1.getX();
        int x2 = p2.getX();
        int x3 = p3.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        int y3 = p3.getY();

        double width = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double length = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));

        return 2 * (width + length);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", p4=" + p4 +
                ", color=" + color +
                '}';
    }

}
