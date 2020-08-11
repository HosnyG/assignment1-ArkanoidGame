/**
 * A line segment in coordinate plane.
 * the line connects two points , a start point and an
 * end point.
 *
 * @author : Ganaiem Hosny
 */
public class Line {

    private Point startPoint;
    private Point endPoint;

    /**
     * Constructor.
     * given a start and an end points.
     *
     * @param start : start point.
     * @param end   : end point.
     */
    public Line(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }

    /**
     * Constructor.
     * given coordinates of the start and the end point.
     *
     * @param x1 : x-coordinate of the start point.
     * @param y1 : y-coordinate of the start point.
     * @param x2 : x-coordinate of the end point.
     * @param y2 : y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.startPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return startPoint.distance(endPoint);
    }

    /**
     * Find the middle point of the line segment.
     * Using Mid-point Formula.
     *
     * @return the mid-point.
     */
    public Point middle() {
        double x = (this.startPoint.getX() + this.endPoint.getX()) / 2;
        double y = (this.startPoint.getY() + this.endPoint.getY()) / 2;
        Point midPoint = new Point(x, y);
        return midPoint;
    }

    /**
     * @return the start point of the line segment.
     */
    public Point start() {
        return this.startPoint;
    }

    /**
     * @return the end point of the line segment.
     */
    public Point end() {
        return this.endPoint;
    }

    /**
     * Given three co-linear points p1,p2 and p3.
     * the function checks if point p2 lies on line segment
     * p1p3 , by check if the coordinates of p2 ranging from
     * the coordinates of p1 to p3's coordinates.
     *
     * @param p1 : start point of the segment.
     * @param p2 : the point to check if it lies in the segment.
     * @param p3 : end point of the segment .
     * @return true if p2 lies on p1p3 segment , false otherwise.
     */
    boolean onSegment(Point p1, Point p2, Point p3) {
        //check if p2 lies on p1p3 segment.
        if (p2.getX() <= Math.max(p1.getX(), p3.getX()) && p2.getX() >= Math.min(p1.getX(), p3.getX())
                && p2.getY() <= Math.max(p1.getY(), p3.getY()) && p2.getY() >= Math.min(p1.getY(), p3.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Find orientation of ordered triplet (p1, p2, p3).
     * Orientation of an ordered triplet of points in the plane can be :
     * Counter Clock-Wise,Clock-Wise,Co-linear.
     *
     * Source: https://www.geeksforgeeks.org/orientation-3-ordered-points
     *
     * @param p1 : Point in the plane.
     * @param p2 : Point in the plane.
     * @param p3 : Point in the plane.
     * @return 0 if p1,p2 and p3 are Co-linear
     * 1 if p1,p2 and p3 are Clock-Wise.
     * 2 if p1,p2 and p3 are Counter-Clock-Wise.
     */
    int orientation(Point p1, Point p2, Point p3) {

        //orientation formula.
        double value = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX())
                - (p2.getX() - p1.getX()) * (p3.getY() - p2.getY());
        if (value == 0) {
            return 0; //co-linear
        }
        if (value > 0) {
            return 1; //Clock wise
        } else {
            return 2; //Counter-clock wise
        }
    }

    /**
     * Check if the line-segment intersects with other line segment.
     * using Points Orientation way.
     * two segments (p1,p2) and (p3,p4) intersects if and only if
     * one of the two condition is verified :
     * 1.General case:
     * (p1, p2, p3) and (p1, p2, p4) have different orientations and
     * (p3, p4, p1) and (p3, p4, p2) have different orientations.
     *
     * 2.special case:
     * (p1, p2, p3), (p1, p2, p4), (p3, p4, p1), and (p3, p4, p2) are all collinear and
     * the x-projections of (p1, p2) and (p3, p4) intersect.
     * the y-projections of (p1, q1) and (p3, p4) intersect.
     *
     * Source: Computational Geometry - Chapter 22 .
     *
     * @param other : line to check intersection with it.
     * @return true if the two lines intersects , false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point p1 = this.startPoint;
        Point p2 = this.endPoint;
        Point p3 = other.startPoint;
        Point p4 = other.endPoint;

        //finding the four orientations needed.
        int o1 = orientation(p1, p2, p3);
        int o2 = orientation(p1, p2, p4);
        int o3 = orientation(p3, p4, p1);
        int o4 = orientation(p3, p4, p2);

        //General case.
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        //Special cases.
        //p1, p2 and p3 are co-linear and p2 lies on segment p1p2
        if (o1 == 0 && onSegment(p1, p3, p2)) {
            return true;
        }
        // p1, p2 and p4 are co-linear and p4 lies on segment p1p2
        if (o2 == 0 && onSegment(p1, p4, p2)) {
            return true;
        }
        // p3, p4 and p1 are co-linear and p1 lies on segment p3q4
        if (o3 == 0 && onSegment(p3, p1, p4)) {
            return true;
        }
        // p1, p4 and p2 are co-linear and p2 lies on segment p3q4
        if (o4 == 0 && onSegment(p3, p2, p4)) {
            return true;
        }
        return false; //the two conditions are not verified.

    }

    /**
     * calculate the line slope using Slope-Formula.
     *
     * @return the slope of the line.
     */
    public double getSlope() {
        double x1 = startPoint.getX();
        double y1 = startPoint.getY();
        double x2 = endPoint.getX();
        double y2 = endPoint.getY();
        return (y2 - y1) / (x2 - x1); //slope formula.
    }

    /**
     * get the -b- (intersection with Y-axis) value of the line.
     * line equation : y=mx+b.
     * given one point in the line and the slope of the line ,
     * calculate the b after substitute x and y coordinates of the
     * point in equation.
     *
     * @param slope      : line's slope.
     * @param point : line's start point.
     * @return b-value.
     */
    double getB(double slope, Point point) {
        //y=mx+b -> b=y-mx.
        return point.getY() - (slope * point.getX());
    }

    /**
     * finding the intersection Point with other line.
     * by treating the line-segments as boundless line,
     * because the lines if they intersect , intersect once.
     * doing that by setting the two y-coordinates
     * equal (of the two lines), i.e. :
     * first line equation : y1=m1x1+b1.
     * second line equation : y2=m2x2+b2.
     * m1x+b1=m2x+b2 => m1x-m2x=b2-b1 => x=(b1-b2)/(m2-m1)
     * this is intersection point' x-coordinate , for y,
     * placing the x-coordinate into either of the equations.
     *
     * @param other : line to find the intersection point with it.
     * @return the intersection point of the two lines.
     */
    public Point intersectionWith(Line other) {
        double m1 = this.getSlope();
        double m2 = other.getSlope();

        double b1 = getB(m1, this.startPoint);
        double b2 = getB(m2, other.startPoint);

        double x = (b1 - b2) / (m2 - m1); // x-coordinate of the intersection point.
        double y = (m1 * x) + b1; // placing x into this line equation.
        return new Point(x, y);
    }

    /**
     * check if the two lines segments are equal.
     *
     * @param other : line-segment sto compare with .
     * @return true if the two lines segments are equal , false otherwise.
     */
    public boolean equals(Line other) {
        Point p1 = this.startPoint;
        Point p2 = this.endPoint;
        Point p3 = other.start();
        Point p4 = other.end();
        //two lines-segments are equal if the have the same start and end points vice versa.
        if ((p1.equals(p3) && p2.equals(p4)) || ((p1.equals(p4) && p2.equals(p3)))) {
            return true;
        }
        return false;
    }
}