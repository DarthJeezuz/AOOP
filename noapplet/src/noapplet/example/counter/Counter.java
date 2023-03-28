package noapplet.example.counter;

import noapplet.NoApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Counter extends NoApplet {
    public Counter(){
        super();
        var model = new CounterModel();
        var display = new JTextField(10);
        var button = new JButton("Increment");
        var dButton = new JButton("Decrement");
        var clrButton = new JButton("Clear");
        display.setEditable(false);  // To not allow for direct editing in display box
        add(new JLabel("Value:"));
        add(display);
        add(button);
        add(dButton);
        add(clrButton);
        display.setText(Integer.toString(model.value()));
        display.setHorizontalAlignment(JTextField.CENTER); // Centers the value within the text field
        Color def = button.getBackground();
        // change color of 'Increment'
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                button.setBackground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e){
                button.setBackground(def);
            }
        });
        button.addActionListener(e -> {
            model.increment();
            display.setText(Integer.toString(model.value()));
        });
        dButton.addActionListener(e -> {
            model.decrement();
            display.setText(Integer.toString(model.value()));
        });
        clrButton.addActionListener(e -> {
            model.clear();
            display.setText(Integer.toString(model.value()));
        });

    }
    public static void main(String[] args){
        new Counter().run();
    }
}

class CounterModel{
    private int value;

    public int value(){
        return value;
    }

    public void increment(){
        value++;
    }

    public void decrement(){
        value--;
    }

    public void clear(){
        value = 0;
    }
}
