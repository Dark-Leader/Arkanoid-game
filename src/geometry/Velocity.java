package geometry;

import java.util.Random;

/**
 * Represents Velocity of object in 2d space.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx double.
     * @param dy double.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * move point according to velocity in 2d space.
     * @param p Point.
     * @return Point - new point after movement.
     */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }

    /**
     * calculate Velocity from angle and speed.
     * @param angle double - [0,360], direction.
     * @param speed double - speed in said direction.
     * @return Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // calculating the X and Y vectors for the new Velocity object.
        double angleInRadians = Math.toRadians(angle);
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);
        // returning the new Velocity object.
        return new Velocity(dx, dy);
    }

    /**
     * Generate random velocity with dx in range [minX, maxX], dy in range [minY, maxY].
     * @param minX double.
     * @param maxX double.
     * @param minY double.
     * @param maxY double.
     * @param flipX boolean - true to randomly flip dx.
     * @param flipY boolean - true to randomly flip dy.
     * @return Velocity.
     */
    public static Velocity generateRandomVelocity(double minX, double maxX, double minY, double maxY,
                                                   boolean flipX, boolean flipY) {
        Random rand = new Random();
        double dx = minX + (maxX - minX) * rand.nextDouble();
        double dy = minY + (maxY - minY) * rand.nextDouble();
        if (flipX && rand.nextBoolean()) {
            dx *= -1;
        }
        if (flipY && rand.nextBoolean()) {
            dy *= -1;
        }

        return new Velocity(dx, dy);
    }

    /**
     * getter dx.
     * @return double.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getter dy.
     * @return double.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * setter dx.
     * @param dx double.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * setter dy.
     * @param dy double.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    @Override
    public String toString() {
        return "Dx: " + dx + ", Dy: " + dy;
    }
}
