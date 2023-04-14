package noapplet.example;

import org.javatuples.Triplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BoardPanel extends Main {
    ClickListener listener;

    public ArrayList<Triplet> stones = new ArrayList<Triplet>();
//    public Triplet<Integer, Integer, Boolean> stone;
    public char[][] map;
    static boolean p1turn = true;
    boolean humanMode;
    boolean mouseBound;
    ArrayList<Integer> move = new ArrayList();
    boolean activeBoard = false;
    int mausX = 0;
    int mausY = 0;
    int squareLen = 20;
    ArrayList<ClickListener> cliqs = new ArrayList<>();


//    public BoardPanel(){new noapplet.example.Board();}
    public BoardPanel(Board board){
        this.board = board;
        Timer timer = new Timer(0, (e -> repaint()));
        timer.start();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    public void paintComponent(Graphics g){
        Dimension d = getSize();
        g.setColor(new Color(255, 161, 0));
        g.fillRect(0, 0, d.width, d.height);
        g.setFont(new Font("San-serif", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        drawBoard(g,d);
        drawStone(g,d);
        drawGhostStone(g,d);
        //draw stones



//        g.fillOval(150, 150, 20, 20);
//        g.setColor(Color.red);
//        g.fillOval(170,130, 20, 20);
//        g.setColor(Color.blue);
//        g.drawString("Jesus Oropeza", 60, 40);

    }
    public void drawBoard(Graphics g, Dimension d){
        int down = 0;
        int right = 0;
        for(int i = 16; i > 0; i--){
            down += 20;
            right += 20;
            g.drawLine(0, right, d.width, down);
            g.drawLine(right, 0, down, d.height);
        }
    }

    public void drawGhostStone(Graphics g, Dimension d){
        if(!activeBoard && mouseBound && mausX < squareLen * d.width + (squareLen/2) && mausY < squareLen * d.width + (squareLen/2)){
            int tempX = ((mausX - (squareLen/2))/squareLen) * squareLen;
            int tempY = ((mausY - (squareLen/2))/squareLen) * squareLen;
            g.setColor(Color.BLACK);
            g.drawOval(tempX + (squareLen/2), tempY + (squareLen/2), squareLen, squareLen);
        }
    }
    public void drawStone(Graphics g, Dimension d){
//        if(!activeBoard && mouseBound && mausX < squareLen * d.width + (squareLen/2) && mausY < squareLen * d.width + (squareLen/2)){
        if(stones.size() > 0){
            for(var triplet : stones){
                int tempX = (int) triplet.getValue0();
                int tempY = (int) triplet.getValue1();
                boolean bool = (boolean) triplet.getValue2();
                if(bool){
                    g.setColor(Color.WHITE);
                }
                else{
                    g.setColor(Color.BLACK);
                }
                g.fillOval(tempX + (squareLen/2), tempY + (squareLen/2), squareLen, squareLen);

            }
        }


//        }
    }

    protected final MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {mouseBound = true;}
        @Override
        public void mouseExited(MouseEvent e){mouseBound = false;}
        @Override
        public void mouseMoved(MouseEvent e){
            mausX = e.getX();
            mausY = e.getY();
//            int tempX = ((mausX - (squareLen/2))/squareLen);
//            int tempY = ((mausY - (squareLen/2))/squareLen);
//            tempX = Math.min(tempX, board.size() - 1);
//            tempY = Math.min(tempY, board.size() - 1);
//            System.out.println("tempX: " + tempX + " tempY: " + tempY);
//            System.out.println("mausX: " + mausX + " mausY: " + mausY);
        }

        public void mouseClicked(MouseEvent e){
            System.out.println("MouseClick!!!");

            int tempX = ((mausX - (squareLen/2))/squareLen);
            int tempY = ((mausY - (squareLen/2))/squareLen);
            tempX = Math.min(tempX, board.size() - 1);
            tempY = Math.min(tempY, board.size() - 1);
            boolean valid = Board.validate(tempX,tempY,p1turn);
            if(valid){
                move.add(tempX);
                move.add(tempY);
                Triplet<Integer, Integer, Boolean> stone = new Triplet<>(tempX, tempY, p1turn);
                stones.add(stone);
                if(p1turn) {p1turn = false;}
                else{p1turn = true;}

            }
            else{
                System.out.println("THAT IS NOT A VALID SELECTION");
            }
            for(int i = 0; i < cliqs.size();i++){
                cliqs.get(i).placeClicked(tempX, tempY);
            }
        }
    };
    public ArrayList getPlayerMove(){
        return move;
    }
    interface ClickListener{
        void placeClicked(int x, int y);
    }
}
