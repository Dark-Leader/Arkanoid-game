package Sprites;

import Geometry.Line;
import Geometry.Point;
import Geometry.Velocity;
import biuoop.DrawSurface;
import interfaces.Sprite;
import settings.CollisionInfo;
import settings.Game;
import settings.GameEnvironment;


/**
 * Represents a Ball in 2d space.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;

    private Velocity velocity;

    private GameEnvironment environment;

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
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
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
     */
    public void moveOneStep() {
        if (this.velocity == null) {
            return;
        }
        // move to new position
        Point newCenter = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, newCenter);
        if (this.environment == null) {
            this.center = newCenter;
            return;
        }
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info == null) {
            this.center = newCenter;
            return;
        }
        Velocity newVelocity = info.collisionObject().hit(info.collisionPoint(), this.velocity);
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double x = info.collisionPoint().getX();
        double y = info.collisionPoint().getY();
        if (dx >= 0) {
            newCenter.setX(x - 3);
        }
        if (dx <= 0) {
            newCenter.setX(x + 3);
        }

        if (dy >= 0) {
            newCenter.setY(y - 3);
        }
        if (dy <= 0) {
            newCenter.setY(y + 3);
        }
        this.center = newCenter;
        this.velocity = newVelocity;

    }

    /**
     * setter for ball center.
     * @param center Ball.
     */
    public void setCenter(Point center) {
        this.center = center;
    }


    @Override
    public String toString() {
        return "Center: " + this.center + " Radius: " + this.radius + " Color: "
                + this.color + " Velocity: " + this.velocity;
    }

    /**
     * setter for gameEnvironment.
     * @param gameEnvironment GameEnvironment.
     */
    public void setGameEnviroment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * Add ball to the game -> ball is a sprite, so we need to add it to list of sprites.
     * @param game Game - game to add ball to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
