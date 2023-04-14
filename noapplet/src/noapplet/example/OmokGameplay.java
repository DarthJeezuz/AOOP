package noapplet.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OmokGameplay extends BoardPanel{
    char OpponentSymbol = 'O';
    char playerSymbol = 'X';
    static boolean winner = false;

    ArrayList pMove = new ArrayList<>();
    public OmokGameplay(Board board) {super(board);}
    public void start(int mode){
        System.out.println("We have entered OmokGameplay.start()");
        if(mode == 1){
            new Thread(this::Human).start();
        }
        else{
            new Thread(this::Strategy).start();
        }
    }

    public void Human(){

        Main instance = new Main();
        JLabel turn = instance.getLabel();
        if(p1turn){
            turn.setText("Player 1 turn");
        }
        else{
            turn.setText("Player 2 turn");
        }
        // wait for player input

//            while(pMove.isEmpty()){
        System.out.println("we've entered while loop to catch player move");
        pMove = getPlayerMove();

//            }
        Board.placeStones(move.get(0), move.get(1), p1turn);
        pMove.clear();




    }

    public void Strategy(){
        while(!winner){
            //get player move

            // get Computer Move

            // check for win
        }
    }
    private int[] getHardMove() {
        int[] move = minimax(3, OpponentSymbol);
        return move;
    }

    private int[] minimax(int depth, char symbol) {
        List<int[]> availableMoves = getAvailableMoves();

        int bestRow = -1;
        int bestCol = -1;
        int bestScore = (symbol == OpponentSymbol) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        if (availableMoves.isEmpty() || depth == 0) {
            bestScore = evaluate();
        } else {
            for (int[] move : availableMoves) {
                int row = move[0];
                int col = move[1];

                map[row][col] = symbol;

                if (symbol == OpponentSymbol) {
                    int score = minimax(depth - 1, playerSymbol)[2];
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
                else {
                    int score = minimax(depth - 1, OpponentSymbol)[2];
                    if (score < bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }

                map[row][col] = '.';
            }
        }

        return new int[] {bestRow, bestCol, bestScore};
    }

    private List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '.') {
                    moves.add(new int[] {i, j});
                }
            }
        }
        return moves;
    }

    private int evaluate() {
        int score = 0;

        // Check rows
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j <= board.size() - 5; j++) {
                int count = 0;
                for (int k = j; k < j + 5; k++) {
                    if (map[i][k] == OpponentSymbol) {
                        count++;
                    } else if (map[i][k] == playerSymbol) {
                        count = 0;
                        break;
                    }
                }
                score += count * count;
            }
        }

        // Check columns
        for (int j = 0; j < map[0].length; j++) {
            for (int i = 0; i <= map.length - 5; i++) {
                int count = 0;
                for (int k = i; k < i + 5; k++) {
                    if (map[k][j] == OpponentSymbol) {
                        count++;
                    } else if (map[k][j] == playerSymbol) {
                        count = 0;
                        break;
                    }
                }
                score += count * count;
            }
        }

        // Check diagonals
        for (int i = 0; i <= map.length - 5; i++) {
            for (int j = 0; j <= map.length - 5; j++) {
                int count1 = 0;
                int count2 = 0;
                for (int k = 0; k < 5; k++) {
                    if (map[i+k][j+k] == OpponentSymbol) {
                        count1++;
                    } else if (map[i+k][j+k] == playerSymbol) {
                        count1 = 0;
                        break;
                    }
                    if (map[i+4-k][j+k] == OpponentSymbol) {
                        count2++;
                    } else if (map[i+4-k][j+k] == playerSymbol) {
                        count2 = 0;
                        break;
                    }
                }
                score += count1 * count1 + count2 * count2;
            }
        }

        return score;
    }


}
