package com.oop.coordinates;

public class Radius {

    private int r;

    public Radius(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "Radius{" +
                "r=" + r +
                '}';
    }

}
