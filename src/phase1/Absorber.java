package phase1;

import java.awt.Rectangle;
/*
 * String Representation: =
 * Size and Shape: A rectangle kL *mL where k and m are 0<k<=20
 * Orientation: Only one kind
 * Coef of Reflection: None, ball captured
 * Trigger: generated when the ball hits it 
 * Action: shoots out store ball
 * Description: absorber stops ball and holds it. Ball's center is .25L from bottom .25 from the right side,
 * Initial velocity of the ball should be 50L/sec with default gravity and default values for friction
 * if not holding the ball or if previously rejected ball hos not left the absorber, 
 * then the absorber takes no action when triggered.   
 */
public class Absorber implements Gadget {
    private int xPos; 
    private int yPos;
    private Rectangle absSize; 
    
    public Absorber(int x, int y, Rectangle size){ 
        this.xPos =x;
        this.yPos = y;
        this.absSize=size; 
    }

    @Override
    public void action(Gadget outerWalls) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString(Gadget OuterWalls) {
        String stringBuilder="";
        stringBuilder += "x:"+this.xPos;
        stringBuilder += "y:"+this.yPos;
        stringBuilder += "S: "+ this.absSize;
        return stringBuilder;
    }
    
    
    
    
}
    
