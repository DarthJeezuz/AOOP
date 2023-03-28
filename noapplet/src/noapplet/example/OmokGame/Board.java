package noapplet.example.OmokGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction of an Omok board, which consists of n x n intersections
 * or places where players can place their stones. The board can be
 * accessed using a pair of 0-based indices (x, y), where x and y
 * denote the column and row number, respectively. The top-left
 * intersection is represented by the indices (0, 0), and the
 * bottom-right intersection is represented by the indices (n-1, n-1).
 */
public class Board {

    char[][] board;
    int size;
    /** Create a new board of the default size. */
    public Board() {
    }

    /** Create a new board of the specified size. */
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){board[i][j] = '.';}
        }
    }

    /** Return the size of this board. */
    public int size() {
        return size;
    }

    /** Removes all the stones placed on the board, effectively
     * resetting the board to its original state.
     */
    public void clear() {
        new Board(size);
    }

    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
        int cnt = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Place a stone for the specified player at a specified
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
        if(y > board.length || x > board.length){
            // location does not exist
            System.out.println("That is not a valid coordinate");
        }
        /** Place Stone */
        if(isEmpty(x,y)){
            board[x][y] = player.Pstone();
        }
    }

    /**
     * Return a boolean value indicating whether the specified
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isEmpty(int x, int y) {
        if(board[x][y] == '.'){
            return true;
        }
        return false;
    }

    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
        if(isEmpty(x,y)){
            return false;
        }
        return true;
    }

    /**
     * Return a boolean value indicating whether the specified
     * intersection (x, y) on the board is occupied by the given
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
        if(isOccupied(x,y)){
            /**  */
            if(board[x][y] == player.Pstone()){
                return true;
            }
        }
        return false;
    }

    /**
     * Return the player who occupies the specified intersection (x, y)
     * on the board. If the place is empty, this method returns null.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {
        if(isEmpty(x,y)){
            return null;
        }
        return Player.getPlayer();
    }

    /**
     * Return a boolean value indicating whether the given player
     * has a winning row on the board. A winning row is a consecutive
     * sequence of five or more stones placed by the same player in
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
        int vertCount = 0;
        int horizCount = 0;
        boolean consecutiveV = false;
        boolean consecutiveH = false;
        char ch = player.Pstone();
        char W = '#'; // char to highlight winning line

        List<Integer> HIlst = new ArrayList<>(); // horizontal list for x values
        List<Integer> HJlst = new ArrayList<>(); // horizontal list for y values
        List<Integer> VIlst = new ArrayList<>(); // vertical list for x values
        List<Integer> VJlst = new ArrayList<>(); // vertical list for y values
        /**
         * Horizontal & Vertical counts should reset if ch is not consecutive
         */
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++) {
                // Checking for available moves
                if(board[i][j] == '.'){

                }
                // check horizontal
                if (board[i][j] == ch){
                    horizCount++;
                    consecutiveH = true;
                    HIlst.add(i);
                    HJlst.add(j);
                    if(horizCount == 5){
                        for(int k = 0; k < HIlst.size(); k++){
                            board[HIlst.get(k)][HJlst.get(k)] = W;
                        }
                        return true;
                    }
                }
                else{
                    HIlst.clear();
                    HJlst.clear();
                    consecutiveH = false;
                    horizCount = 0;
                }
                // check vertical
                if(board[j][i] == ch){
                    vertCount++;
                    consecutiveV = true;
                    VIlst.add(i);
                    VJlst.add(j);
                    if(vertCount == 5){
                        for(int k = 0; k < VIlst.size(); k++){
                            board[VJlst.get(k)][VIlst.get(k)] = W;
                        }
                        return true;
                    }
                }
                else{
                    VIlst.clear();
                    VJlst.clear();
                    consecutiveV = false;
                    vertCount = 0;
                }
            }
        }


        /**The two following diagonal checks, simultaneously view five spaces
         * that could possibly yield a diagonal win.
         * check diagonal from bottom-left to top-right (i.e. / )
         */
        for(int i = 4; i < board.length; i++){
            for(int j = 0; j < board.length-4; j++){
//                For troubleshooting
//                System.out.println("/ y:" + i + " x:" + j + "\ty:" + (i-1) + " x:" + (j+1) + "\ty:" + (i-2) + " x:" + (j+2) + "\ty:" + (i-3) + " x:" + (j+3) + "\ty:" + (i-4) + " x:" + (j+4));
                if(board[i][j] == ch && board[i-1][j+1] == ch && board[i-2][j+2] == ch && board[i-3][j+3] == ch && board[i-4][j+4] == ch){
                    board[i][j] = W;
                    board[i-1][j+1] = W;
                    board[i-2][j+2] = W;
                    board[i-3][j+3] = W;
                    board[i-4][j+4] = W;
                    return true;
                }
            }
        }
        /**
         * check diagonal from top-left to bottom-right (i.e. \ )
         */
        for(int i = 0; i < board.length - 4; i++){
            for(int j = 0; j < board.length - 4; j++){
//                For troubleshooting
//                System.out.println("\\ y:" + i + " x:" + j + "\ty:" + (i+1) + " x:" + (j+1) + "\ty:" + (i+2) + " x:" + (j+2) + "\ty:" + (i+3) + " x:" + (j+3) + "\ty:" + (i+4) + " x:" + (j+4));
                if(board[i][j] == ch && board[i+1][j+1] == ch && board[i+2][j+2] == ch && board[i+3][j+3] == ch && board[i+4][j+4] == ch){
                    board[i][j] = W;
                    board[i+1][j+1] = W;
                    board[i+2][j+2] = W;
                    board[i+3][j+3] = W;
                    board[i+4][j+4] = W;
                    return true;
                }
            }
        }
        return false;

    }

    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
    public Iterable<Place> winningRow() {
        return null;
    }

    /**
     * An intersection on an Omok board identified by its 0-based column
     * index (x) and row index (y). The indices determine the position
     * of a place on the board, with (0, 0) denoting the top-left
     * corner and (n-1, n-1) denoting the bottom-right corner,
     * where n is the size of the board.
     */
    public static class Place {
        /** 0-based column index of this place. */
        public final int x;

        /** 0-based row index of this place. */
        public final int y;

        /** Create a new place of the given indices.
         *
         * @param x 0-based column (vertical) index
         * @param y 0-based row (horizontal) index
         */
        public Place(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // other methods if needed ...
    }
}

/**
 * A player in an Omok game. It holds the name of the player and
 * can be used to identify a specific player throughout the game.
 * The Player class helps to keep track of the moves made by each
 * player during the game.
 */
class Player {

    boolean p1Set = false;
    /** Name of this player. */
    private String name = null;
    private char stone;
    static Player player;
    public Player(String name, Character stone){
        this.name = name;
        this.stone = stone;
    }
    /** Create a new player with the given name. */
    public Player(String name) {
        this.name = name;
        if(!p1Set){
            stone = 'X';
            p1Set = true;
        }
        stone = 'O';
    }
    public static Player getPlayer(){
        return player;
    }
    /** Return the name of this player. */
    public String name(){
        return name;
    }
    /** Return the players char (stone) */
    public Character Pstone(){
        return stone;
    }
}
