package com.oop.act;

import com.oop.ColorType;
import com.oop.exceptions.GetPointFromStringException;
import com.oop.shapes.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Secretary {

    public Shape getShape(String line) {
        return new Circle();
    }

    public ArrayList<Shape> getListOfShapes() {

        ArrayList<Shape> shapes = new ArrayList<Shape>();

        try {

            BufferedReader in = new BufferedReader(new FileReader(new File(getClass().getResource("/data").getFile())));
            try {

                String line;

                while ((line = in.readLine()) != null) {

                    ColorType colorType;
                    ArrayList<Point> points = new ArrayList<Point>();
                    Radius radius = null;

                    String[] parts = line.split("\\(");

                    if (line.startsWith("(")) {
                        colorType = parsColorType("");
                        points = getPoints(parts[0]);
                        if (points.size() == 1) {
                            radius = getRadius(parts[0]);
                        }

                    } else {
                        colorType = parsColorType(line.startsWith("(") ? "" : parts[0]);
                        points = getPoints(parts[1]);
                        if (points.size() == 1) {
                            radius = getRadius(parts[1]);
                        }
                    }

                    if (points.size() == 4) {
                        shapes.add(new Rectangle(points.get(0), points.get(1), points.get(2), points.get(3)));
                    }

                }


            } catch (GetPointFromStringException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return shapes;

    }


    private ColorType parsColorType(String line) {

        try {
            return ColorType.valueOf(line.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ColorType.WHITE;
        }

    }

    private ArrayList<Point> getPoints(String line) throws Exception {

        ArrayList<Point> points = new ArrayList<Point>();

        if (line.endsWith(")")) {
            line = line.substring(0, line.length() - 1);
        } else {
//            throw new Exception();
            return points;
        }

        String[] parts = line.split(";");
        for (String point : parts) {

            String[] coordinates = point.split(",");
            if (coordinates.length != 2) {
                System.out.println(Arrays.toString(coordinates));
//                throw new GetPointFromStringException();
                continue;
            }

            points.add(new Point(Integer.valueOf(coordinates[0].trim()), Integer.valueOf(coordinates[1].trim())));

        }

        return points;

    }

    Radius getRadius(String line) throws Exception {

        Radius radius = null;

        if (line.endsWith(")")) {
            line = line.substring(0, line.length() - 1);
        } else {
//            throw new Exception();
            return radius;
        }

        String[] parts = line.split(";");
        for (String point : parts) {

            String[] coordinates = point.split(",");
            if (coordinates.length > 1) {
                continue;
            }

            int r = Integer.valueOf(coordinates[0].trim());

            if (r <= 0) {
//                throw new Exception();
                continue;
            }

            radius = new Radius(r);

        }

        if (radius == null) {
//            throw new Exception("");
            return radius;
        }

        return radius;

    }

}
