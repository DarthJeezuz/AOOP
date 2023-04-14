//package noapplet.example;
//
//import static noapplet.example.Main.board;
//
///**Class to determine game mode and interact with board for Player class
// * @ author Jesus Oropeza & Dante Lopez
// * @ version 1 (2/26/2023)
// */
//public class Game {
//    public Game(int mode){
//        if(mode == 1){OmokGameplay.Human();/*Player.Human();*/}
//        else{OmokGameplay.Strategy();/*Player.Strategy();*/}
//    }
//    public static void getRules(){
//        Controller.Rules();
//    }
//    public static void getPlayerMove(boolean p1turn){
//        Controller.Play(p1turn);
//    }
//    public static boolean determineWin(boolean p1turn){
//
//        return Board.check(p1turn);
//    }
//    public static void setAIMove(int row, int col){
//        Board.placeStone(row,col,false);
//        }
//    public static int getBoardSize(){
//        return board.size();
//
//        }
//}
