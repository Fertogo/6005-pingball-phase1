package phase1;
import physics.*; 
import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testMergeBoard() {
        Board board = new Board(20,20); 


        //Gadget circleBumper = new CircleBumper(10,10);
        Vect vector = new Vect(1, 1);
        Vect vector2 = new Vect(5, 5);
        Gadget squareBumper = new SquareBumper(vector); 
        Gadget squareBumper2 = new SquareBumper(vector2); 
        //board.addGadget(circleBumper);

        board.addGadget(squareBumper); 
        board.addGadget(squareBumper2);
        board.step();     
    }
    
    @Test
    public void testBouncingBall(){ 
        Board board = new Board(20,20); 

        Vect position = new Vect(2,5); 

        Vect velocity = new Vect(2,2); 


        Vect bumperPosition = new Vect(5, 5);
        Gadget squareBumper = new SquareBumper(bumperPosition);
        board.addGadget(squareBumper); 
        
        Ball ball = new Ball(position, velocity, 25); 
        
        board.addBall(ball); 

        int steps = 10; 
        for (int i=0; i<steps; i++){ 
            board.step(); 
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Done");
    }
    
    @Test public void testTwoBallsHitSameWallSameTime(){ 
        Board board = new Board(20,20); 
        Ball ball1 = new Ball(new Vect(5,5), new Vect(-1,0),0); 
        Ball ball2 = new Ball(new Vect(5,10), new Vect(-1,0),0); 
        board.addBall(ball1); 
        board.addBall(ball2);
        board.step(70); //Watch how the balls go out of sync! 
        fail("Not in Sync"); 

    }
//    public void testCheckRep(){ 
//        Board board = new Board(20,20); 
//        Gadget circleBumper = new CircleBumper(10,10);
//        Gadget circleBumper2 = new CircleBumper(10,10); 
//        board.addGadget(circleBumper); 
//        board.addGadget(circleBumper2); 
//        //TODO: Assert that checkrep fails
//    }

}
