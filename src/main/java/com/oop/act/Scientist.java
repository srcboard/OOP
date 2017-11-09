package com.oop.act;

import com.oop.shapes.Shape;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

        shapes.forEach(e -> {
            System.out.println(e);
            System.out.println("Perimeter=" + e.getPerimeter());
        });

        System.out.println("Total=" + shapes
                .stream()
                .collect(Collectors.summarizingDouble(Shape::getPerimeter))
                .getSum());
        
    }

}
