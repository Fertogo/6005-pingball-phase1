package phase1;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;

public class BallTest {

    @Test
    public void testFreeFall() throws InterruptedException {
        Board board = new Board(20, 20);
        Vect velocity = new Vect(500, 10000);
        Vect position = new Vect(3.0, 3.0);
        Ball ball = new Ball(position, velocity, 25);
        board.addBall(ball);
        
        Gadget sBumper1 = new CircleBumper(new Vect(3.0, 10.0));
        Gadget sBumper2 = new CircleBumper(new Vect(4.0, 10.0));
        Gadget sBumper3 = new CircleBumper(new Vect(5.0, 10.0));
        Gadget sBumper4 = new CircleBumper(new Vect(6.0, 10.0));
        board.addGadget(sBumper1);
        board.addGadget(sBumper2);
        board.addGadget(sBumper3);
        board.addGadget(sBumper4);
        
        int steps = 200;
        for(int i=1; i<steps; i++){
            board.step();
            Thread.sleep(100);
        }
        
        assertEquals(19.0, ball.getPosition().y(), 0);
    }

}
