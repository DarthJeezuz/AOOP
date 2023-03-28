package noapplet.example;
/**Class to draw board and validate placements of stones
 * @ author Jesus Oropeza & Dante Lopez
 * @ version 1 (2/26/2023)
 */
public class Board{
    static char[][] board;
    public Board(){}

    public static void initBoard(int size){
        /**
         * Make blank board of dots
         */
        board = new char[size][size];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){board[i][j] = '.';}
        }
    }
    public static void drawBoard(){
        for(int i = 0; i < board.length; i++){
            System.out.print(i + "|\t");
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print("  ");
        System.out.print("\t");
        for(int i = 0; i < board.length; i++){
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static boolean placeStone(int row, int col, boolean p1turn){
        /**
         * Validating appropriate move
         */
        if(row > board.length || col > board.length){
            return false;
        }
        if(board[row][col] == '.'){
            if(p1turn){
                board[row][col] = 'X';
                return true;
            }
            else{
                board[row][col] = 'O';
                return true;
            }
        }
        return false;
    }

    public static boolean check(boolean p1turn){
        return Place.CheckForWin(p1turn);
    }
    public static char[][] getBoard(){
        return board;
    }
    public static void declareDraw(){
        ConsoleUI.Draw();
    }
}
