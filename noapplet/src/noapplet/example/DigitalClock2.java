package noapplet.example;

import java.awt.Color;

public class DigitalClock2 extends DigitalClock {
    public DigitalClock2(String[] params) {
        super(params);
    }

    @Override
    public void init() {
        String param = getParameter("color");
        if ("red".equals(param)) {
            color = Color.RED;
        } else if ("blue".equals(param)) {
            color = Color.BLUE;
        } else if ("yellow".equals(param)) {
            color = Color.YELLOW;
        } else {
            color = Color.GREEN;
        }
    }

    public static void main(String[] args) {
        new DigitalClock2(new String[]{"color=red"}).run();

//        new DigitalClock2(args).run();
    }
}
