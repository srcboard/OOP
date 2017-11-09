package com.oop.shapes;

import com.oop.ColorType;

public class Triangle implements Shape {

    private Point p1, p2, p3;
    private ColorType color;

    public Triangle(Point p1, Point p2, Point p3, ColorType color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
    }

    public double getPerimeter() {

        int x1 = p1.getX();
        int x2 = p2.getX();
        int x3 = p3.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        int y3 = p3.getY();

        double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
        double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));

        return (a + b + c) / 2.0;

    }


    @Override
    public String toString() {
        return "Triangle{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", color=" + color +
                '}';
    }

}
