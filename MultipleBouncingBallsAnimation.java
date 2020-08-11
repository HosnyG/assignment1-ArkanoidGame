import biuoop.DrawSurface;
import biuoop.GUI;

import java.util.Random;
import java.awt.Color;

/**
 * A Multiple Bouncing Balls Animation.
 *
 * @author : Ganaiem Hosny
 */
public class MultipleBouncingBallsAnimation {

    /**
     * Main method of MultipleBouncingBallsAnimation.
     * creating a new GUI object create a screen with dimensions,
     * drawing balls with certain sizes getting from the main
     * arguments, the balls get their velocity according to their
     * size ,big size=>less velocity ,small size=>big velocity.
     *
     * @param args : every argument represent ball's size.
     */
    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        Random rand = new Random(); // create a random-number generator
        //Create a window
        GUI gui = new GUI("Multiple bouncing balls Animation", width, height);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        //there is no arguments.
        if (args.length == 0) {
            System.out.println("there is no balls to draw!");
            return;
        }
        //balls's keeper array.
        Ball[] balls = new Ball[args.length];

        /*
        creating a new balls with random center point and color
        besides to the size (radius) respectively.
         */
        for (int i = 0; i < args.length; i++) {
            int x = rand.nextInt(width) + 1; // in range 1 - width of the screen.
            int y = rand.nextInt(height) + 1; // in range 1 - height of the screen.
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            //create a new random colour by creating random primary colours.
            Color randomColor = new Color(r, g, b);
            //creating the ball with appropriate characteristics.
            balls[i] = new Ball(x, y, Integer.parseInt(args[i]), randomColor);
            //set the boundaries of the screen to the ball.
            balls[i].setBoundaries(0, height, width, 0);
        }

        //sorting the balls in the array according to their size, from bigger to smaller.
        for (int i = 0; i < balls.length; i++) {
            for (int j = i + 1; j < balls.length; j++) {
                if (balls[i].getSize() < balls[j].getSize()) {
                    Ball tempBall = balls[i]; //keep in temp ball.
                    balls[i] = balls[j];
                    balls[j] = tempBall;
                }
            }
        }
        //setting the minimal velocity.
        Velocity minimalVelocity = new Velocity(3, 3);
        //the big ball will start in the minimal velocity.
        balls[0].setVelocity(minimalVelocity);

        /*
        if the ball smaller , will get big velocity from it's former ball.
        every balls that their size more than 50 have the same velocity and
        that's the bigger's velocity.
        every two balls have the same size , will get the same velocity.
         */
        for (int i = 1; i < balls.length; i++) {
            //balls bigger than 50 get the same velocity(the bigger balls's velocity).
            if (balls[i].getSize() >= 50) {
                balls[i].setVelocity(balls[0].getVelocity());
                continue;
            }
            //balls with same size get same velocity.
            if (balls[i].getSize() == balls[i - 1].getSize()) {
                balls[i].setVelocity(balls[i - 1].getVelocity());
                continue;
            }
            //smaller big get bigger velocity than it's former balls.
            //former ball's velocity+1<dx=dy<10
            int dx = rand.nextInt(10) + (int) balls[i - 1].getVelocity().getDx() + 1;

            int dy = dx; //symmetrical change in y and x axes.
            balls[i].setVelocity(dx, dy);
        }

        //draw the balls on the screen and moving according to their velocity.
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}