//package noapplet.example;
///**Class to control players turn and AI placements
// * @ author Jesus Oropeza & Dante Lopez
// * @ version 1 (2/26/2023)
// */
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Player {
//
//    static int boardSize = Game.getBoardSize();
//    public static char[][] map = Board.getMap();
//    public static Board board = Board.getBoard();
//    private static char player = 'X';     // Player 1 is always 'X'
//    private static char aiPlayer = 'O';  // AI or player 2 is always 'O'
//
//    public Player(char[][] map){
//        this.map = map;
//    }
//    public static void Human(){
//        boolean p1turn = false;
//        boolean winner = false;
//
//
//        Game.getRules();
//        while(!winner){
//            if(p1turn) {p1turn = false;}
//            else{p1turn = true;}
//            Game.getPlayerMove(p1turn);
//            winner = Game.determineWin(p1turn);
//        }
//        if(winner){Controller.Win(p1turn);}
//    }
//
//
//    public static void Strategy(){
//        boolean p1turn = false;
//        boolean winner = false;
//
//        Game.getRules();
//        while(!winner) {
//            // Toggle Player turn
//            if (p1turn) {p1turn = false;}
//            else {p1turn = true;}
//            if(p1turn) {
//                Game.getPlayerMove(true);
//            }
//            winner = Game.determineWin(p1turn);
//            if (!p1turn) {
//                int[] AIMoves = getNextMove();
//                int row = AIMoves[0];
//                int col = AIMoves[1];
//                Game.setAIMove(row, col);
//            }
//        }
//        if(winner){Controller.Win(p1turn);}
//    }
//    public static int[] getNextMove() {
//        List<int[]> moves = getAvailableMoves();
//        int maxScore = Integer.MIN_VALUE;
//        int[] nextMove = null;
//
//        for (int[] move : moves) {
//            int row = move[0];
//            int col = move[1];
//            int score = evaluateMove(row, col);
//
//            if (score >= maxScore) {
//                maxScore = score;
//                nextMove = move;
//            }
//        }
//        return nextMove;
//    }
//
//    private static List<int[]> getAvailableMoves() {
//        // Generates a list of all available empty spaces on the board
//        List<int[]> moves = new ArrayList<>();
//        for (int row = 0; row < board.length; row++) {
//            for (int col = 0; col < board[0].length; col++) {
//                if (board[row][col] == '.') {
//                    moves.add(new int[]{row, col});
//                }
//            }
//        }
//        return moves;
//    }
//    // Generates a score for each empty space to determine the best possible space to occupy
//    private static int evaluateMove(int row, int col) {
//        int score = 0;
//        // Check horizontal line
//        int count = 0;
//
//        for (int c = col - 4; c <= col + 4; c++) {
//            // Within board parameters
//            if (c >= 0 && c < board[0].length) {
//                // There's a nearby 'O' therefore increase count
//                if (board[row][c] == aiPlayer) {
//                    count++;
//                }
//                // There's a nearby 'X' therefore increase count
//                else if (board[row][c] == player) {
//                    count++;
//                }
//            }
//        }
//        score += Math.pow(2,count);
//        // Check vertical line
//        count = 0;
//        for (int r = row - 4; r <= row + 4; r++) {
//            if (r >= 0 && r < board.length) {
//                // There's a nearby 'O' therefore increase count
//                if (board[r][col] == aiPlayer) {
//                    count++;
//                }
//                // There's a nearby 'X' therefore increase count
//                else if (board[r][col] == player) {
//                    count++;
//                }
//            }
//        }
//        score += Math.pow(2,count);
//        // Check diagonal line (i.e. \ )
//        count = 0;
//        for (int i = -4; i <= 4; i++) {
//            int r = row + i;
//            int c = col + i;
//            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
//                // There's a nearby 'O' therefore increase count
//                if (board[r][c] == aiPlayer) {
//                    count++;
//                }
//                // There's a nearby 'X' therefore increase count
//                else if (board[r][c] == player) {
//                    count++;
//                }
//            }
//        }
//        score += Math.pow(2,count);
//        // Check anti-diagonal line (i.e. / )
//        count = 0;
//        for (int i = -4; i <= 4; i++) {
//            int r = row - i;
//            int c = col + i;
//            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
//                // There's a nearby 'O' therefore increase count
//                if (board[r][c] == aiPlayer) {
//                    count++;
//                }
//                // There's a nearby 'X' therefore increase count
//                else if (board[r][c] == player) {
//                    count++;
//                }
//            }
//        }
//        score += Math.pow(2,count);
//        return score;
//    }
//}
