package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle in 2d space.
 */
public class Rectangle {

    private double width;
    private double height;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    /**
     * Constructor - Create a new rectangle with location and width/height.
     * @param upperLeft Point - top left point of rectangle.
     * @param width double - width of rectangle.
     * @param height double - height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        this.width = width;
        this.height = height;
        this.topLeft = upperLeft;
        this.topRight = new Point(x + width, y);
        this.bottomLeft = new Point(x, y + height);
        this.bottomRight = new Point(x + width, y + height);
        this.top = new Line(topLeft, topRight);
        this.bottom = new Line(bottomLeft, bottomRight);
        this.left = new Line(topLeft, bottomLeft);
        this.right = new Line(topRight, bottomRight);

    }

    /**
     * getter for left side.
     * @return Line.
     */
    public Line getLeft() {
        return left;
    }
    /**
     * getter for right side.
     * @return Line.
     */
    public Line getRight() {
        return right;
    }
    /**
     * getter for bottom side.
     * @return Line.
     */
    public Line getBottom() {
        return this.bottom;
    }
    /**
     * getter for top side.
     * @return Line.
     */
    public Line getTop() {
        return top;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * compute all collision points with a line.
     * @param line Line - line to compute intersection points with.
     * @return ArrayList[Point] - list of intersection point.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> result = new ArrayList<>();
        Line[] lines = {left, right, top, bottom};
        for (Line l : lines) {
            Point intersection = line.intersectionWith(l);
            if (intersection != null) {
                result.add(intersection);
            }
        }
        return result;
    }

    /**
     * getter for width.
     * @return double.
     */
    public double getWidth() {
        return width;
    }
    /**
     * getter for height.
     * @return double.
     */
    public double getHeight() {
        return height;
    }

    // Returns the upper-left point of the rectangle.

    /**
     * getter for top left point.
     * @return Point.
     */
    public Point getTopLeft() {
        return topLeft;
    }
    /**
     * getter for bottom right point.
     * @return Point.
     */
    public Point getBottomRight() {
        return bottomRight;
    }
    /**
     * getter for bottom left point.
     * @return Point.
     */
    public Point getBottomLeft() {
        return bottomLeft;
    }
    /**
     * getter for top right point.
     * @return Point.
     */
    public Point getTopRight() {
        return topRight;
    }
}
