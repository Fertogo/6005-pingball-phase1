package phase1;
import physics.*; 
import static org.junit.Assert.*;

import org.junit.Test;

public class AbsorberTest {

    @Test public void holdBall(){ 
        Board board = new Board(20,20); 
        Ball ball1 = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
        Absorber absorber = new Absorber(10,10, 3, 2);
        board.addGadget(absorber); 
        board.addBall(ball1); 
        absorber.shootBall(ball1);
//        absorber.toString();
        
        
        //Test with CircleBumper
       
       
//        board.step(100); //TODO Ball goes through Bumper instead of hitting it. 
//        assertTrue(ball1.getPosition().x() > 1.25); 
        System.out.print(absorber.toString(20,20));
       

    }

}
