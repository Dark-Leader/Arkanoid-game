package Geometry;

/**
 * Represents a line segment in 2d space.
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * Constructor.
     * @param start Point - start point.
     * @param end Point - end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     * @param x1 double - start x.
     * @param y1 double - start y.
     * @param x2 double - end x.
     * @param y2 double - end y.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * return length of the line segment using pythagoras theorem: a^2 + b^2 = c^2.
     * @return double.
     */
    public double length() {
        double xChange = Math.abs(this.end.getX() - this.start.getX());
        double yChange = Math.abs(this.end.getY() - this.start.getY());
        double length = Math.sqrt(Math.pow(xChange, 2.0) + Math.pow(yChange, 2.0));
        return length;
    }

    /**
     * return middle point of the line segment.
     * @return Point.
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * getter start point.
     * @return Point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * getter end point.
     * @return Point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Check if this line intersects other line in 2d space by calculating the orientations of
     * the lines, and then checks if the lines are collinear then return true only of they
     * start or end the same point.
     * @param other
     * @return boolean - true if line segments intersect, else false.
     */
    public boolean isIntersecting(Line other) {
        int o1 = orientation(this.start, this.end, other.start);
        int o2 = orientation(this.start, this.end, other.end);
        int o3 = orientation(other.start, other.end, this.start);
        int o4 = orientation(other.start, other.end, this.end);

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special Cases
        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(this.start, other.start, this.end)
            && (this.start.equals(other.start) || this.end.equals(other.start))) {
            return true;
        }

        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(this.start, other.end, this.end)
            && (this.start.equals(other.end) || this.end.equals(other.end))) {
            return true;
        }

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(other.start, this.start, other.end)
            && (other.start.equals(this.start) || other.end.equals(this.start))) {
            return true;
        }

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(other.start, this.end, other.end)
            && (other.start.equals(this.end) || other.end.equals(this.end))) {
            return true;
        }

        return false; // Doesn't fall in any of the above cases
    }

    /**
     * check if point r sits on the segment pq.
     * @param p Point - start point.
     * @param q Point - end point.
     * @param r Point - point to check.
     * @return boolean - true if point r sits on the segment pq else false.
     */
    static boolean onSegment(Point p, Point q, Point r) {
        return (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()));
    }

    /**
     * returns the orientation of 3 points - clockwise, counter-clockwise, collinear.
     * @param p Point.
     * @param q Point.
     * @param r Point.
     * @return int - 0 for collinear, 1 for clockwise, 2 counter-clockwise.
     */
    static int orientation(Point p, Point q, Point r) {

        int val = (int) ((q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY()));

        if (val == 0) {
            return 0; // collinear
        }
        return (val > 0) ? 1 : 2; // clock or counter clock wise
    }

    /**
     * returns slope of a line according to formula: y = ax + b -> returns a.
     * @return double - slope of the line.
     */
    public double getSlope() {
        if (this.start.getX() == this.end.getX()) { // collinear with x-axis
            return Double.MAX_VALUE;
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * calculate intercept point for line according to formula: y = ax + b -> b = y - ax.
     * @return double - b.
     */
    public double getIntercept() {
        double slope = getSlope();
        return this.start.getY() - slope * this.start.getX();
    }

    /**
     * returns if line is above other line.
     * @param other Line.
     * @return boolean - true if above else false.
     */
    public boolean isAbove(Line other) {
        return (this.start.getY() >= other.start.getY() && this.start.getY() >= other.end.getY())
                || (this.end.getY() >= other.start.getY() && this.end.getY() >= other.end.getY());
    }

    /**
     * return intersection point with other line if they intersect.
     * @param other Line.
     * @return Point - if they intersect returns a Point else null.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        // edge cases
        if (this.start.equals(this.end)) {
            return new Point(this.start.getX(), this.start.getY());
        }
        if (other.start.equals(other.end)) {
            return new Point(other.start.getX(), other.start.getY());
        }

        double b1, b2, slope1, slope2, xIntercept, yIntercept;

        slope1 = getSlope();
        slope2 = other.getSlope();

        // edge case - current line is collinear to y-axis.
        if (slope1 == Double.MAX_VALUE) {
            double x = this.start.getX();
            double y;
            if (slope2 == Double.MAX_VALUE) { // other line is also collinear to y-axis.
                // check if one line is above the other -> if so then intersection point is at one of the edges
                if (isAbove(other)) {
                    y = Math.min(this.start.getY(), this.end.getY());
                } else {
                    y = Math.min(other.start.getY(), other.end.getY());
                }
                return new Point(x, y);
            }
            // calculate intersection point according to formula: y = ax + b where x is constant.
            b2 = other.getIntercept();
            y = slope2 * x + b2;
            return new Point(x, y);
        }
        if (slope2 == Double.MAX_VALUE) { // other line is collinear to y-axis and this line is not.
            double x = other.start.getX();
            double y;
            // calculate intersection point according to formula: y = ax + b where x is constant.
            b1 = getIntercept();
            y = slope2 * x + b1;
            return new Point(x, y);
        }

        if (slope1 == slope2) { // if they are equal then intersection point can only be if they share a point
            double x, y;
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                x = this.start.getX();
                y = this.start.getY();
            } else if (this.end.equals(other.start) || this.end.equals(other.end)) {
                x = this.end.getX();
                y = this.end.getY();
            } else {
                return null;
            }
            return new Point(x, y);
        }

        /**
         * General case:
         * solved according to formula: y1 = ax + b1, y2 = bx + b2.
         * if they intersect then y1 = y2 so:
         * ax + b1 = bx + b2 -> x(a - b) = b2 - b1 -> x = (b2-b1)/(a - b)
         * now plug this x value to find y:
         * y = a((b2 - b1)/(a-b)) + b1
          */

        b1 = this.start.getY() - slope1 * this.start.getX();
        b2 = other.start.getY() - slope2 * other.start.getX();

        xIntercept = (b2 - b1) / (slope1 - slope2);
        yIntercept = slope1 * ((b2 - b1) / (slope1 - slope2)) + b1;
        return new Point(xIntercept, yIntercept);
    }

    /**
     * checks if line is equal to other line.
     * @param other Line.
     * @return boolean - true if equal else false.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }

    @Override
    public String toString() {
        return "start: " + this.start + "\tend: " + this.end;
    }

}
