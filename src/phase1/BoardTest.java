package phase1;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testMergeBoard() {
        //Can't write tests until gadgets toString are implemented :( 
        String layer1 = "       #       \n       #       ";
        String layer2 = "#              \n               "; 


        Board board = new Board(); 
        assertEquals(board.toString() , "#      #       \n       #       "); 
               
    }

}
