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
     * Creates an Absorber object in the board 
     * @param x: x Position on the board
     * @param y: y Position on the baord
     * @param size:  A rectangle kL *mL where k and m are 0<k<=20
     * 
     */
    public static Gadget absorber(int x, int y, Rectangle size){ 
        return new Absorber(x,  y, size); 
    }
    
    /**
     * Creates a Square Bumper object on the board
     * @param x: x Position on the board
     * @param y: y Position on the board
     * @return Gadget representing a Square Bumper
     */
    public static Gadget squareBumper(int x, int y){ 
        return new SquareBumper(x, y); 
    }
    
    /**
     * Creates a Triangle Bumper object on the board
     * @param x: x Position on the board
     * @param y: y Position on the board
     * @return Gadget representing a Circle Bumper
     */
    public static Gadget triangleBumper(int x, int y){
        return new TriangleBumper(x, y); 
    }
    
    /**
     *Creates a Circle Bumper object on the board
     * @param x: x Position on the board
     * @param y: y Position on the board
     * @return Gadget representing a Circle Bumper
     */
    public static Gadget circleBumper(int x, int y){ 
        return new CircleBumper(x, y); 
    }
    
    /**
     *Creates a Flipper object in the board 
     * @param x: x Position on the board
     * @param y: y Position on the board
     * @param type: int representing left(0) or right flipper(1)
     * 
     */
    public static Gadget flipper(int x, int y, int type){ 
        return new Flipper(x, y, type); 
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
     * Changes the orientation of a gadget
     * @param degrees: the clockwise orientation in degrees of the object. 
     *                 Must be 0 degrees (the default orientation), 90 degrees, 
     *                 180 degrees, or 270 degrees.
     */
    public void rotateGadget(int degrees);
    
    /**
     * 
     * @return: String representation of the individual Gadget
     */
    @Override
    public String toString(Gadget OuterWalls); 
    

    
}
