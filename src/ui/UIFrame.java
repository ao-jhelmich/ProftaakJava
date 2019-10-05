package ui;

import domain.ShapeController;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame implements Runnable {
    public UIFrame(ShapeController shapeController) {
        super("Sphere application");
        setPreferredSize(new Dimension(1000, 600));

        setContentPane(new UIPanel(this, shapeController));

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void run() {

    }
}
