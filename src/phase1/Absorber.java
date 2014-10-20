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
    /**
     * 
     * @param x-xPosition
     * @param y-yPosition
     * @param size - General Size of the absorber. 
     */
    public Absorber(int x, int y, Rectangle size){ 
        this.xPos =x;
        this.yPos = y;
        this.absSize=size; 
    }
    /**
     * Occurs when a ball hits it
     */
    public void trigger(){
        if(collisionDetected()){
            action(this);
        }
    }
    /**
     * Detects collsions
     * @return true if collision
     */
    private boolean collisionDetected(){
        return true; //TODO
    }
    /**
     * Store ball in one iteration and shoots it back in the next
     * 
     */
    @Override
    public void action() {
        // TODO Auto-generated method stub
        
    }
    /**
     * return Absorber representation
     */
    @Override
    public String toString(int width, int height) {
        String stringBuilder="";
        for(int i=0; i< width;i++){
            stringBuilder+="=";
        }
        for(int i=0; i<height;i++){
            stringBuilder+="\n";    
        }
        for(int i=0; i< width;i++){
            stringBuilder+="=";
        }
        return stringBuilder;
    }
    
    
    
    
}
    
