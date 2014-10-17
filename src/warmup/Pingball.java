package warmup;
import physics.*; 

public interface Pingball {

/*
 * Playing Area:
 * playing area must be 20 L wide by 20 L high. That is, 400 square bumpers
 * The upper left corner is (0,0) and the lower right corner is (20,20).
 */
public static Pingball ball(){ 
    return new Ball("sadf"); 
}

/*
 * Gadgets:
 * Every gadget has an (x,y) location, where x and y are integers in [0,19].
 * Every gadget has a width and height, also integers in [0,19]Others are configurable
 * Some gadgets have a coefficient of reflection, this is a multiplier applied to the ball's 
 *      velocity after it bounces off the gadget. Default multiplier is 1.0. 
 * Some gadgets have an orientation - how the gadget is rotated from its default orientation. 
 *      This is given in degrees (clockwise). Default orientation is 0. 
 * Some gadgets have triggers - an event that happens at the gadget. 
 * Some gadgets have actions - A response that a gadget can make to a trigger happening somewhere on the board. 
 */    
}

class Board implements Pingball{ 

     /**
      * Board Constructor  - Creates a 2D Array representing the board. 
      * @param width
      * @param height
      * 
      */
    
    private char [][] boardArray; 
    public Board(int width, int height){ 

    }
    

    
    
    /**
     * Returns string representation of board. 
     */
    @Override
    public String toString(){ 
      
    }
    
    /**
     * Updates the board with the ball and changes balls fields
     * @param ball Current ball
     * @param x position where the ball wants to go 
     * @param y position where the ball wants to go 
     * Call updateBall at the end with new ball properties. 
     */
    public void updateBoard(Ball ball, int x, int y){ 
        
    }
    
 
}

class Ball implements Pingball{ 
    private int velocity;  
    private int positionX; 
    private int positionY; 
    private double theta; 

    
    public Ball(String position){ 
        
    }
    
    /**
     * Calls update Board with correct parameters. ; 
     */
    public void step(){ 
        
    }
    
    /**
     * Updates fields of the ball
     * @param vel
     * @param posX
     * @param posY
     * @param theta
     */
    public void updateBall(int vel, int posX, int posY, double theta){ 
        
    }
}
