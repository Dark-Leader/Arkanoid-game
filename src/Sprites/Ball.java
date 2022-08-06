package Sprites;

import Geometry.Point;
import Geometry.Velocity;
import biuoop.DrawSurface;


/**
 * Represents a Ball in 2d space.
 */
public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;

    private Velocity velocity;

    /**
     * Constructor.
     * @param center Point - center point of ball.
     * @param r int - radius.
     * @param color Java.awt.Color - color of ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor.
     * @param x1 double - center point x.
     * @param y1 double - center point y.
     * @param r int - radius.
     * @param color Java.awt.Color - color of ball.
     */
    public Ball(double x1, double y1, int r, java.awt.Color color) {
        this(new Point(x1, y1), r, color);
    }

    /**
     * getter center x.
     * @return double.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * getter center y.
     * @return double.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * getter radius.
     * @return int.
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * getter color.
     * @return java.awt.Color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw ball on surface.
     * @param surface DrawSurface - canvas for gui.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * setter Velocity.
     * @param v Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setter Velocity.
     * @param dx double.
     * @param dy double.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getter Velocity.
     * @return Velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * move the ball to new position according to ball last position and speed with single frame in gui.
     * @param width int - width of gui.
     * @param height int - height of gui.
     */
    public void moveOneStep(int width, int height) {
        if (this.velocity == null) {
            return;
        }
        // move to new position
        Point newCenter = this.getVelocity().applyToPoint(this.center);
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double newX = newCenter.getX();
        double newY = newCenter.getY();
        if (dx > 0 && newCenter.getX() + this.radius >= width) { // check if out of bounds right bound.
            this.velocity.setDx(-this.velocity.getDx());
            newX = width - this.radius;
        }
        if (dx < 0 && newCenter.getX() - this.radius < 0) { // check if out of bounds left bound.
            this.velocity.setDx(-this.velocity.getDx());
            newX = this.radius;
        }
        if (dy > 0 && newCenter.getY() + this.radius >= height) { // check if out of bounds bottom bound.
            this.velocity.setDy(-this.velocity.getDy());
            newY = height - this.radius;
        }
        if (dy < 0 && newCenter.getY() - this.radius < 0) { // check if out of bounds top bound.
            this.velocity.setDy(-this.velocity.getDy());
            newY = this.radius;
        }
        this.center.setX(newX);
        this.center.setY(newY);
    }

    /**
     * move the ball to new position according to ball last position and speed with multiple frames in gui.
     * @param startX int - startX of frame.
     * @param startY int - startY of frame.
     * @param endX int - endX of frame.
     * @param endY int - endY of frame.
     */
    public void moveOneStep(int startX, int startY, int endX, int endY) {
        if (this.velocity == null) {
            return;
        }
        // move to new position.
        Point newCenter = this.getVelocity().applyToPoint(this.center);
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double newX = newCenter.getX();
        double newY = newCenter.getY();
        if (dx > 0 && newCenter.getX() + this.radius >= endX) { // check if out of bounds right bound.
            this.velocity.setDx(-this.velocity.getDx());
            newX = endX - this.radius;
        }
        if (dx < 0 && newCenter.getX() - this.radius < startX) { // check if out of bounds left bound.
            this.velocity.setDx(-this.velocity.getDx());
            newX = startX + this.radius;
        }
        if (dy > 0 && newCenter.getY() + this.radius >= endY) { // check if out of bounds bottom bound.
            this.velocity.setDy(-this.velocity.getDy());
            newY = endY - this.radius;
        }
        if (dy < 0 && newCenter.getY() - this.radius < startY) { // check if out of bounds top bound.
            this.velocity.setDy(-this.velocity.getDy());
            newY = startY + this.radius;
        }
        this.center.setX(newX);
        this.center.setY(newY);
    }

    @Override
    public String toString() {
        return "Center: " + this.center + " Radius: " + this.radius + " Color: "
                + this.color + " Velocity: " + this.velocity;
    }
}
