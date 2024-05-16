package SnakeGame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            JFrame parentFrame = new JFrame();
            EntryForm form = new EntryForm(parentFrame);
            form.setVisible(true);
        }
    });
}
}
