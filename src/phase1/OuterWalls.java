package phase1;
import physics.*;

public class OuterWalls {
    private LineSegment leftWall;
    private LineSegment rightWall;
    private LineSegment topWall;
    private LineSegment bottomWall;
    private int height; 
    private int width; 
    
    private void checkRep(){
        //TODO: Check that all of the walls are connected
    }
    
    public OuterWalls(int width, int height){ 
        leftWall = new LineSegment(0,0,0,height-1); 
        rightWall = new LineSegment(width-1,0,width-1,height-1); 
        topWall = new LineSegment(0,0,width-1,0); 
        bottomWall = new LineSegment(0,height-1, width-1,height-1);
        this.height = height; 
        this.width = width; 
        checkRep(); 
    }
    
    public void collision(Ball ball, int wall){ //0:Top 1:Right 2:Bottom 3:Left
        System.out.println("Hit a wall!"); 
        System.out.println(ball.getPosition()); 
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
        ball.updateBall(position,  newVelocity);

        
    }
    public int getHeight(){ 
        return this.height; 
    }
    
    public int getWidth(){ 
        return this.width; 
    }
   
    public String toString(int width, int height) {

        char [][] wallArray; 
        System.out.println(height + " " + width); 
        wallArray = new char[height][width];
      //Populate with space

        for (int x = 0; x<width; x++){ 
            System.out.println("x: " + x); 
            for (int y = 0; y<height; y++){ 
                //System.out.println(wallArray[x]);
                wallArray[x][y] = ' '; 
                //System.out.println("   y:" + y); 

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
