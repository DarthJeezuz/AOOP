package noapplet.example;

import noapplet.NoApplet;

import javax.swing.*;
import java.awt.*;

public class ScrollingBanner extends NoApplet {
    protected String text;
    protected Font font = new java.awt.Font("Sans-serif", Font.BOLD, 24);
    protected Dimension dim;
    protected int x, y;
    protected int delay = 1; // milliseconds
    protected int offset = 1;
    protected Timer timer; // animation timer

    public ScrollingBanner(String[] params) { super(params);}

    public void init(){
        //get parameters delay and text
        String att = getParameter("delay");
        if(att != null){
            delay = Integer.parseInt(att);
        }
        att = getParameter("text");
        if(att != null){
            text = att;
        }
        else{
            text = "Go Miners!";
        }
        // set the initial position of the text
        dim = getSize();
        x = dim.width;
        y = font.getSize();
        // initialize the animation timer
        timer = new Timer(delay, e -> repaint());
    }
    protected void paintComponent(Graphics g){
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int length = fm.stringWidth(text);

        // adjust the position of the text from the previous frame
        x = x - offset;
        // if the text is completely off to the left end
        // move the position back to the right end
        if (x < -length) {
            x = dim.width;
        }
        // set the pen color and draw the backround
        g.setColor(Color.BLACK);
        g.fillRect(0,0, dim.width, dim.height);

        // set the pen color and draw the text
        g.setColor(Color.GREEN);
        g.drawString(text, x, y);
    }
    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }

    public static void main(String[] args){
        new ScrollingBanner(args).run();
    }
}
