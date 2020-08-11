import biuoop.DrawSurface;

/**
 * A ball (circle) in frame.
 *
 * @author : Ganaiem Hosny
 */
public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private double rightBoundary;
    private double leftBoundary;
    private double bottomBoundary;
    private double topBoundary;

    /**
     * Constructor.
     * given the center point,radius and color.
     *
     * @param center : ball's center point.
     * @param r      :  ball's radius.
     * @param color  : ball's color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor.
     * given the x and y coordinates of the center point of the ball,
     * radius and color.
     *
     * @param x     : x-coordinate of the ball's center point.
     * @param y     : y-coordinate of the ball's center point.
     * @param r     :  ball's radius.
     * @param color : ball's color.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        Point centerPoint = new Point(x, y);
        this.center = centerPoint;
        this.radius = r;
        this.color = color;
    }

    /**
     * @return x-coordinate of the ball's center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y-coordinate of the ball's center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface : draw surface to draw the ball on it.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * set velocity to the ball.
     * given the velocity.
     *
     * @param v : the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set velocity to the ball.
     * given the change in position on the `x` and the `y` axes.
     *
     * @param dx : change in position on the x axis.
     * @param dy : change in position on the y axis.
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * @return ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set the frame's boundaries that the ball resides
     * between them.
     *
     * @param top    : frame's top boundary.
     * @param bottom : frame's bottom boundary.
     * @param right  : frame's  right boundary.
     * @param left   :  frame's left boundary.
     */
    public void setBoundaries(double top, double bottom, double right, double left) {
        this.topBoundary = top;
        this.bottomBoundary = bottom;
        this.rightBoundary = right;
        this.leftBoundary = left;
    }

    /**
     * move the the ball one step according to it's velocity.
     * taking ball's exit into consideration.
     */
    public void moveOneStep() {
        //restrict it from the right and left
        if (this.center.getX() >= this.rightBoundary || this.center.getX() <= this.leftBoundary) {
            this.setVelocity(-(this.velocity.getDx()), this.velocity.getDy()); //Changing the horizontal direction
        }
        //restrict it from above and below
        if (this.center.getY() >= this.bottomBoundary || this.center.getY() <= this.topBoundary) {
            this.setVelocity((this.velocity.getDx()), -(this.velocity.getDy())); //Changing the vertical direction
        }
        //change ball's center point position according to the new velocity.
        this.center = this.getVelocity().applyToPoint(this.center);
    }
}