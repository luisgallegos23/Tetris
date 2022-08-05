import java.util.ArrayList;

public class Tetris {
    public static void main(String[] args){new Tetris().run();}

    private TetrisModel modal;

    //Draw the board of the game
    public void draw(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        //DRAWS board
        drawGrid(modal.getBoard());
        drawBoard();
        StdDraw.show();
        StdDraw.pause(10);
    }
    //Draws the individual tetriminos with each type having a different color
    public void drawGrid(ArrayList<Tetrimino> Board){
        StdDraw.setPenColor(StdDraw.WHITE);
        for (Tetrimino tetrimino : Board) {
            if(tetrimino.getType()==1){
                StdDraw.setPenColor(StdDraw.RED);
            }else if(tetrimino.getType()==2){
                StdDraw.setPenColor(StdDraw.BLUE);
            }else if(tetrimino.getType()==3){
                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
            }else if(tetrimino.getType()==4){
                StdDraw.setPenColor(StdDraw.GREEN);
            }else if(tetrimino.getType()==5){
                StdDraw.setPenColor(StdDraw.BOOK_RED);
            }else if(tetrimino.getType()==6){
                StdDraw.setPenColor(StdDraw.MAGENTA);
            }else if(tetrimino.getType()==7){
                StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            }
            for (Extent c : tetrimino.getBlocks()) {
                if (c != null){
                    StdDraw.filledSquare(c.getX(), c.getY(), c.getRadius());}

            }
        }
    }

    //Draws grids to make the movement easier to recognize
    public void drawBoard(){
        Extent[][] Grid = new Extent[10][20];
        double x = -0.05;
        for (int i = 0; i < 10; i++) {
            x += 0.1;
            double y = 0.05;
            for (int j = 0; j < 20; j++) {
                Grid[i][j] = new Extent(x, y, .05);
                y += .10;
            }}//StdOut.print(Grid);

        for(int Row=0;Row < 10;Row++){
            for(int Col = 0; Col < 20;Col++){
                StdDraw.setPenColor(StdDraw.WHITE);
                Extent S = Grid[Row][Col];
                StdDraw.square(S.getX(),S.getY(), S.getRadius());

            }
        }StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.1, 1.85,"Score:" + modal.Score());

    }

    //Controls the movement of the individual tetriminos
    public void handleKeyPresses(){
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_I)){
            modal.rotate();
            StdDraw.pause(100);
        }if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_J)){
            modal.shiftPiece(-10);
            StdDraw.pause(100);
        }if(StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_L)){
            modal.shiftPiece(40);
            StdDraw.pause(100);
        }if(StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_K)){
            modal.fall();
            StdDraw.pause(100);
        }
    }

    //runs the game
    public void run(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(360, 720);
        StdDraw.setYscale(0.0,2.0);
        titleScreen();
        while(true){
            boolean Game = false;
            modal = new TetrisModel();
            while(!Game){
                draw();
                handleKeyPresses();
                if(modal.fallStatusCheck())
                {
                    modal.checkLinesToClear();
                    modal.clearLines();
                    Game = modal.gameOver();
                }
            }
            draw();
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.pause(30);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(0.5,1.1,"GAME OVER");
            StdDraw.text(0.5,1.0,"Press SPACE to play Again");
            StdDraw.show();
            while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
                // Wait for spacebar
            }
        }

    }



    //Creates the titles screan where it states the controls
    public void titleScreen(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 1.0, "TETRIS");
        StdDraw.text(0.5, 0.8, "Controls:");
        StdDraw.text(0.5, 0.7, "Use I/J/K/L for movement");
        StdDraw.text(0.5, 0.6, "Press SPACE to start.");
        StdDraw.show();
        while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
            // Wait for spacebar
        }
    }
}
