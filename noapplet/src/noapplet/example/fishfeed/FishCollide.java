package noapplet.example.fishfeed;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FishCollide {
    List<OceanStuff> fish = new ArrayList<>();
    OceanStuff candie;

    public FishCollide(List<OceanStuff> in, OceanStuff candie){
        this.fish = in;
        this.candie = candie;
    }
    public boolean detect(){
        for(OceanStuff f : fish){

            if(getBounds(f).intersects(getBounds(candie))){
                // make fish sink and candy disappear and play sound
                candie.resetC();
                f.sinkF();
                f.incrimentScore();

                return true;
            }
        }
        return false;
    }
    public Rectangle getBounds(OceanStuff o){
        int offset = o.getRadius();
        return new Rectangle(o.getX()-offset, o.getY()-offset, offset/2, offset/2);
    }


}
