package noapplet.example.fishfeed;

import java.awt.*;

public class Fishies extends Fish{
    public Fishies(int x, int y, int offset, int radius, int dim, Image fish, boolean isFish, boolean sinkFish){
        super(x,y,offset,radius,dim,fish,isFish,sinkFish);
    }

    @Override
    public void draw(Graphics g, boolean droppingCandy) {
        int x = getX();
        int y = getY();
        int radius = getRadius();
        Image fish = getFish();
        boolean isFish = getIsFish();
        boolean sinkFish = getSinkFish();
        super.movement(droppingCandy,sinkFish);
        if(sinkFish && !isFish){
            y = 10;
            g.drawImage(fish, x, y, radius, radius, null);
        }
        else{
            g.drawImage(fish, x, y, radius, radius, null);
        }
    }

}
