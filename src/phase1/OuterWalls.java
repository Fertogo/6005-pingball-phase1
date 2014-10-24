package phase1;
import physics.*;

/**
 * Represents the OuterWalls (playing area) of the board. 
 * Define L to be the basic distance unit, equal to the edge length of a square bumper. 
 * Corresponding to standard usage in the graphics community, the origin is in the 
 * upper left-hand corner with coordinates increasing to the right and down.
 * 
 *
 */
public class OuterWalls {
    private LineSegment leftWall;
    private LineSegment rightWall;
    private LineSegment topWall;
    private LineSegment bottomWall;
    private int height; 
    private int width; 
    
    private void checkRep(){
        assert(leftWall.p1() == topWall.p1());
        assert(rightWall.p2() == bottomWall.p2());
        assert(topWall.p2() == rightWall.p1());
        assert(bottomWall.p1() == leftWall.p2());
    }
    
    /**
     * Outer Walls constructor 
     * @param width: width of the playing board 
     * @param height: height of the playing board
     */
    public OuterWalls(int width, int height){ 
        leftWall = new LineSegment(0,0,0,height-1); 
        rightWall = new LineSegment(width-1,0,width-1,height-1); 
        topWall = new LineSegment(0,0,width-1,0); 
        bottomWall = new LineSegment(0,height-1, width-1,height-1);
        this.height = height; 
        this.width = width; 
        checkRep(); 
    }
    
    /**
     * Called when a ball collies with an outer wall. 
     * @param ball: Ball that collied with an outer Wall
     * @param wall: int representing which wall was hit. //0:Top 1:Right 2:Bottom 3:Left
     */
    public void collision(Ball ball, int wall){ 

        Vect oldVelocity = ball.getVelocity(); 
        Vect newVelocity = null; 
        switch(wall){ 
        case(0): 
           //Collision with Top Wall
            newVelocity = Geometry.reflectWall(topWall, oldVelocity);
            break; 
        case(1):
          //Collision with Right Wall
            newVelocity = Geometry.reflectWall(rightWall, oldVelocity);
            break;
        case(2):
            //Collision with Bottom Wall
            newVelocity = Geometry.reflectWall(bottomWall, oldVelocity);
            break;
        case(3):
           //Collision with Left Wall
           newVelocity = Geometry.reflectWall(leftWall, oldVelocity);
            break;   
        }
        
        Vect position = ball.getPosition(); 
        ball.updateBall(position, newVelocity);
   
    }
    
    /**
     * 
     * @return height of the outer walls
     */
    public int getHeight(){ 
        return this.height; 
    }
    
    /**
     * 
     * @return height of the outer walls
     */
    public int getWidth(){ 
        return this.width; 
    }
   
    /**
     * 
     * @param width
     * @param height
     * @return: a string representation of a playing area with specified width and height
     */
    public String toString(int width, int height) {

        char [][] wallArray; 

        wallArray = new char[height][width];
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
        
        //Convert 2D Array to String
        String board = "";

        for(int i=0; i<wallArray.length;i++){
            for(int j=0; j< wallArray[0].length;j++){
            board += Character.toString(wallArray[i][j]);
            }
            board += "\n";
        }

     return board;
    }
 
}
