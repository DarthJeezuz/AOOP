package noapplet.example.fishfeed;

import java.awt.*;
import java.util.Random;

public class Bubbles{
    static Random ran = new Random();
    int x,y,offset,radius,dim;
    public Bubbles(int x, int y, int offset, int radius, int dim){
        this.x = x;
        this.y = y;
        this.offset = offset;
        this.radius = radius;
        this.dim = dim;

    }

    public void move(){
        y = y - offset;
        if(y < 0){
            radius = ran.nextInt(5,25);
            x = ran.nextInt(2,650);
            y = 700;
            offset = ran.nextInt(5,15); // speed
        }
    }
    public void drawB(Graphics g) {
        move();
        g.setColor(Color.WHITE);
        g.drawOval(x,y,radius,radius);
    }
}
