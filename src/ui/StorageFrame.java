package ui;

import domain.ShapeController;

import javax.swing.*;
import java.awt.*;

public class StorageFrame extends JFrame implements Runnable {
    public StorageFrame(ShapeController shapeController) {
        super("Choose your preferred storage!");
        setPreferredSize(new Dimension(350, 350));

        setContentPane(new StoragePanel(shapeController));

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void run() {

    }
}
