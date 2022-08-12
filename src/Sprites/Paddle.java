package Sprites;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Collidable;
import interfaces.Sprite;
import settings.Game;

/**
 * Represents a paddle -> controlled by the player to move left and right.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor sensor;
    private Block body;
    private double speed = 10;
    private int rightBorder;
    private int leftBorder;

    /**
     * Constructor.
     * @param sensor KeyBoardSensor - sensor to follow keyboard inputs from user to move the paddle.
     * @param body Block - represents the location of the paddle.
     * @param leftBorder int - paddle can't go out of bounds -> this is the left bound.
     * @param rightBorder int - paddle can't go out of bounds -> this is the right bound.
     */
    public Paddle(KeyboardSensor sensor, Block body, int leftBorder, int rightBorder) {
        this.sensor = sensor;
        this.body = body;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    /**
     * moves paddle to the left.
     * if paddle is too close to left border then we make new paddle location next to left border.
     * else move the paddle according to velocity.
     */
    public void moveLeft() {
        Rectangle rect = body.getCollisionRectangle();
        Point topLeft = rect.getTopLeft();
        double x = topLeft.getX();
        double y = topLeft.getY();

        if (x - speed <= leftBorder) {
            x = leftBorder;
            this.body.setRectangle(new Rectangle(new Point(x, y), rect.getWidth(), rect.getHeight()));
        } else {
            x -= speed;
            this.body.setRectangle(new Rectangle(new Point(x, y), rect.getWidth(), rect.getHeight()));
        }
    }

    /**
     * moves paddle to the right.
     * if paddle is too close to left border then we make new paddle location next to right border.
     * else move the paddle according to velocity.
     */
    public void moveRight() {
        Rectangle rect = body.getCollisionRectangle();
        Point topLeft = rect.getTopLeft();
        double x = topLeft.getX();
        double y = topLeft.getY();

        if (x + rect.getWidth() + speed >= rightBorder) {
            x = rightBorder - rect.getWidth();
        } else {
            x += speed;
        }
        this.body.setRectangle(new Rectangle(new Point(x, y), rect.getWidth(), rect.getHeight()));
    }

    /**
     * check if paddle needs to move, if so move it accordingly.
     * can't have 2 inputs at once -> they cancel each other out.
     */
    public void timePassed() {
        if (sensor.isPressed(KeyboardSensor.LEFT_KEY) && !sensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveLeft();
        } else if (sensor.isPressed(KeyboardSensor.RIGHT_KEY) && !sensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw paddle on to a draw surface.
     * @param d DrawSurface - surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        this.body.drawOn(d);
    }

    /**
     * getter for rectangle.
     * @return Rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.body.getCollisionRectangle();
    }

    /**
     * a ball hit the paddle, so we need to check where the collision happened and give
     * the ball a new velocity according to collision location.
     * @param hitter Ball - ball that hit the collidable object.
     * @param collisionPoint Point - collision point with the collidable object.
     * @param currentVelocity Velocity - ball velocity prior to the collision.
     * @return
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        /**
         * split paddle into 5 sections:
         * first section -> make ball move very left.
         * second section -> make ball move left.
         * third section -> normal behavior - flip dy vector of ball.
         * fourth section -> make ball move right.
         * fifth section -> make ball move very right.
         */
        Rectangle rect = this.body.getCollisionRectangle();
        double width = rect.getWidth();
        double ratio = width / 5;
        Line top = rect.getTop();
        if (!Line.isBetween(top.start(),  top.end(), collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        double xMin = top.start().getX();
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        Velocity result;
        if (collisionPoint.getX() < xMin + ratio) {
            result = Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionPoint.getX() < xMin + ratio * 2) {
            result = Velocity.fromAngleAndSpeed(330, speed);
        } else if (collisionPoint.getX() < xMin + ratio * 3) {
            result = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPoint.getX() < xMin + ratio * 4) {
            result = Velocity.fromAngleAndSpeed(30, speed);
        } else {
            result = Velocity.fromAngleAndSpeed(60, speed);
        }
        return result;
    }

    /**
     * add paddle to the game.
     * since paddle is a sprite and collidable we add it to both collections.
     * @param g Game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
