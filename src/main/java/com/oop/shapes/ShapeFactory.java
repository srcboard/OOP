package com.oop.shapes;

import com.oop.ShapeType;

public class ShapeFactory {

    public Shape getShape(ShapeType shapeType) {

        if (shapeType == ShapeType.CIRCLE) {
            return new Circle();
        }

        return null;
    }

}
