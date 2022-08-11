package settings;

import Geometry.Point;
import interfaces.Collidable;

/**
 * Represents a description of a collision between a sprite and a collidable object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable object;

    /**
     * Constructor.
     * @param collisionPoint Point - collision point between sprite and collidable object.
     * @param object Collidable - object whom the collision happened with.
     */
    public CollisionInfo(Point collisionPoint, Collidable object) {
        this.collisionPoint = collisionPoint;
        this.object = object;
    }

    /**
     * getter for collision point.
     * @return Point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * getter for collidable object.
     * @return Collidable.
     */
    public Collidable collisionObject() {
        return object;
    }
}
