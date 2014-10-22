package phase1;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;

public class BallTest {

    @Test
    public void test() {
        Board board = new Board(20, 20);
        Vect velocity = new Vect(7.0, 7.0);
        Vect position = new Vect(3.0, 3.0);
        Ball ball = new Ball(position, velocity);
        board.addBall(ball);
        while(true){
            System.out.println("Actual Velocity: " + ball.getVelocity());
            board.step();
        }
    }

}
