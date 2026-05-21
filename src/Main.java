import javax.swing.SwingUtilities;

import view.gui.MainFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });

    }
}