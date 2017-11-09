package com.oop.act;

import com.oop.shapes.Shape;

import java.util.ArrayList;

public class Scientist {

    private static Scientist instance;

    private Scientist() {
    }

    public static Scientist getInstance() {
        if (instance == null) {
            instance = new Scientist();
        }
        return instance;
    }

    public void calculateParameters(ArrayList<Shape> shapes) {

        for (Shape shape : shapes) {
            System.out.println(shape);
            System.out.println("Perimeter=" + shape.getPerimeter());
        }

    }

}
