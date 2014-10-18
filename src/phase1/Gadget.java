package phase1; 
import java.awt.Rectangle;

import physics.*; 
/**
 * 
 * TODO: Specs for the interface
 *
 */
public interface Gadget {

    
    /**
     * TODO: Write specs
     * @param x
     * @param y
     * @param orientation
     * @param size
     * @return
     */
    public static Gadget absorber(int x, int y, double orientation,Rectangle size){ 
        return new Absorber(x,  y,  orientation,size); 
    }
    
    /**
     * TODO: Write specs
     * @param x
     * @param y
     * @return
     */
    public static Gadget squareBumper(int x, int y){ 
        return new SquareBumper(x,  y, size); 
    }
    
    /**
     * TODO: Write specs
     * @param x
     * @param y
     * @param orientation
     * @return
     */
    public static Gadget triangleBumper(int x, int y, double orientation){ //TODO: Size and shape
        return new TriangleBumper(x,  y, size); 
    }
    
    /**
     * TODO: Write specs
     * @param x
     * @param y
     * @return
     */
    public static Gadget circleBumper(int x, int y){ 
        return new CircleBumper(x,  y, size); 
    }
    
    /**
     * TODO: Write specs
     * @param type (left or right)
     * @return
     */
    public static Gadget flipper(int x, int y, String type){ 
        return new Flipper(x,  y, size); 
    }
    
    /**
     * Creates the board's outerWalls
     * @param width - Desired width of the board
     * @param height - Desired height of the wall
     * @return - Gadget representing the outerWalls of the board. 
     */
    public static Gadget outerWalls(int width, int height){ 
        return new OuterWalls(width, height); 
    }
    
    
    
    
    /**
     * TODO: Write specs
     */
    public void action(Gadget outerWalls); 
    
    /**
     * 
     * @return: String representation of the individual Gadget
     */
    @Override
    public String toString(Gadget OuterWalls); 
    

    
}
