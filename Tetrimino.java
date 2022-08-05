import java.util.ArrayList;

public class Tetrimino {

    //Determines which tetrimino is generated
    private int type;
    //Handles 4 extent blocks that make up the tetrimino
    private Extent[] blocks;
    //dictates what position of rotation the tetrimino is in.
    private int rotation;

    //intializes the tetrimino to 1 of 7 types. Square, T, L, J, Z, S or I
    public Tetrimino(int type){
        this.type = type;
        rotation = 1;
        blocks = new Extent[4];
        blocks[0] = new Extent(0.45,1.95,.05);
        blocks[1] = new Extent(0.55,1.95,0.05);
        blocks[2] = new Extent(0.45,1.85,0.05);
        blocks[3] = new Extent(0.55,1.85,0.05);
        if(type==2){
            blocks[0] = new Extent(0.45,1.95,.05);
            blocks[1] = new Extent(0.35,1.85,0.05);
            blocks[2] = new Extent(0.45,1.85,0.05);
            blocks[3] = new Extent(0.55,1.85,0.05);
            //  Extent[] blocks = {new Extent(0.45,.95,.05),new Extent(0.35,0.85,0.05),new Extent(0.45,0.85,0.05),new Extent(0.55,0.85,0.05)};
        }
        if(type==3){
            blocks[0] = new Extent(0.45,1.95,.05);
            blocks[1] = new Extent(0.45,1.75,0.05);
            blocks[2] = new Extent(0.45,1.85,0.05);
            blocks[3] = new Extent(0.55,1.75,0.05);
            // Extent[] blocks = {new Extent(0.45,.95,.05),new Extent(0.45,0.75,0.05),new Extent(0.45,0.85,0.05),new Extent(0.55,0.75,0.05)};
        }
        if(type==4){
            blocks[0] = new Extent(0.45,1.95,.05);
            blocks[1] = new Extent(0.45,1.75,0.05);
            blocks[2] = new Extent(0.45,1.85,0.05);
            blocks[3] = new Extent(0.35,1.75,0.05);
            //  Extent[] blocks = {new Extent(0.45,.95,.05),new Extent(0.45,0.75,0.05),new Extent(0.45,0.85,0.05),new Extent(0.35,0.75,0.05)};
        }
        if(type==5){
            blocks[0] = new Extent(0.45,1.95,.05);
            blocks[1] = new Extent(0.35,1.95,0.05);
            blocks[2] = new Extent(0.45,1.85,0.05);
            blocks[3] = new Extent(0.55,1.85,0.05);
            // Extent[] blocks = {new Extent(0.45,.95,.05),new Extent(0.35,0.95,0.05),new Extent(0.45,0.85,0.05),new Extent(0.55,0.85,0.05)};
        }
        if(type==6){
            blocks[0] = new Extent(0.45,1.95,.05);
            blocks[1] = new Extent(0.35,1.85,0.05);
            blocks[2] = new Extent(0.45,1.85,0.05);
            blocks[3] = new Extent(0.55,1.95,0.05);
            // Extent[] blocks = {new Extent(0.45,.95,.05),new Extent(0.35,0.85,0.05),new Extent(0.45,0.85,0.05),new Extent(0.55,0.95,0.05)};
        }
        if(type==7){
            blocks[0] = new Extent(0.45,1.95,.05);
            blocks[1] = new Extent(0.45,1.85,0.05);
            blocks[2] = new Extent(0.45,1.75,0.05);
            blocks[3] = new Extent(0.45,1.65,0.05);
            //  Extent[] blocks = {new Extent(0.45,.95,.05),new Extent(0.45,0.85,0.05),new Extent(0.45,0.75,0.05),new Extent(0.45,0.65,0.05)};
        }
    }

