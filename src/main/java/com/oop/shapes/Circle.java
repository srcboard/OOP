package com.oop.shapes;

import com.oop.ColorType;
import com.oop.coordinates.Point;
import com.oop.coordinates.Radius;

public class Circle implements Shape {

    private Point p;
    private Radius r;
    private ColorType color;

    public Circle(Point p, Radius r, ColorType color) {
        this.p = p;
        this.r = r;
        this.color = color;
    }

    public double getPerimeter() {
        return 2 * Math.PI * r.getR();
    }

    @Override
    public String toString() {
        return "Circle{" +
                "p=" + p +
                ", r=" + r +
                ", color=" + color +
                '}';
    }

}
