package phase1;

import physics.Geometry;
import physics.Vect;

public class Ball implements Gadget {
    
    private Vect position;
    private Vect velocity;
    public Ball(Vect position, Vect velocity) {
        this.position = position;
        this.velocity = velocity;
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
        wallArray[(int) this.position.y()][(int) this.position.x()] = '*';
        
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
        Vect delta = new Vect(Math.round(position.angle().cos()), Math.round(position.angle().sin()));
        System.out.println(delta);
        this.position = this.position.plus(delta);
    }

    public Vect getPosition(){
        return this.position;
    }
    

    public Vect getVelocity(){
        return this.velocity;
    }

    @Override
    public Vect getNext() {
        Vect delta = new Vect(Math.round(position.angle().cos()), Math.round(position.angle().sin()));
        return this.position.plus(delta);
    }

   
}
