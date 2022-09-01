package Sprites;

import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import biuoop.DrawSurface;
import interfaces.Sprite;
import settings.CollisionInfo;
import settings.GameLevel;
import settings.GameEnvironment;

import java.awt.Color;


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
     * @param d DrawSurface - canvas for gui.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(getX(), getY(), getSize());
        d.setColor(Color.BLACK);
        d.drawCircle(getX(), getY(), getSize());
    }

    /**
     * move in the ball in space.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * remove the ball from the game.
     * @param game GameLevel.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
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
        Velocity newVelocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        double dx = velocity.getDx();
        double dy = velocity.getDy();
        double x = info.collisionPoint().getX();
        double y = info.collisionPoint().getY();
        double space = 3;
        if (dx >= 0) {
            newCenter.setX(x - space);
        }
        if (dx < 0) {
            newCenter.setX(x + space);
        }

        if (dy >= 0) {
            newCenter.setY(y - space);
        }
        if (dy < 0) {
            newCenter.setY(y + space);
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
     * @param game GameLevel - game to add ball to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
