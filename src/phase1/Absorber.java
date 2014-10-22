package phase1;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Rectangle;

import physics.Vect;
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
    private int width;
    private int height;
    private int ballsStored;
    private Rectangle rectangle;
    private Point positionPoint; 
    /**
     * 
     * @param x-xPosition
     * @param y-yPosition
     * @param size - General Size of the absorber. 
     */
    public Absorber(int x, int y, int width, int height ){ 
        this.xPos =x;
        this.yPos = y;
        this.width=width;
        this.height=height;
        this.rectangle= new Rectangle(x, y, width, height);
    }
    public Absorber(int x, int y, Rectangle size) {
        // TODO Auto-generated constructor stub
    }
    /**
     * Occurs when a ball hits it
     */
    public void trigger(){
//        if(collisionDetected()){
//            action(this);
//        }
    }
    /**
     * Detects collsions
     * @return true if collision
     */
    private void collision(){
        this.ballsStored+=1;
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
    public String toString(int boardWidth, int boardHeight) {
        Board board = new Board(boardWidth, boardHeight);
        char [][] wallArray = board.getArray();
        for(int i=0; i< this.width;i++){
//            stringBuilder+="=";
            wallArray[this.yPos][this.xPos+i] = '=';
        }
//        for(int i=0; i<height;i++){
//            stringBuilder+="\n";    
//        }
        for(int i=0; i< width;i++){
//            stringBuilder+="=";
            wallArray[this.yPos+this.height][this.xPos+i] = '=';
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
    @Override
    public void rotateGadget(int degrees) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Vect getPosition() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Vect getNext() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void collision(Ball ball) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void step() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean contains(Vect pos) {
        // TODO Auto-generated method stub
        return false;
    }
    
    
    
    
}
    
