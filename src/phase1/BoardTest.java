package phase1;
import physics.*; 
import physics.Vect; 

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
        System.out.println(board.toString()) ;
               
    }
    
    @Test
    public void testBouncingBall(){ 
        Board board = new Board(20,20); 
        Vect position = new Vect(5,5); 
        Vect velocity = new Vect(1,1); 
        Ball ball = new Ball(position, velocity); 
        
        board.addBall(ball); 
        board.step(); 
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