    //Moves the tetrimino right in the grid. Breaks if blocks exist to the right.
    public void shiftRight(ArrayList<Tetrimino> activeBoard){
        for(int i=0; i<activeBoard.size();i++){
            for(int j=0;j<blocks.length;j++){
                if(activeBoard.get(i).getBlocks()!=blocks&&activeBoard.get(i).hasBlockAtCoordinate(blocks[j].getX()+.1,blocks[j].getY())){
                    return;
                }
            }
        }
        for(int i=0;i<blocks.length;i++){
            if(blocks[i].getX()>=0.9){
                return;
            }
        }
        for(int i=0;i<blocks.length;i++){
            blocks[i].move(.1,0);
        }
    }

    //Moves the tetrimino left in the grid. Breaks if blocks exist to the left.
    public void shiftLeft(ArrayList<Tetrimino> activeBoard){
        for(int i=0; i<activeBoard.size();i++){
            for(int j=0;j<blocks.length;j++){
                if(activeBoard.get(i).getBlocks()!=blocks&&activeBoard.get(i).hasBlockAtCoordinate(blocks[j].getX()-.1,blocks[j].getY())){
                    return;
                }
            }
        }
        for(int i=0;i<blocks.length;i++){
            if(blocks[i].getX()<=0.1){
                return;
            }
        }
        for(int i=0;i<blocks.length;i++){
            blocks[i].move(-0.1,0);
        }
    }

