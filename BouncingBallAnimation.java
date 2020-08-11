import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * A Bouncing Ball Animation .
 *
 * @author : Ganaiem Hosny
 */
public class BouncingBallAnimation {
    /**
     * Main method of BouncingBallAnimation.
     * creating a new GUI object create a screen with dimensions,
     * drawing three bouncing balls on the screen.
     *
     * @param args :  arguments are ignored.
     */
    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        //create a screen.
        GUI gui = new GUI("Bouncing Ball Animation", width, height);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        //define the balls.
        Ball ball1 = new Ball(50, 100, 30, Color.blue);
        Ball ball2 = new Ball(50, 100, 30, Color.red);
        Ball ball3 = new Ball(50, 100, 30, Color.yellow);
        //set the boundaries of the screen to the balls.
        ball1.setBoundaries(0, height, width, 0);
        ball2.setBoundaries(0, height, width, 0);
        ball3.setBoundaries(0, height, width, 0);
        //set certain velocity for each ball.
        ball1.setVelocity(6, 8);
        ball2.setVelocity(4, 11);
        ball3.setVelocity(20, 25);

        /*
        creating a surface that draw every ball on the screen,
        and every ball moving Sequentially.
         */
        while (true) {
            //moving the balls according to their velocity.
            ball1.moveOneStep();
            ball3.moveOneStep();
            ball2.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            //draw the ball in the surface.
            ball1.drawOn(d);
            ball2.drawOn(d);
            ball3.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}