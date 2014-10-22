package phase1;
import physics.LineSegment;
import java.awt.Rectangle;
import physics.Geometry;
import java.awt.Point;


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
    private String flipperType; 
    private final String [] typesOfFlipper = {"left", "right"} ;
    private boolean isHorizontal;
    private LineSegment lineSegment; 
    private Point pivotPoint; 
    /**
     * Constructor
     * @param x
     * @param y
     * @param type
     */
    public Flipper(int x, int y, int type){ 
        this.xPos =x;
        this.yPos = y;
        this.pivotPoint= new Point(x,y);
        this.lineSegment= new LineSegment(x,y, x, y-1);
        this.flipperType=typesOfFlipper[type];
        this.isHorizontal=false;
    }
    /**
     * Triggering event that is proceeded by an action
     */
    public void trigger(){
        if(collisionDetected()){
            action(this);
        }
    }
    
    public Ball hitBall(Ball ball){
        return new Ball(ball.getPosition(), Geometry.reflectRotatingWall(lineSegment,pivotPoint, 1080., ball, ball.getVelocity()));
    }
    /**
     * Detects a collision
     * @return true if collision occurs
     */
    private boolean collisionDetected(){
        return true; //TODO
    }
    /**
     * Switches the state of the flipper 
     */
    @Override
    public void action() {
        
        
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
    /**
     * Rotates flipper
     * @param degrees 0-360
     */
    @Override
    public void rotateGadget(int degrees) {
        // TODO Auto-generated method stub
        
    }
    
    
    
}
