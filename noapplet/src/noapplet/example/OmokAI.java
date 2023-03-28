package noapplet.example;
import java.util.ArrayList;
import java.util.List;

public class OmokAI {
    private int[][] board;
    private int player;
    private int aiPlayer;

    public OmokAI(int[][] board, int player) {
        this.board = board;
        this.player = player;
        this.aiPlayer = player == 1 ? 2 : 1; // determine the AI player
    }

    public int[] getNextMove() {
        List<int[]> moves = getAvailableMoves();
        int maxScore = Integer.MIN_VALUE;
        int[] nextMove = null;

        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];
            int score = evaluateMove(row, col);

            if (score > maxScore) {
                maxScore = score;
                nextMove = move;
            }
        }
        return nextMove;
    }

    private List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 0) {
                    moves.add(new int[]{row, col});
                }
            }
        }
        return moves;
    }

    private int evaluateMove(int row, int col) {
        int score = 0;

        // Check horizontal line
        int count = 0;
        for (int c = col - 4; c <= col + 4; c++) {
            if (c >= 0 && c < board[0].length) {
                if (board[row][c] == aiPlayer) {
                    count++;
                } else if (board[row][c] == player) {
                    count = 0;
                    break;
                }
            }
        }
        score += count * count;

        // Check vertical line
        count = 0;
        for (int r = row - 4; r <= row + 4; r++) {
            if (r >= 0 && r < board.length) {
                if (board[r][col] == aiPlayer) {
                    count++;
                } else if (board[r][col] == player) {
                    count = 0;
                    break;
                }
            }
        }
        score += count * count;

        // Check diagonal line
        count = 0;
        for (int i = -4; i <= 4; i++) {
            int r = row + i;
            int c = col + i;
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                if (board[r][c] == aiPlayer) {
                    count++;
                } else if (board[r][c] == player) {
                    count = 0;
                    break;
                }
            }
        }
        score += count * count;

        // Check anti-diagonal line
        count = 0;
        for (int i = -4; i <= 4; i++) {
            int r = row - i;
            int c = col + i;
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                if (board[r][c] == aiPlayer) {
                    count++;
                } else if (board[r][c] == player) {
                    count = 0;
                    break;
                }
            }
        }
        score += count * count;

        return score;
    }
}

