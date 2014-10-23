package phase1;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;

public class BallTest {

    @Test
    public void testFreeFall() throws InterruptedException {
        Board board = new Board(20, 20);
        Vect velocity = new Vect(0,0);
        Vect position = new Vect(3.0, 3.0);
        Ball ball = new Ball(position, velocity);
        board.addBall(ball);
        
        
        
        int steps = 20;
        for(int i=1; i<steps; i++){
            board.step();
            Thread.sleep(100);
        }
        
        assertEquals(19.0, ball.getPosition().y(), 0);
    }
    @Test
    public void testSquareBumper() throws InterruptedException{
        Board board = new Board(20, 20);
        Vect velocity = new Vect(.5,0);
        Vect position = new Vect(3.0, 3.0);
        Ball ball = new Ball(position, velocity);
        board.addBall(ball);
        
        Gadget sBumper1 = new SquareBumper(new Vect(3.0, 10.0));
        Gadget sBumper2 = new CircleBumper(new Vect(4.0, 11.0));
        Gadget sBumper3 = new CircleBumper(new Vect(5.0, 12.0));
        board.addGadget(sBumper1);
        board.addGadget(sBumper2);
       // board.addGadget(sBumper3);

        
        int steps = 2000;
        for(int i=1; i<steps; i++){
            
            board.step();
            System.out.println(ball.getVelocity());
            System.out.println(ball.getPosition());
            //Thread.sleep();
        }
    }
    
    @Test public void testBallsCollidingWithEachother() { 
        Board board  = new Board (20,20); 
        Ball ball1 = new Ball(new Vect(5,5)); 
        Ball ball2 = new Ball(new Vect(15,5), new Vect(-20,0)); 
        board.addBall(ball1); 
        board.addBall(ball2);
        board.step(300); 
        fail("TODO"); 

    }
}
