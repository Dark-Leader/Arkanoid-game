package interfaces;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import Sprites.Ball;

/**
 * Represents an object who supports collisions with other objects.
 */
public interface Collidable {
    /**
     * return the rectangle where the collision happened.
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * update ball velocity to new velocity after hitting the collidable object.
     * @param collisionPoint Point - collision point with the collidable object.
     * @param currentVelocity Velocity - ball velocity prior to the collision.
     * @param hitter Ball - ball that hit the collidable object.
     * @return Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
