package phase1;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testMergeBoard() {
        Board board = new Board(20,20); 
        Gadget circleBumper = new CircleBumper(10,10); 
        Gadget squareBumper = new SquareBumper(1,1); 
        Gadget squareBumper2 = new SquareBumper(5,5); 
        board.addGadget(circleBumper);
        board.addGadget(squareBumper); 
        board.addGadget(squareBumper2);
        System.out.println(board.toString()) ;
               
    }

}
