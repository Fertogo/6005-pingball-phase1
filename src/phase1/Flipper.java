package phase1;

public class Flipper implements Gadget{
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
    public Flipper(int x, int y, int type){ 
        
    }

    @Override
    public void action(Gadget outerWalls) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString(Gadget OuterWalls) {
        // TODO Auto-generated method stub
        return null;
    }
}