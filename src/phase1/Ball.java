package phase1;

import physics.Geometry;
import physics.Vect;

public class Ball implements Gadget {
    
    private Vect position;
    private Vect velocity;
    private double gravity;
    private long time = System.currentTimeMillis();
    private long timeDiff;
    
    public Ball(Vect position, Vect velocity) {
        this.position = position;
        this.velocity = velocity;
        this.gravity = 25; 
    }
    
    public Ball(Vect position, Vect velocity, double gravity) {
        this.position = position;
        this.velocity = velocity;
        this.gravity = gravity;
    }
    
    public Ball(Vect position){ 
        this.position = position; 
        this.velocity = new Vect(0,0); 
    }

    @Override
    public void action() {

    }

    @Override
    public void rotateGadget(int degrees) {

    }

    @Override
    public String toString(int width, int height) {
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        if(this.position.y()>19){
            this.position = new Vect(this.position.x(), 19);
        }else if(this.position.y()<0){
            this.position = new Vect(this.position.x(), 0);
        }
        if(this.position.x()>19){
            this.position = new Vect(19,this.position.y());
        }else if(this.position.x()<0){
            this.position = new Vect(0, this.position.y());
        }
        wallArray[(int) Math.round(this.position.y())][(int) Math.round(this.position.x())] = '*';
        
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
    public void collision(Ball ball) {
        Vect center = new Vect(.5, .5);
        Vect centerBall1 = ball.getPosition().plus(center);
        Vect centerBall2 = this.getPosition().plus(center);
        int massBall1 = 1, massBall2 = 1;
        Geometry.VectPair ballCollision = Geometry.reflectBalls(centerBall1, massBall1 , ball.getVelocity()
                ,centerBall2 , massBall2, this.getVelocity());
        ball.updateBall(ball.getPosition(), ballCollision.v1);
        
    }
    public void updateBall(Vect position, Vect velocity) {
        this.position = position;
        this.velocity = velocity;
        
    }
    
    public void step(){
        Vect gravity = new Vect(0, this.gravity*(this.timeDiff));
        Vect delta = new Vect(velocity.plus(gravity).angle().cos(), velocity.plus(gravity).angle().sin());
        this.position = this.position.plus(delta);
    }

    public Vect getPosition(){
        return this.position;
    }
    

    public Vect getVelocity(){
        Vect gravity = new Vect(0, this.gravity*(this.timeDiff));
        return this.velocity.plus(gravity);
    }

    
    @Override
    public Vect getNext() {
        this.timeDiff = System.currentTimeMillis() - this.time;
        Vect gravity = new Vect(0, this.gravity*(this.timeDiff));
        Vect delta = new Vect(velocity.plus(gravity).angle().cos(), velocity.plus(gravity).angle().sin());
        return this.position.plus(delta);

    }

    @Override
    public boolean contains(Vect pos) {
        // TODO Auto-generated method stub
        return false;
    }

   
}
