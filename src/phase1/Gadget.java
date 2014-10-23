package phase1; 

import java.util.List;

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
    public static Gadget absorber(Vect position, int width, int height){ 
        return new Absorber(position, width, height); 
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

    public static Gadget flipper(Vect position, int type){ 
        return new Flipper(position, type); 

    }
    

    /**
     * Creates a ball
     * @param x: x position of the ball 
     * @param y: y position of the ball
     * @param velocity: initial velocity of the ball
     * @return A ball
     */
    public static Gadget ball(Vect position, Vect velocity){
        return new Ball(position, velocity);
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
    public Vect getNext(double time);

    /**
     * Adjusts a ball after a collision. 
     * @param ball: ball that is about to collide with gadget. 
     */
    public void collision(Ball ball);

    
    
    /**
     * 
     * @param ball: ball that us about to collide with gadget
     * @return the time until collision with ball
     */
    public double timeToCollision(Ball ball);

    public static char [][] getArray(int height,int width){
        char [][] wallArray = new char[height][width];

        //Populate with space
        for (int x = 0; x<width; x++){ 
            for (int y = 0; y<height; y++){ 
                wallArray[x][y] = ' '; 
            }
        }
        //Draw the walls
        //Top wall
        for (int i=0; i<width; i++){ 
            wallArray[i][0] = '.'; 
        }
        //Bottom wall
        for (int i=0; i<width; i++){ 
            wallArray[i][height-1] = '.';
        }
        //Left wall
        for (int y=0; y<width; y++){ 
            wallArray[0][y] = '.'; 
        }
        //Right wall
        for (int y=0; y<width; y++){ 
            wallArray[width-1][y] = '.'; 
        }
        return wallArray;
    }
    
    /**
     * Adds a gadget to Gadget's gadgets to be triggered
     * @param triggeredGadget: Gadget to be added to triggered gadgets
     */
    public void addTriggeredGadget(Gadget triggeredGadget);

    /**
     * Called when the Gadget is triggered
     * Calls action of Gadgets that Gadget it supposed to Trigger. 
     */
    void trigger();

}
