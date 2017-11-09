package com.oop.act;

import com.oop.ColorType;
import com.oop.ToRefactoring;
import com.oop.coordinates.Point;
import com.oop.coordinates.Radius;
import com.oop.exceptions.EndOfLineParsingException;
import com.oop.exceptions.RadiusNegativeException;
import com.oop.exceptions.ShapeNotFoundException;
import com.oop.shapes.*;
import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Secretary {

    private static Secretary instance;

    private static Logger LOGGER = Logger.getLogger(Secretary.class.getName());

    private static final String COLOR_AND_POINTS_SEPARATOR = "\\(";
    private static final String POINTS_SEPARATOR = ";";
    private static final String COORDINATE_SEPARATOR = ",";
    private static final String END_OF_LINE_SEPARATOR = ")";

    private Secretary() {
    }

    public static Secretary getInstance() {
        if (instance == null) {
            instance = new Secretary();
        }
        return instance;
    }

    public ArrayList<Shape> getListOfShapes() throws IOException {

        ArrayList<Shape> shapes = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(new File(getClass().getResource("/data").getFile())))) {

            String fullLine;
            while ((fullLine = in.readLine()) != null) {

                String line = fullLine;

                ColorType color;
                ArrayList<Point> points;
                Radius radius = null;

                try {
                    line = initialParseOperator(fullLine);
                } catch (EndOfLineParsingException e) {
                    LOGGER.warning("Invalid line format (EndOfLineParsingException): " + fullLine);
                    continue;
                }

                String[] parts = line.split(COLOR_AND_POINTS_SEPARATOR);
                if (line.startsWith(COLOR_AND_POINTS_SEPARATOR)) {

                    color = parsColorType("");
                    points = parsPoints(parts[0]);
                    if (points.size() == 1) {
                        try {
                            radius = parsRadius(parts[0]);
                        } catch (RadiusNegativeException e) {
                            LOGGER.warning("Invalid line format (RadiusNegativeException): " + fullLine);
                        }
                    }

                } else {

                    color = parsColorType(line.startsWith(COLOR_AND_POINTS_SEPARATOR) ? "" : parts[0]);
                    points = parsPoints(parts[1]);
                    if (points.size() == 1) {
                        try {
                            radius = parsRadius(parts[1]);
                        } catch (RadiusNegativeException e) {
                            LOGGER.warning("Invalid line format (RadiusNegativeException): " + fullLine);
                        }
                    }

                }

                try {
                    Shape shape = getShape(color, points, radius);
                    if (shape != null) {
                        shapes.add(shape);
                    }
                } catch (ShapeNotFoundException e) {
                    LOGGER.warning("Invalid line format (ShapeNotFoundException): " + fullLine);
                }

            }

        }

        return shapes;

    }

    private String initialParseOperator(@NotNull String line) throws EndOfLineParsingException {
        if (line.endsWith(END_OF_LINE_SEPARATOR)) {
            return line.substring(0, line.length() - 1);
        } else {
            throw new EndOfLineParsingException();
        }
    }

    private ColorType parsColorType(String line) {

        try {
            return ColorType.valueOf(line.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ColorType.WHITE;
        }

    }

    private ArrayList<Point> parsPoints(String line) {

        ArrayList<Point> points = new ArrayList<Point>();

        String[] parts = line.split(POINTS_SEPARATOR);
        for (String point : parts) {

            String[] coordinates = point.split(COORDINATE_SEPARATOR);
            if (coordinates.length == 2) {
                points.add(new Point(Integer.valueOf(coordinates[0].trim()), Integer.valueOf(coordinates[1].trim())));
            }

        }

        return points;

    }

    private Radius parsRadius(String line) throws RadiusNegativeException {

        Radius radius = null;

        String[] points = line.split(POINTS_SEPARATOR);
        for (String point : points) {

            String[] coordinates = point.split(COORDINATE_SEPARATOR);
            if (coordinates.length > 1) {
                continue;
            }

            int r = Integer.valueOf(coordinates[0].trim());

            if (r <= 0) {
                throw new RadiusNegativeException();
            }

            radius = new Radius(r);

        }

        return radius;

    }

    @ToRefactoring
    private Shape getShape(ColorType color, ArrayList<Point> points, Radius radius) throws ShapeNotFoundException {

        if (points.size() == 4) {
            return new Rectangle(points.get(0), points.get(1), points.get(2), points.get(3), color);
        }

        if (points.size() == 3) {
            return new Triangle(points.get(0), points.get(1), points.get(2), color);
        }

        if (points.size() == 1 && radius != null) {
            return new Circle(points.get(0), radius, color);
        }

        throw new ShapeNotFoundException();

    }

}
