package noapplet.example;
/**Class to control the overall mechanics of the game Omok
 * @ author Jesus Oropeza & Dante Lopez
 * @ version 1 (2/26/2023)
 */
public class Controller {
    public Controller(){Intro();}

    public static void Intro(){
        int size = ConsoleUI.Greeting();
        Board.initBoard(size);
        int mode = ConsoleUI.GameMode();
        new Game(mode);
    }

    public static void Rules(){ConsoleUI.DisplayRules();}

    public static void Play(boolean p1turn){ConsoleUI.play(p1turn);}

    public static void Win(boolean p1turn){ConsoleUI.DisplayWin(p1turn);}
}