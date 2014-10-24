package phase1;
import physics.*; 
import static org.junit.Assert.*;

import org.junit.Test;

public class AbsorberTest {
<<<<<<< HEAD
//
=======

    @Test public void holdBall(){ 
        Board board = new Board(20,20); 
        Ball ball1 = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
        Absorber absorber = new Absorber(new Vect(0,16), 20, 4);
        board.addGadget(absorber); 
        board.addBall(ball1); 
        absorber.shootBall(ball1);
        
        
        //Test with CircleBumper
       
       
//        board.step(100); //TODO Ball goes through Bumper instead of hitting it. 
//        assertTrue(ball1.getPosition().x() > 1.25); 
        System.out.print(board.toString());
       

    }
    /**
     * Absorber Testing Strategy
     *      Make sure ball bounces off correctly on stable and moving flipper
     *      Partitions:
     *          Basics: 
     *             Construction of Absorber of Multiple Widths and Height
     *                  Try a Width of 1, 20, 10
     *                  Try a Height of 1, 20
     *             Trigger a Ball Storage
     *                  Trigger A ball Storage with no balls in the 
     *                  Trigger another BallStorage one time step later 
     *             Trigger a Ball Release
     *                  With One Ball Already in the Absorber
     *                  With Two Balls in Absorber      
     *             
     *          Collisions 
     *             Ball Collides with Absorber from the top
     *             Ball Collides with Absorber from the bottom
     *              
     *             
     */
    //TODO
>>>>>>> 79ead91b3cb8cf1d7c1ff109967cdf4e3f98c976
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
