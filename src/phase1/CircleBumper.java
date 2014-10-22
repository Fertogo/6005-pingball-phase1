package phase1;

import physics.Circle;
import physics.Geometry;
import physics.Vect;

public class CircleBumper implements Gadget {
    private Vect position;
    private Circle circle; 
    
    public CircleBumper(Vect position) {
        this.position = position;
        int diameter = 1; 
        this.circle = new Circle(position.x(), position.y(), diameter/2); 
    }

    @Override
    public void action() {
    }

    @Override
    public void rotateGadget(int degrees) {
        //Not applicable
    }

    @Override
    public String toString(int height, int width) {
        
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        wallArray[(int) this.position.y()][(int) this.position.x()] = 'O';
        
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
        System.out.println("Collision with a CircleBumper"); 
        Vect newBallVelocity = Geometry.reflectCircle(circle.getCenter(), ball.getPosition(), ball.getVelocity());
        ball.updateBall(ball.getPosition(), newBallVelocity);
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
    public void step() {
    }

    @Override
    public boolean contains(Vect position) {
        return this.position.equals(position); 
    }

}
