package noapplet.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

//import static com.sun.java.swing.action.ActionUtilities;

public class Main extends JPanel{
    public static Board board;
    public static BoardPanel BP = new BoardPanel(board);
    private static final String IMAGE_DIR = "images/";
    protected static boolean mouseBound = false;
    protected String selectedItem;
    boolean humanMode;
    private static JLabel playerTurn = new JLabel(" turn");
    public JLabel getLabel(){return playerTurn;}

    public static void main(String[] args) {
        ImageIcon PLAY_ICON = new ImageIcon("C:\\Users\\Master\\CodingWorkspace\\newproj\\noapplet\\res\\play.png");
        ImageIcon ABOUT_ICON = new ImageIcon("C:\\Users\\Master\\CodingWorkspace\\newproj\\noapplet\\res\\about.png");
        ImageIcon WEB_PAGE = new ImageIcon("C:\\Users\\Master\\CodingWorkspace\\newproj\\noapplet\\res\\wificon.png");
        PLAY_ICON.setImage(PLAY_ICON.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
        ABOUT_ICON.setImage(ABOUT_ICON.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
        WEB_PAGE.setImage(WEB_PAGE.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
        OmokGameplay omokGameplay = new OmokGameplay(new Board());

        var topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        var panel = new JPanel();
        panel.setLayout(new BorderLayout());
        var strategyPanel = new JPanel();
        strategyPanel.setLayout(new FlowLayout());
        String s[] = {"Human", "Dumb Bot", "Smart Bot"};
        JComboBox<String> jcb = new JComboBox<>(s);
        var play = new JButton("Play");

        var rules = new JTextArea();

        var frame = new JFrame("Omok");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(350, 450));

        // Add Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menu.getAccessibleContext().setAccessibleDescription("Game menu");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Play", KeyEvent.VK_P);
        JMenuItem menuItem1 = new JMenuItem("About", KeyEvent.VK_A);
        menuItem.setIcon(PLAY_ICON);
        menuItem1.setIcon(ABOUT_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Play a new game");
        menuItem1.getAccessibleContext().setAccessibleDescription("About the game");
        menu.add(menuItem);
        menu.add(menuItem1);

        // Add Toolbar
        JToolBar toolBar = new JToolBar("Omok");
        JButton playButton = new JButton(PLAY_ICON);
        JButton aboutButton = new JButton(ABOUT_ICON);
        JButton webButton = new JButton(WEB_PAGE);
        playButton.setToolTipText("Play a new game");
        aboutButton.setToolTipText("About the game");
        webButton.setToolTipText("Web service");
        playButton.setFocusPainted(false);
        aboutButton.setFocusPainted(false);
        webButton.setFocusPainted(false);
        toolBar.add(playButton);
        toolBar.add(aboutButton);
        toolBar.add(webButton);

        strategyPanel.add(play);
        strategyPanel.add(new JLabel("Strategy: "));
        strategyPanel.add(jcb);

        // Adding Menu and toolbar
        topPanel.add(menuBar, BorderLayout.NORTH);
        topPanel.add(toolBar, BorderLayout.SOUTH);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(strategyPanel, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.NORTH);

        frame.add(BP, BorderLayout.CENTER);
        frame.add(playerTurn, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        play.addActionListener(new ActionListener() {
            String selectedItem = (String) jcb.getSelectedItem();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(selectedItem, "Human")) {
                    // call player for human game
                    System.out.println("A click happened to start a human game");
                    Controller.Start(1);
                }
                else {
                    //call player for com game
                    Controller.Start(0);
                }
            }
        });
        menuItem.addActionListener(e -> {
            String selectedItem = (String) jcb.getSelectedItem();
            if(play.isSelected()){
                //starts a new game
                if(Objects.equals(selectedItem, "Human")){
                    // call player for human game
                    Controller.Start(1);
                }
                else {
                    //call player for com game
                    Controller.Start(0);
                }
            }

        });
        playButton.addActionListener(e -> {
            String selectedItem = (String) jcb.getSelectedItem();
            if(play.isSelected()){
                //starts a new game
                if(Objects.equals(selectedItem, "Human")){
                    // call player for human game
                    Controller.Start(1);
                }
                else {
                    //call player for com game
                    Controller.Start(0);
                }
            }
        });
        aboutButton.addActionListener(e -> {
            JOptionPane.showConfirmDialog(rules,"Make selection by first entering the number for the desired ROW\n" +
                    "Then enter the number for the desired COLUMN\n" +
                    "The first person to go will have the designation 'X' and the other will have the designation 'O'\n" +
                    "The first player to have an unbroken line of 5 wins!","About",JOptionPane.OK_OPTION);
        });
        webButton.addActionListener(e -> {

        });



    }


}
