package phase1;

import physics.Vect;

public class CircleBumper implements Gadget {
    private Vect position;
    
    public CircleBumper(Vect position) {
        this.position = position;
    }

    @Override
    public void action() {
    }

    @Override
    public void rotateGadget(int degrees) {
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
        // TODO Auto-generated method stub
        
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

}
