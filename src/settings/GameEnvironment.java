package settings;

import Geometry.Line;
import Geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;

/**
 * Represents a collection of Collidable objects in a game.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<>();

    /**
     * add Collidable object to the collection.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * compute the closest collision point between trajectory line and all the
     * collidable objects in the collection (the game).
     * @param trajectory Line - line trajectory of a sprite.
     * @return CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestObj = null;
        double minDist = Double.POSITIVE_INFINITY;
        Point closestCollisionPoint = null;
        for (Collidable c: collidables) {
            Point collision = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (collision != null) {
                double distance = collision.distance(trajectory.start());
                if (distance < minDist) {
                    minDist = distance;
                    closestObj = c;
                    closestCollisionPoint = collision;
                }
            }
        }
        if (closestObj != null && closestCollisionPoint != null) {
            return new CollisionInfo(closestCollisionPoint, closestObj);
        }
        return null;
    }

}
