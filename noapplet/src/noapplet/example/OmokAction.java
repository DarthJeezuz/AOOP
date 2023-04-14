package noapplet.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

class OmokAction extends AbstractAction {
    private Runnable runnable;

    public OmokAction(String name, ImageIcon icon, String descr, int mnemonic, int accelerator,
                      Runnable runnable) {
        super(name, icon);
        this.runnable = runnable;
        putValue(SHORT_DESCRIPTION, descr);
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(accelerator, InputEvent.ALT_DOWN_MASK));
    }

    public void actionPerformed(ActionEvent e) {
        runnable.run();
    }
}

