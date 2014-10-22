package phase1;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class TriangleBumper implements Gadget {
    private Vect position;
    private int orientation = 0;
    private LineSegment wall1; 
    private LineSegment wall2; 
    private LineSegment longWall; 
    
    private void checkRep(){ 
        System.out.println("Checking rep..."); 
        assert(longWall.length() > wall1.length()); 
        assert(longWall.length() > wall2.length()); 
        assert(this.orientation % 90 == 0); 
        assert(this.orientation <= 360); 
    }
    
    public TriangleBumper(Vect position) {
        this.position = position;      
        triangleDegree0(); 
        this.orientation= 0; 
        checkRep();
    }
    
    public TriangleBumper(Vect position, int orientation){ 
        this.position = position; 
        rotateGadget(orientation); 
        checkRep();
    }

    /**
     * Changes orientation of this to a triangle with an orientation of 0 degrees
     */
    private void triangleDegree0(){ 
        int x = (int)(this.position.x());
        int y = (int)(this.position.y()); 
        this.wall1 = new LineSegment(x,y,x+1,y); 
        this.wall2 = new LineSegment(x,y,x,y+1); 
        this.longWall = new LineSegment(x+1,y,x,y+1); 
    }
    
    /**
     * Changes orientation of this to a triangle with an orientation of 90 degrees
     */
    private void triangleDegree90(){ 
        int x = (int)(this.position.x());
        int y = (int)(this.position.y()); 
        this.wall1 = new LineSegment(x,y,x+1,y); 
        this.wall2 = new LineSegment(x+1,y,x+1,y+1); 
        this.longWall = new LineSegment(x,y,x+1,y+1); 
    }
    
    /**
     * Changes orientation of this to a triangle with an orientation of 180 degrees
     */
    private void triangleDegree180(){ 
        System.out.println("Rotating triangle to 180"); 
        int x = (int)(this.position.x());
        int y = (int)(this.position.y()); 
        this.wall1 = new LineSegment(x,y+1,x+1,y+1); 
        this.wall2 = new LineSegment(x+1,y,x+1,y+1); 
        this.longWall = new LineSegment(x,y+1,x+1,y); 
        System.out.println(longWall.toString()); 
    }
    
    /**
     * Changes orientation of this to a triangle with an orientation of 270 degrees
     */
    private void triangleDegree270(){ 
        int x = (int)(this.position.x());
        int y = (int)(this.position.y()); 
        this.wall1 = new LineSegment(x,y,x,y+1); 
        this.wall2 = new LineSegment(x,y+1,x+1,y+1); 
        this.longWall = new LineSegment(x,y,x+1,y+1);
    }
    
    @Override
    public void action() {
        // TODO Auto-generated method stub

    }

    @Override
    public void rotateGadget(int degrees) {
        int newOrientation = (this.orientation+degrees) %360; 
        switch(newOrientation){ 
        case(0): triangleDegree0(); break; 
        case(90): triangleDegree90(); break;
        case(180): triangleDegree180(); break;
        case(270): triangleDegree270(); break;
        }
        this.orientation = newOrientation; 
        System.out.println(this.orientation); 
        checkRep(); 
        System.out.println(longWall.toString()); 

   
    }

    @Override
    public String toString(int height, int width) {
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        if(this.orientation == 0 || this.orientation == 180){
            wallArray[(int) this.position.y()][(int) this.position.x()] = '/';
        }else{
            wallArray[(int) this.position.y()][(int) this.position.x()] = '\\';
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
    public Vect getPosition() {
        return this.position;
    }

    @Override
    public Vect getNext() {
        this.step();
        return this.position;
    }

    @Override
    public void collision(Ball ball) {
        System.out.println("Collision with a TriangleBumper!"); 
        Vect currentBallVelocity = ball.getVelocity(); 
        Vect currentBallPosition = ball.getPosition(); 
        Vect newBallVelocity = new Vect(0,0); 
        double ballX = currentBallPosition.x(); 
        double ballY = currentBallPosition.y(); 
        
        //System.out.println(currentBallPosition.toString());
        //Check Corners
        if (ballX > this.position.x() + 1 && ballY > this.position.y()){ 
            //Hitting the top right corner of Bumper
            //System.out.println("TOP-RIGHT-CORNER");

            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX < this.position.x() && ballY > this.position.y()){ 
            //Hitting the top left corner of Bumper
            //System.out.println("TOP-LEFT-CORNER");
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX < this.position.x() && ballY > this.position.y()+1){ 
            //Hitting the bottom left corner of Bumper
            //System.out.println("BOTTOM-LEFT-CORNER");
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX > this.position.x()+1 && ballY > this.position.y()+1){ 
            //Hitting the bottom right corner of Bumper
            //System.out.println("BOTTOM=RIGHT-CORNER");
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        //Check which wall was hit
        else if(ballY < this.position.y() && ballX >= this.position.x() && ballX <= this.position.x()+1 ){ 
            //Hitting top wall
            //System.out.println("TOP"); 
            //System.out.println(longWall.toString()) ;
            switch(this.orientation){ 
           
            case(0):  newBallVelocity = Geometry.reflectWall(wall1, currentBallVelocity); break;
            case(90): newBallVelocity = Geometry.reflectWall(wall1, currentBallVelocity); break;
            case(180): newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); System.out.println(newBallVelocity); break;
            case(270): newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); break;
            
            }
           
        }
        else if(ballY >= this.position.y()+1){ 
            //Hitting bottom wall
            //System.out.println("BOTTOM");
            switch(this.orientation){ 

            case(0):  newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); break;
            case(90): newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); break;
            case(180): newBallVelocity = Geometry.reflectWall(wall1, currentBallVelocity); break;
            case(270): newBallVelocity = Geometry.reflectWall(wall2, currentBallVelocity); break;
            
            }
        }
        else if(ballX >= this.position.x()+1){ 
            //Hitting right wall
            //System.out.println("RIGHT");
            //System.out.println(longWall.toString()); 
            switch(this.orientation){ 

            case(0):  newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); break;
            case(90): newBallVelocity = Geometry.reflectWall(wall2, currentBallVelocity); break;
            case(180): newBallVelocity = Geometry.reflectWall(wall2, currentBallVelocity); break;
            case(270): newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); break;
            
            }        }
        else if(ballX <= this.position.x()){ 
            //Hitting left wall
            //System.out.println("LEFT");
            switch(this.orientation){ 
            
            case(0):  newBallVelocity = Geometry.reflectWall(wall2, currentBallVelocity); break;
            case(90): newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); break;
            case(180): newBallVelocity = Geometry.reflectWall(longWall, currentBallVelocity); break;
            case(270): newBallVelocity = Geometry.reflectWall(wall1, currentBallVelocity); break;
            
            }  
   
        }
        else{
            System.err.println("ERROR: Detected Collision, but can't figure out which side ball hit!"); 
        }
        
//        System.out.println("Old Ball Velocity: " + ball.getVelocity().toString());
//        System.out.println("New Ball Velocity: " + newBallVelocity.toString());

        ball.updateBall(currentBallPosition, newBallVelocity); 
    }

    @Override
    public void step() {
        
    }

    @Override
    public boolean contains(Vect position) {
        return this.position.equals(position); 
    }

}
