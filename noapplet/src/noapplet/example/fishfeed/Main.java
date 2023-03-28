package noapplet.example.fishfeed;

import noapplet.example.AnimationNoApplet;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Main extends AnimationNoApplet {
    private static final int NUM_FISH = 5;
    static Random ran = new Random();
    FishCollide col;
    Font font = new Font("Monospaced", Font.BOLD, 25);
    protected java.util.List<OceanStuff> fishList = new ArrayList<>();
    protected static java.util.List<Image> fishImages = new ArrayList<>();
    protected static java.util.List<Bubbles> bubbles = new ArrayList<>();
    protected static OceanStuff candie;
    protected static Image candy;
    protected static boolean droppingCandy = false;
    protected static boolean sinkFish = false;
    protected static int score = 0;
    protected static boolean scored = false;
    static File wavFile = new File("C:\\Users\\Master\\CodingWorkspace\\newproj\\noapplet\\res\\woohoo.wav");


    public Main(String[] args){
        super(args);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dropCandy();
            }
        });
        fishImages.add(getImage("fish1.png"));
        fishImages.add(getImage("fish2.png"));
        fishImages.add(getImage("fish3.png"));
        fishImages.add(getImage("fish4.png"));
    }

    private void dropCandy() {
        droppingCandy = true;
    }

    @Override
    protected void initAnimation(){
        candy = getImage("candy.png");
        candie = new Fishies(20,10,10, 40,dim.height,candy,false,sinkFish); // adding candy
        for(int i = 0; i < 50; i++){
            int radius = ran.nextInt(5,25);
            int x = ran.nextInt(2,650);
            int y = dim.height;
            int offset = ran.nextInt(5,15); // speed
            bubbles.add(new Bubbles(x,y,offset,radius,dim.height));
        }
        for(int i = 0; i < NUM_FISH; i++){
            int radius = ran.nextInt(15,80);
            int x = dim.width;
            int y = ran.nextInt(100, 600);
            int offset = ran.nextInt(5,15);
            int dy = ran.nextInt(1,5);
            Image fish = fishImages.get(ran.nextInt(fishImages.size()));
            fishList.add(new Fishies(x, y, offset, radius, dim.width, fish, true,sinkFish));
        }
        col = new FishCollide(fishList, candie);

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(getImage("background.png"), 0, 0,this);
        g.setColor(Color.GREEN);
        g.drawString(String.format("Score: " + score), 500,20);
        if(scored){
            play("C:\\Users\\Master\\CodingWorkspace\\newproj\\noapplet\\src\\noapplet\\example\\fishfeed\\woohoo.wav\r\n");
            scored = false;
        }
        sinkFish = col.detect();

        candie.draw(g,droppingCandy);

        for(Bubbles b: bubbles){
            b.drawB(g);
        }
        for(OceanStuff f: fishList){
            f.draw(g,droppingCandy);
        }
    }
    public static Image getFishImage(){
        return fishImages.get(ran.nextInt(fishImages.size()));
    }
    public static void resetCandy(){
        droppingCandy = false;
    }
    public static void resetSinkFish(){
        sinkFish = false;
    }
    public static void incScore(){
        scored = true;
        score++;
    }



    public static void main(String[] args){
        new Main(new String[] {"width=600","height=700","delay=100"}).run();
    }
}

