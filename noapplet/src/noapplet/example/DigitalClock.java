package noapplet.example;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import java.util.Calendar;

public class DigitalClock extends noapplet.NoApplet{

    protected Timer timer;
    protected Font font = new Font("Monospaced", Font.BOLD, 48);
    protected Color color = Color.GREEN;

    public DigitalClock(String[] params){
        timer = new Timer(1000, event -> repaint());

    }

    @Override
    public void start(){
        timer.start();
    }
    @Override
    public void stop(){
        timer.stop();
    }
//    @Override
    protected void paintComponent(Graphics g){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,300, 300);
        g.setFont(font);
        g.setColor(color);
        g.drawString(String.format("%02d:%02d:%02d", hour, minute, second), 10, 60);
    }
    public static void main(String[] args){
        new DigitalClock(args).run();
    }
}
