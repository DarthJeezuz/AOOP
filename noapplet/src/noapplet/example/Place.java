package noapplet.example;
/**Class to check for an unbroken line of five
 * @ author Jesus Oropeza & Dante Lopez
 * @ version 1 (2/26/2023)
 */
import java.util.ArrayList;
import java.util.List;

public class Place extends Board{
    private static int vertCount = 0;
    private static int horizCount = 0;
    private static boolean consecutiveV = false;
    private static boolean consecutiveH = false;

    Place(){}
    static boolean CheckForWin(boolean p1turn){
        char ch;
        char W = '#';
        if(p1turn){ch = 'X';}
        else{ch = 'O';}
        int count = 0;
        List<Integer> HIlst = new ArrayList<>();
        List<Integer> HJlst = new ArrayList<>();
        List<Integer> VIlst = new ArrayList<>();
        List<Integer> VJlst = new ArrayList<>();
        /**
         * Horizontal & Vertical counts should reset if ch is not consecutive
         */
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++) {
                // Checking for available moves
                if(board[i][j] == '.'){
                    count++;
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
        if(count == 0){Board.declareDraw();}

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
}
