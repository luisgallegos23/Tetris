import java.util.ArrayList;

public class TetrisModel {
    //Current Block that can be moved around and manipulated.
    private Tetrimino activeBlock;

    //List of Tetriminos that make up the entire board.
    private ArrayList<Tetrimino> Board;

    //List of Y values. Used to store lines that need to be cleared.
    private ArrayList<Double> lines;

    private int score; //added

    //Generates an object of TetrisModel.
    public TetrisModel(){
        Board = new ArrayList<>();
        lines = new ArrayList<>();
        loadNewPiece();
    }

    //Returns the arraylist of tetrimino object. Useful for generating a display of the current Board state.
    public ArrayList<Tetrimino> getBoard(){
        return Board;
    }

    //Creates a new piece of a random type, sets it as the activeBlock, and adds it to the Board.
    public void loadNewPiece(){
        activeBlock= new Tetrimino(StdRandom.uniform(1,8));
        Board.add(activeBlock);
    }

    //Shifts activeBlock right or left depending on inputted value.
    public void shiftPiece(int x){
        if (x > 0) {
            activeBlock.shiftRight(Board);
        }
        if (x < 0) {
            activeBlock.shiftLeft(Board);
        }
    }

    //Causes the activeBlock to fall 1 line.
    public void fall(){
        activeBlock.fall(Board);
    }

    //Rotates the activeBlock.
    public void rotate(){
        activeBlock.rotate(Board);
    }

    //Checks all available lines to see if any can be clear. If any can, they are added to the line ArrayList.
    public void checkLinesToClear(){
        for(double i = 1.95;i>=0.04;i-=0.1){
            if(isLineFull(i)){
                lines.add(i);
            }
        }
    }

    //Determines if a specific line is full.
    public boolean isLineFull(double y){
        int count=0;
        for(int i=0;i<Board.size();i++){
            count+=Board.get(i).hasBlocksAtYPosition(y);
        }
        if (count == 10) {
            score +=1;
            return true;
        }
        return false;
    }

    //Clears  lines that are completely filled with pieces.
    public void clearLines(){
        for(int i=0;i<lines.size();i++){
            for(int j = 0; j<Board.size();j++){
                Board.get(j).clear(lines.get(i));
            }
        }
        lines.clear();
    }

    //Removes empty pieces from the Board arraylist. Prevents buildup of empty pieces.
    public void deleteEmptyPieces(){
        for(int i=Board.size()-1;i>=0;i--){
            if(Board.get(i).isEmpty()){
                Board.remove(i);
            }
        }
    }

    //Checks if the current piece is done falling. If true, a new piece is loaded.
    public boolean fallStatusCheck(){
        if(activeBlock.hasHitFloor(Board)){
            loadNewPiece();
            return true;
        }
        return false;
    }

    //Checks to see if the starting position for the next piece is filled. Returns true if it is.
    public boolean gameOver(){
        if (score >= 10){
            return true;
        }
        return activeBlock.isStartingPositionFilled(Board);
    }

    public int Score(){
        return score;
    }
}

