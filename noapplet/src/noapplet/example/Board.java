package noapplet.example;

/**Class to draw board and validate placements of stones
 * @ author Jesus Oropeza & Dante Lopez
 * @ version 1 (2/26/2023)
 */
public class Board{
    private static char[][] map;
    private static Board board;
    public Board(){}
    static int size = 15;
    public static void initBoard(){
        /**
         * Make blank board of dots
         */
        map = new char[size][size];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++){map[i][j] = '.';}
        }
    }
    public static void drawBoard(){
        for(int i = 0; i < map.length; i++){
            System.out.print(i + "|\t");
            for(int j = 0; j < map.length; j++){
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print("  ");
        System.out.print("\t");
        for(int i = 0; i < map.length; i++){
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static boolean validate(int row, int col, boolean p1turn){
        /**
         * Validating coordinates & that the space is empty
         */
        if(row > map.length || col > map.length){
            return false;
        }
        if(map[row][col] == '.'){
            return true;
        }
        return false;
    }
    public static void placeStones(int row, int col, boolean p1turn){
        if(p1turn){
                map[row][col] = 'X';
        }
        else{
                map[row][col] = 'O';
        }
    }

    public static boolean check(boolean p1turn){return Check.CheckForWin(p1turn);}
    public static Board getBoard(){return board;}
    public static char[][] getMap(){return map;}
    public static void declareDraw(){/*ConsoleUI.Draw();*/}
    public static int size(){return size();}
}
