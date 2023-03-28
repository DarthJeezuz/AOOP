package noapplet.example.fishfeed;

import java.awt.*;

public interface OceanStuff {
    void draw(Graphics g, boolean droppingCandy);
    int getX();
    int getY();
    int getRadius();
    void sinkF();
    void resetC();
    void incrimentScore();


}
