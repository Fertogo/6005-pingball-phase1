package phase1; 
import java.awt.Rectangle;

import physics.*; 

/*
 * A Gadget: 
 * Every gadget has an (x,y) location, where x and y are integers in [0,19].
 * Every gadget has a width and height, also integers in [0,19]Others are configurable
 * Some gadgets have a coefficient of reflection, this is a multiplier applied to the ball's 
 *      velocity after it bounces off the gadget. Default multiplier is 1.0. 
 * Some gadgets have an orientation - how the gadget is rotated from its default orientation. 
 *      This is given in degrees (clockwise). Default orientation is 0. 
 * Some gadgets have triggers - an event that happens at the gadget. 
 * Some gadgets have actions - A response that a gadget can make to a trigger happening somewhere on the board. 
 */   
public interface Gadget {

    /**
     * absorber stops ball and holds it. 
     * if not holding the ball or if previously rejected ball hos not left the absorber, 
     * then the absorber takes no action when triggered.   
     * @param x: x Position on the board
     * @param y: y Position on the board
     * @param size:  A rectangle kL *mL where k and m are 0<k<=20
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
    public static Gadget squareBumper(Vect position){ 
        return new SquareBumper(position); 
    }
    
    /**
     * Creates a Triangle Bumper object on the board
     * @param x: x Position on the board
     * @param y: y Position on the board
     * @return Gadget representing a Circle Bumper
     */

    public static Gadget triangleBumper(Vect position){
        return new TriangleBumper(position); 
    }
    
    /**
     *Creates a Circle Bumper object on the board
     * @param x: x Position on the board
     * @param y: y Position on the board
     * @return Gadget representing a Circle Bumper
     */
    public static Gadget circleBumper(Vect position){ 
        return new CircleBumper(position); 
    }
    
    /**
     * Flipper Flips when triggered
     * it does so in a swivel motion. 
     * @param x
     * @param y
     * @param type- int type  representing left(0) or right flipper(1)
     * @return Flipper gadget
     */

    public static Gadget flipper(int x, int y, int type){ 
        return new Flipper(x,  y,  type); 

    }
    

    /**
     * Creates a ball
     * @param x: x position of the ball 
     * @param y: y position of the ball
     * @param velocity: initial velocity of the ball
     * @return A ball
     */
    public static Gadget ball(Vect position, Vect velocity, double gravity){
        return new Ball(position, velocity, gravity);
    }
    /**
     * Called when a gadget is triggered
     * 
     */
    public void action(); 
    
    /**
     * Changes the orientation of a gadget
     * @param degrees: the clockwise orientation in degrees of the object. 
     *                 Must be 0 degrees (the default orientation), 90 degrees, 
     *                 180 degrees, or 270 degrees.
     */
    public void rotateGadget(int degrees);
    
    /**
     * @param height: height of board
     * @param width: width of the board
     * @return: String representation of the individual Gadget in the board. 
     */
    public String toString(int width, int height);

    /**
     * 
     * @return Vector representing the position of the object
     */
    public Vect getPosition();

     /**
      * 
      * @return where the gadget wants to be in the next time step. 
      */
    public Vect getNext();

    /**
     * Adjusts a ball after a collision. 
     * @param ball: ball that is about to collide with gadget. 
     */
    public void collision(Ball ball);

    /**
     * Updates gadget after one timestep
     */
    public void step(); 
    
    /**
     * 
     * @param position: position to check in Gadget
     * @return True if gadget contains p, that is, a ball at p would collide with the gadget. 
     */
    public boolean contains(Vect position); 

    
}
