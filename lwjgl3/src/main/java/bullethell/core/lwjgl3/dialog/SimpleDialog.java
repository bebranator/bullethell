package bullethell.core.lwjgl3.dialog;

import javax.swing.*;

public class SimpleDialog extends JFrame {
    public SimpleDialog(String text, String title) {
        super();
        setTitle(title);
        JOptionPane pane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
            new Object[]{}, null);

        setContentPane(pane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

    }
    public void shw() {
        new Thread(() -> setVisible(true)).start();
    }
}
