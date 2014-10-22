package phase1;

import java.awt.Rectangle;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * 
 * Represents a PingBall Square Bumper
 *
 */
public class SquareBumper implements Gadget {
    private Vect position;
    private Rectangle gadgetArea; 
    private LineSegment leftWall; 
    private LineSegment rightWall; 
    private LineSegment bottomWall; 
    private LineSegment topWall; 
    
    public SquareBumper(Vect position) {
        int x = (int)(position.x()); 
        int y = (int)(position.y()); 

        this.position = position;
        
        int posX = (int)(this.position.x());
        int posY = (int)(this.position.y());  
        this.gadgetArea = new Rectangle(posX, posY, posX+1, posY+1);
        
        //Create walls representing square bumper. 
        this.topWall = new LineSegment(x,y,x+1,y); 
        this.rightWall = new LineSegment(x+1,y,x+1,y+1); 
        this.bottomWall = new LineSegment(x,y+1,x+1,y+1); 
        this.leftWall = new LineSegment(x,y,x,y+1); 
    }

    @Override
    public void action() {
        //TODO: Implement this
    }

    @Override
    public void rotateGadget(int degrees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(int height, int width) {
        
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        wallArray[(int) this.position.y()][(int) this.position.x()] = '#';
        
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
    public Vect getPosition() {
        return this.position;
    }

    @Override
    public Vect getNext() {
        return this.position;
    }

    @Override
    public void collision(Ball ball) {
        System.out.println("Ball collision with Square Bumper"); 
        Vect currentBallVelocity = ball.getVelocity(); 
        Vect currentBallPosition = ball.getPosition(); 
        Vect newBallVelocity = new Vect(0,0); 
        double ballX = currentBallPosition.x(); 
        double ballY = currentBallPosition.y(); 
        //Check Corners
        if (ballX > this.position.x() + 1 && ballY > this.position.y()){ 
            //Hitting the top right corner of Bumper
            System.out.println("Hit TopRight corner of Square Bumper"); 

            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX < this.position.x() && ballY > this.position.y()){ 
            //Hitting the top left corner of Bumper
            System.out.println("Hit TopLeft corner of Square Bumper"); 
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX < this.position.x() && ballY > this.position.y()+1){ 
            //Hitting the bottom left corner of Bumper
            System.out.println("Hit BottomLeft corner of Square Bumper"); 
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX > this.position.x()+1 && ballY > this.position.y()+1){ 
            //Hitting the bottom right corner of Bumper
            System.out.println("Hit BottomRight corner of Square Bumper"); 
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        //Check which wall was hit
        else if(ballY < this.position.y()){ 
            //Hitting top wall
            System.out.println("Hit Top Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(topWall, currentBallVelocity);
        }
        else if(ballY > this.position.y()+1){ 
            //Hitting bottom wall
            System.out.println("Hit Bottom Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(bottomWall, currentBallVelocity);
        }
        else if(ballX > this.position.x()+1){ 
            //Hitting right wall
            System.out.println("Hit Right Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(rightWall, currentBallVelocity);
        }
        else if(ballX < this.position.x()){ 
            //Hitting left wall
            System.out.println("Hit Left Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(leftWall, currentBallVelocity);
        }
        
        System.out.println("New Ball Velocity" + newBallVelocity);
        ball.updateBall(currentBallPosition, newBallVelocity); 
    }

    @Override
    public void step() {
        
    }

    @Override
    public boolean contains(Vect position) {  
        return gadgetArea.contains(position.x(),position.y());
        //return this.position.equals(position); 
    }

}
