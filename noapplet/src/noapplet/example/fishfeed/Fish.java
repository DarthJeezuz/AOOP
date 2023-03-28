package noapplet.example.fishfeed;

import java.awt.*;
import java.util.Random;

public abstract class Fish implements OceanStuff{
    private boolean isFish;
    private boolean sinkFish;
    private int x, y, offset, radius, dim;
    private Image fish;
    private Random ran = new Random();
    public Fish(int x, int y, int offset, int radius, int dim, Image fish, boolean isFish, boolean sinkFish){
        this.x = x;
        this.y = y;
        this.offset = offset;
        this.radius = radius;
        this.dim = dim;
        this.fish = fish;
        this.isFish = isFish;
        this.sinkFish = sinkFish;
    }


    public abstract void draw(Graphics g, boolean droppingCandy);
    protected void movement(boolean droppingCandy, boolean sinkFish){
        int length = radius*2;
        // moving fishes
        if(isFish){
            if(sinkFish){
                y = y + offset;
                if(y > dim + length){
                    // reset fish
                    radius = ran.nextInt(15,80);
                    y = ran.nextInt(100,600);
                    offset = ran.nextInt(5,15);
                    fish = Main.getFishImage();
                    x = dim;
                    Main.resetSinkFish();

                }
            }
            else{
                x = x - offset;
                if(x < -length){
                    radius = ran.nextInt(15,80);
                    y = ran.nextInt(100,600);
                    offset = ran.nextInt(5,15);
                    fish = Main.getFishImage();
                    x = dim;
                }
            }

        }
        // moving candy
        if(droppingCandy && !isFish){
            y = y + offset;
            if(y > dim+length){
                // candy reached the bottom, reset candy, reset boolean droppingCandy
                resetC();
            }
        }


    }
    public int getX(){return x;}
    public int getY(){return y;}
    public int getRadius(){return radius;}
    public Image getFish(){return fish;}
    public boolean getIsFish(){return isFish;}
    public boolean getSinkFish(){return sinkFish;}
    public void sinkF(){sinkFish = true;}
    public void resetC(){y = 10; Main.resetCandy();}
    public void incrimentScore(){Main.incScore();}
}
