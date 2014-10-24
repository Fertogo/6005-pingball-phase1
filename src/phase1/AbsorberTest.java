package phase1;
import physics.*; 
import static org.junit.Assert.*;

import org.junit.Test;

public class AbsorberTest {
//
//    @Test public void holdBall(){ 
//        Board board = new Board(20,20); 
//        Ball ball1 = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
//        Absorber absorber = new Absorber(new Vect(0,0), 1, 1);
//        board.addGadget(absorber); 
//        board.addBall(ball1); 
//        absorber.shootBall(ball1);
////        absorber.toString();
//        
//        
//        //Test with CircleBumper
//       
//       
////        board.step(100); //TODO Ball goes through Bumper instead of hitting it. 
////        assertTrue(ball1.getPosition().x() > 1.25); 
//        System.out.print(absorber.toString(20,20));
//       
//
//    }
   
//        Collisions 
//                Ball Collides with Absorber from the top
//                Ball Collides with Absorber from the bottom
        @Test public void ballCollideTopOfAbsorberTest(){ 
            Board board = new Board(20,20); 
            Ball ball = new Ball(new Vect(8.0,1.25), new Vect(0,1)); 
            Absorber absorber = new Absorber(new Vect(5,5), 10, 1);
            board.addGadget(absorber); 
            board.addBall(ball);
//            System.out.println(ball.getPosition());
            board.step(20);
            
            

        }
        @Test public void ballCollideBottomOfAbsorberTest(){ 
            //TODO
        }
  

}
