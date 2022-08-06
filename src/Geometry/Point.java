package Geometry;

/**
 * Represents a point in 2d space.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x double - x position.
     * @param y double - y position.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter x position.
     * @return double.
     */
    public double getX() {
        return x;
    }
    /**
     * getter y position.
     * @return double.
     */
    public double getY() {
        return y;
    }

    /**
     * setter x position.
     * @param x double - new x position.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * setter y position.
     * @param y double - new x position.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * calculate distance from other point according to formula: distance = sqrt((x1-x2)^2 + (y1-y2)^2).
     * @param other Point.
     * @return double - distance between the two points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2.0) + Math.pow(this.y - other.y, 2.0));
    }

    /**
     * check if point equals another point.
     * @param other Point.
     * @return boolean - true if equals, else false.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "(x: " + this.x + ", y: " + this.y + ")";
    }
}
