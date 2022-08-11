package interfaces;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

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
     * @return Velocity.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
