package phase1;

public class SquareBumper implements Gadget {
    private int xPosition;
    private int yPosition;
    
    public SquareBumper(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub

    }

    @Override
    public void rotateGadget(int degrees) {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString(int height, int width) {
        
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        wallArray[this.yPosition][this.xPosition] = '#';
        
        String boardToString = "";
        for(int i=0; i<wallArray.length;i++){
            for(int j=0; j< wallArray[0].length;j++){
            boardToString += Character.toString(wallArray[i][j]);
            }
            boardToString += "\n";
        }
        
        return boardToString;
    }

}
