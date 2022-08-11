package Sprites;

import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.Sprite;
import settings.Game;

import java.awt.Color;

/**
 * Represents a Base Block object in the game.
 * could be border block, game block or paddle.
 */
public class Block implements Collidable, Sprite {

    private Rectangle rectangle;
    private Color color = Color.DARK_GRAY;

    private Velocity velocity;

    /**
     * Constructor.
     * @param rectangle Rectangle - underlying rectangle to know the location of the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * setter for rectangle.
     * @param rectangle Rectangle.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity hitEdge = hitEdge(collisionPoint, currentVelocity);
        if (hitEdge != null) {
            return hitEdge;
        }
        Velocity hitLine = hitSide(collisionPoint, currentVelocity);
        return hitLine;
    }

    /**
     * check which side did the ball hit the block on - could be top, left, bottom, right.
     * if top or bottom we change the dy vector of the velocity, else change dx vector.
     * @param collisionPoint Point - collision point of the ball with the block.
     * @param current Velocity - current ball velocity prior to collision.
     * @return Velocity - new velocity based direction of impact.
     */
    private Velocity hitSide(Point collisionPoint, Velocity current) {
        Line left = rectangle.getLeft();
        Line right = rectangle.getRight();
        double dx = current.getDx();
        double dy = current.getDy();
        if (Line.isBetween(left.start(), left.end(), collisionPoint)
                || Line.isBetween(right.start(), right.end(), collisionPoint)) {
            return new Velocity(-dx, dy);
        } else {
            return new Velocity(dx, -dy);
        }
    }

    /**
     * check if collision happened on one the 4 corners of the block,
     * if so then call handlers.
     * @param collisionPoint Point - collision point of ball with the block.
     * @param current Velocity - ball velocity prior to collision.
     * @return Velocity - new ball velocity.
     */
    private Velocity hitEdge(Point collisionPoint, Velocity current) {
        if (collisionPoint.equals(rectangle.getTopLeft())) {
            return handleTopLeftHit(current);
        } else if (collisionPoint.equals(rectangle.getTopRight())) {
            return handleTopRightHit(current);
        } else if (collisionPoint.equals(rectangle.getBottomLeft())) {
            return handleBottomLeftHit(current);
        } else if (collisionPoint.equals(rectangle.getBottomRight())) {
            return handleBottomRightHit(current);
        }
        return null;
    }

    /**
     * handler for impact with top left corner.
     * @param current Velocity - ball velocity prior to collision.
     * @return Velocity - new ball velocity.
     */
    private Velocity handleTopLeftHit(Velocity current) {
        double dx = current.getDx();
        double dy = current.getDy();
        if (dx >= 0 && dy >= 0) {
            return new Velocity(-dx, -dy);
        } else if (dx >= 0 && dy <= 0) {
            return new Velocity(-dx, dy);
        } else if (dx <= 0 && dy >= 0) {
            return new Velocity(dx, -dy);
        } else {
            return new Velocity(-dx, -dy);
        }
    }
    /**
     * handler for impact with top right corner.
     * @param current Velocity - ball velocity prior to collision.
     * @return Velocity - new ball velocity.
     */
    private Velocity handleTopRightHit(Velocity current) {
        double dx = current.getDx();
        double dy = current.getDy();
        if (dx >= 0 && dy >= 0) {
            return new Velocity(dx, -dy);
        } else if (dx >= 0 && dy <= 0) {
            return new Velocity(-dx, -dy);
        } else if (dx <= 0 && dy >= 0) {
            return new Velocity(-dx, -dy);
        } else {
            return new Velocity(-dx, dy);
        }
    }
    /**
     * handler for impact with bottom left corner.
     * @param current Velocity - ball velocity prior to collision.
     * @return Velocity - new ball velocity.
     */
    private Velocity handleBottomLeftHit(Velocity current) {
        double dx = current.getDx();
        double dy = current.getDy();
        if (dx >= 0 && dy >= 0) {
            return new Velocity(-dx, dy);
        } else if (dx >= 0 && dy <= 0) {
            return new Velocity(-dx, -dy);
        } else if (dx <= 0 && dy >= 0) {
            return new Velocity(-dx, -dy);
        } else {
            return new Velocity(dx, -dy);
        }
    }
    /**
     * handler for impact with bottom right corner.
     * @param current Velocity - ball velocity prior to collision.
     * @return Velocity - new ball velocity.
     */
    private Velocity handleBottomRightHit(Velocity current) {
        double dx = current.getDx();
        double dy = current.getDy();
        if (dx >= 0 && dy >= 0) {
            return new Velocity(-dx, -dy);
        } else if (dx >= 0 && dy <= 0) {
            return new Velocity(dx, -dy);
        } else if (dx <= 0 && dy >= 0) {
            return new Velocity(-dx, dy);
        } else {
            return new Velocity(-dx, -dy);
        }
    }

    /**
     * setter for color.
     * @param color Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * draw the block on the given draw surface.
     * @param d DrawSurface - surface to draw on.
     */
    public void drawOn(DrawSurface d) {

        int x = (int) rectangle.getTopLeft().getX();
        int y = (int) rectangle.getTopLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();
        d.setColor(color);
        d.fillRectangle(x, y, width, height);

    }

    /**
     * if block doesn't have a velocity - NOT a paddle then we do nothing, else move the block to
     * new location.
     */
    @Override
    public void timePassed() {
        if (this.velocity == null) {
            return;
        }
        Point topLeft = rectangle.getTopLeft();
        double x = topLeft.getX();
        double y = topLeft.getY();
        x += velocity.getDx();
        y += velocity.getDy();
        this.rectangle = new Rectangle(new Point(x, y), rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * setter from velocity.
     * @param velocity Velocity.
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * add block to a game.
     * since a block is both a sprite and a collidable then we add the block to both collections.
     * @param game Game - game to add block to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}