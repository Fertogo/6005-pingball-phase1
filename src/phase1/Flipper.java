package phase1;

import java.awt.Rectangle;

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
    //String type  representing left(0) or right flipper(1)
    private String flipperType; 
    private final String [] typesOfFlipper = {"left", "right"} ;
    
    
    
    public Flipper(int x, int y, int type){ 
        this.xPos =x;
        this.yPos = y;
        this.flipperType=typesOfFlipper[type];
        
    }
    public void trigger(){
        if(collisionDetected()){
            action(this);
        }
    }
    private boolean collisionDetected(){
        return true; //TODO
    }
    @Override
    public void action(Gadget outerWalls) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        String stringBuilder="";
        stringBuilder += "x:"+this.xPos;
        stringBuilder += "y:"+this.yPos;
        stringBuilder += "Type: "+ this.flipperType;
        return stringBuilder;
        

    }
}