    //Causes the tetrimino to fall on the grid. Breaks if blocks are below it.
    public void fall(ArrayList<Tetrimino> activeBoard){
        for(int i=0; i<activeBoard.size();i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (activeBoard.get(i).getBlocks() != blocks && activeBoard.get(i).hasBlockAtCoordinate(blocks[j].getX(), blocks[j].getY() - .1)) {
                    return;
                }
            }
        }
        for(int i=0;i<blocks.length;i++){
            if(blocks[i].getY()<=0.1){
                return;
            }
        }
        for(int i=0;i<blocks.length;i++){
            blocks[i].move(0,-0.1);
        }
    }

    //returns the array of extent blocks.
    public Extent[] getBlocks() {
        return blocks;
    }

    //Returns the type of the Tetrimino.
    public int getType(){
        return type;
    }

    //Detects if the tetrimino has a block or blocks at a current place in the grid. returns the number of blocks that are. Used to detect if a line needs to be cleared.
    public int hasBlocksAtYPosition(double y){
        int count =0;
        for(int i=0;i<blocks.length;i++){
            if(blocks[i]!=null&&blocks[i].getY()>=y-0.01&&blocks[i].getY()<=y+0.01){
                count++;
            }
        }
        return count;
    }

    //Detects if the tetrimino has a block at a specific position. Used for preventing movement of tetrimino if it would collide with another one's blocks.
    public boolean hasBlockAtCoordinate(double x, double y){
        for(int i=0;i<blocks.length;i++){
            if(blocks[i]!=null&&blocks[i].getX()<=x+0.01&&blocks[i].getX()>=x-0.01&&blocks[i].getY()>=y-0.01&&blocks[i].getY()<=y+0.01){
                return true;
            }
        }
        return false;
    }

    //Clears blocks at a specific Y value on the grid and moves block above it down. Used for clearing lines.
    public void clear(double y){
        for(int i=0; i<blocks.length;i++){
            if(blocks[i]!=null&&blocks[i].getY()>=y-0.01&&blocks[i].getY()<=y+0.01){
                blocks[i]=null;
            }
            if(blocks[i]!=null&&blocks[i].getY()>y){
                blocks[i].move(0,-.1);
            }
        }
    }

    public boolean isEmpty(){
        for(int i=0;i<blocks.length;i++){
            if(blocks[i]!=null){
                return false;
            }
        }
        return true;
    }

    //checks to see if any blocks on the board occupy the starting position of where blocks spawn in.
    public boolean isStartingPositionFilled(ArrayList<Tetrimino> activeBoard){
        for(int i=0; i<activeBoard.size();i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (activeBoard.get(i).getBlocks() != blocks && activeBoard.get(i).hasBlockAtCoordinate(blocks[j].getX(), blocks[j].getY())) {
                    return true;
                }
            }
        }
        if(hasBlocksAtYPosition(0.05)>0){
            return true;
        }
        return false;
    }

    public boolean hasHitFloor(ArrayList<Tetrimino> activeBoard){
        for(int i=0; i<activeBoard.size();i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (activeBoard.get(i).getBlocks() != blocks && activeBoard.get(i).hasBlockAtCoordinate(blocks[j].getX(), blocks[j].getY()-.1)) {
                    return true;
                }
            }
        }
        for(int i=0;i<blocks.length;i++){
            if(blocks[i].getY()<=0.1){
                return true;
            }
        }
        return false;
    }

    //Rotates the tetriminio Clockwise
    public void rotate(ArrayList<Tetrimino> activeBoard){
        if(type==2){

            if(rotation==1){
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()-0.1,blocks[0].getY())||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()-.1,blocks[0].getY()+.1))){
                        return;
                    }
                }
                blocks[2].move(-.1,.1);
                blocks[3].move(-.2,.2);
                rotation = 2;
            }

            else if(rotation==2){
                if(blocks[0].getX()+0.1>1){
                    return;
                }
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX(),blocks[0].getY()+.1)||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()+.1,blocks[0].getY()+.1))){
                        return;
                    }
                }
                blocks[2].move(0.1,0.1);
                blocks[1].move(0.2,0.2);
                rotation = 3;
            }

            else if(rotation == 3){
                if (blocks[0].getY() - .1 < 0) {
                    return;
                }
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()+.1,blocks[0].getY())||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()+.1,blocks[0].getY()-.1))){
                        return;
                    }
                }
                blocks[2].move(0.1,-0.1);
                blocks[3].move(0.2,-0.2);
                rotation=4;
            }

            else if(rotation == 4){
                if(blocks[0].getX()-0.1<0){
                    return;
                }
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX(),blocks[0].getY()-0.1)||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()-.1,blocks[0].getY()-.1))){
                        return;
                    }
                }
                blocks[2].move(-0.1,-0.1);
                blocks[1].move(-0.2,-0.2);
                rotation=1;
            }
        }

        if (type == 3) {

            if (rotation == 1) {
                if (blocks[2].getX() - 0.1 < 0) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() - 0.1, blocks[2].getY()) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() - .1, blocks[2].getY() - .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() + .1, blocks[2].getY()))) {
                        return;
                    }
                }
                blocks[0].move(0.1, -0.1);
                blocks[1].move(-0.1, 0.1);
                blocks[3].move(-0.2, 0);
                rotation=2;
            }

            else if (rotation == 2) {
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()+0.1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() - .1, blocks[2].getY() + .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()-0.1))) {
                        return;
                    }
                }
                blocks[0].move(-0.1,0.1);
                blocks[1].move(0,0.1);
                blocks[3].move(0.1,0);
                rotation=3;
            }

            else if (rotation == 3) {
                if (blocks[2].getX() + 0.1 > 1) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()+0.1, blocks[2].getY()) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() + .1, blocks[2].getY() + .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()-0.1, blocks[2].getY()))) {
                        return;
                    }
                }
                blocks[0].move(0.1,0);
                blocks[1].move(0,-0.1);
                blocks[3].move(0.1,0.1);
                rotation=4;
            }

            else if (rotation == 4) {
                if (blocks[2].getY() - 0.1 < 0) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()-0.1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() + .1, blocks[2].getY() - .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()+0.1))) {
                        return;
                    }
                }
                blocks[0].move(-0.1,0);
                blocks[1].move(0.1,-0.1);
                blocks[3].move(0,-0.1);
                rotation=1;
            }
        }

        if (type == 4) {

            if (rotation == 1) {
                if (blocks[2].getX() + 0.1 > 1) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()+0.1, blocks[2].getY()) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() - .1, blocks[2].getY() + .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()-0.1, blocks[2].getY()))) {
                        return;
                    }
                }
                blocks[0].move(-0.1,0);
                blocks[1].move(0.1,0.1);
                blocks[3].move(0,0.1);
                rotation=2;
            }

            else if (rotation == 2) {
                if (blocks[2].getY() - 0.1 < 0) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()+0.1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() + .1, blocks[2].getY() + .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()-0.1))) {
                        return;
                    }
                }
                blocks[0].move(0.1,0);
                blocks[1].move(0,0.1);
                blocks[3].move(0.1,-0.1);
                rotation=3;
            }

            else if (rotation == 3) {
                if (blocks[2].getX() - 0.1 < 0) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()-0.1, blocks[2].getY()) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() + .1, blocks[2].getY() - .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()+0.1, blocks[2].getY()))) {
                        return;
                    }
                }
                blocks[0].move(-0.1,-0.1);
                blocks[1].move(0,-0.1);
                blocks[3].move(0.1,0);
                rotation=4;
            }

            else if (rotation ==  4) {
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()+0.1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() - .1, blocks[2].getY() - .1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()-0.1))) {
                        return;
                    }
                }
                blocks[0].move(0.1,0.1);
                blocks[1].move(-0.1,-0.1);
                blocks[3].move(-0.2,0);
                rotation=1;

            }
        }

        if (type == 5) {
            if (rotation == 1) {
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()+0.1,blocks[0].getY())||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()+.1,blocks[0].getY()+.1))){
                        return;
                    }
                }
                blocks[1].move(0.2,0.1);
                blocks[3].move(0,0.1);
                rotation=2;
            }

            else if(rotation == 2){
                if (blocks[0].getX() - 0.1 < 0) {
                    return;
                }
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()-0.1,blocks[0].getY())||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()+.1,blocks[0].getY()-.1))){
                        return;
                    }
                }
                blocks[1].move(-0.2,-0.1);
                blocks[3].move(0,-0.1);
                rotation=1;
            }
        }

        if (type == 6) {
            if (rotation == 1) {
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX(),blocks[0].getY()+0.1)||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()+.1,blocks[0].getY()-.1))){
                        return;
                    }
                }
                blocks[1].move(0.1,0.2);
                blocks[2].move(0.1,0);
                rotation = 2;
            }

            else if(rotation == 2){
                if (blocks[0].getX() - 0.1 < 0) {
                    return;
                }
                for(int i=0; i<activeBoard.size();i++) {
                    if(activeBoard.get(i).getBlocks()!=blocks&&(activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX(),blocks[0].getY()-0.1)||activeBoard.get(i).hasBlockAtCoordinate(blocks[0].getX()-.1,blocks[0].getY()-.1))){
                        return;
                    }
                }
                blocks[1].move(-0.1,-0.2);
                blocks[2].move(-0.1,0);
                rotation = 1;
            }
        }

        if (type == 7) {
            if (rotation == 1) {
                if (blocks[2].getX() - 0.1 < 0 || blocks[2].getX() - 0.2 < 0 || blocks[2].getX() + 0.1 > 1) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()+0.1, blocks[2].getY()) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX() - .2, blocks[2].getY()) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX()+0.1, blocks[2].getY()))) {
                        return;
                    }
                }
                blocks[0].move(-0.2,-0.2);
                blocks[1].move(-0.1,-0.1);
                blocks[3].move(0.1,0.1);
                rotation=2;
            }

            else if(rotation == 2){
                if (blocks[2].getY() - 0.1 < 0) {
                    return;
                }
                for (int i = 0; i < activeBoard.size(); i++) {
                    if (activeBoard.get(i).getBlocks() != blocks && (activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()+0.1) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()+0.2) || activeBoard.get(i).hasBlockAtCoordinate(blocks[2].getX(), blocks[2].getY()-0.1))) {
                        return;
                    }
                }
                blocks[0].move(0.2,0.2);
                blocks[1].move(0.1,0.1);
                blocks[3].move(-0.1,-0.1);
                rotation=1;
            }
        }
    }
}


