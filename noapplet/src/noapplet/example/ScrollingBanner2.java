package noapplet.example;

import noapplet.NoApplet;

import javax.swing.*;
import java.awt.*;

public class ScrollingBanner2 extends ScrollingBanner {

    public ScrollingBanner2(String[] params) {
        super(params);
    }
    @Override
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
        x = -dim.width;
        y = font.getSize();
        // initialize the animation timer
        timer = new Timer(delay, e -> repaint());
    }
    @Override
    protected void paintComponent(Graphics g){
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int length = fm.stringWidth(text);

        // adjust the position of the text from the previous frame
        x = x + offset;
        // if the text is completely off to the left end
        // move the position back to the right end
        if (x > dim.width) {
            x = -length;
        }
        // set the pen color and draw the backround
        g.setColor(Color.BLACK);
        g.fillRect(0,0, dim.width, dim.height);

        // set the pen color and draw the text
        g.setColor(Color.GREEN);
        g.drawString(text, x, y);
    }
    public static void main(String[] args){
        new ScrollingBanner2(args).run();
    }
}
