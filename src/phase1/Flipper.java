package phase1;
import java.awt.Rectangle;

import physics.*;

/*
 * String Representation: | or --
 *                        |
 * Size and Shape: Rectangular rotating shape with bounding box 2Lx2L
 * 
 * Orientation: 
 * for left- 0degrees, for pivot point in northwest corner, counterclockwise
 * right   - pivot point in northeast corner, clockwise 
 * 
 * Coeff of Reflection: .95
 * Trigger - when ball hits it
 * Action-rotates 90 degrees, angular velocity of 1080; account for the linear 
 * velocity of the part that contacts the ball
 * 
 */
public class Flipper implements Gadget{
    private int xPos; 
    private int yPos;
    //Int type  representing left(0) or right flipper(1)
    private int flipperType; 
//    private final String [] typesOfFlipper = {"left", "right"} ;
    private boolean isHorizontal;
    private LineSegment lineSegment; 
    private Vect pivotPoint; 
    private Angle angle;
    /**
     * Constructor
     * @param x
     * @param y
     * @param type
     */
    public Flipper(int x, int y, int type){ 
        this.xPos =x;
        this.yPos = y;
        this.pivotPoint= new Vect(x,y);
        this.lineSegment= new LineSegment(x,y, x, y-1);
//        this.flipperType=typesOfFlipper[type];
        this.flipperType=type;
        this.angle= new Angle(3/2*Math.PI);
        this.isHorizontal=false;
    }
    /**
     * Triggering event that is proceeded by an action
     */
    public void trigger(){
        this.action();
       if(isHorizontal){
           isHorizontal=false;
       }
       else if(!isHorizontal){
           isHorizontal=true;
       }
    }
    /**
     * Method that returns a ball that has been hit by a flipper
     * @param ball
     * @return
     */
    public Ball hitBall(Ball ball){
        return new Ball(ball.getPosition(), Geometry.reflectRotatingWall(lineSegment,pivotPoint, 1080., ball, ball.getVelocity()));
    }

    /**
     * Switches the state of the flipper 
     */
    @Override
    public void action() {
        this.hitBall(ball);
        this.rotateGadget(32);
    }
    
    
    /**
     * Rotates flipper
     * @param degrees 0-360
     */
    @Override
    public void rotateGadget(int degrees){
        if(!isHorizontal){
            //left  --CounterClockwise 90
            if(this.flipperType ==0){
                    this.angle = new Angle(0);
            }
            //right  -- Clockwise 90
            if(this.flipperType ==1){
                   this.angle = new Angle(Math.PI);
            }
        }else if(isHorizontal){
            //left  -- Clockwise
            if(this.flipperType ==0){
                this.angle = new Angle(3/2*Math.PI);
            }
            //right  -- CounterClockwise
            if(this.flipperType ==1){
                this.angle = new Angle(3/2*Math.PI);
            }
        }
        
        
        Geometry.rotateAround( this.lineSegment, this.pivotPoint, this.angle);    
        
    }
    
    /**
     * Returns the position of the pivot
     */
    @Override
    public Vect getPosition() {
       return this.pivotPoint;
    }
    
    /**
     * Returns the next point that the Flipper will be at. 
     */
    @Override
    public Vect getNext() {
        return this.pivotPoint;

    }
    /**
     * Defines the action that is to be committed upon collision
     */
    @Override
    public void collision(Ball ball) {
        trigger();
    }
    
    @Override
    public void step() {
        //Empty
    }
    /**
     * Return true if the lineSegment contains the point
     */
    @Override
    public boolean contains(Vect position) {
       if(  lineSegment.p1().equals(position ) ||  lineSegment.p2().equals(position ) ) return true;
       return false;
    }
    
    /**
     * Returns a string representation of flipper
     */
    @Override
    public String toString(int width, int height) {
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        if(isHorizontal){
            wallArray[this.yPos][this.xPos] = '-';
            wallArray[this.yPos][this.xPos+1] = '-';
        }
        else if(!isHorizontal){
            wallArray[this.yPos][this.xPos] = '|';
            wallArray[this.yPos+1][this.xPos] ='|';
        }
        String boardToString = "";
        for(int i=0; i<wallArray.length;i++){
            for(int j=0; j< wallArray[0].length;j++){
            boardToString += Character.toString(wallArray[i][j]);
            }
            boardToString += "\n";
        }
        
        return boardToString;

    }
    

    
    
}
