package ui;

import domain.ShapeController;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame implements Runnable {
    private UIPanel uiPanel;

    public UIFrame(ShapeController shapeController) {
        super("Sphere application");
        setPreferredSize(new Dimension(1000, 600));

        this.uiPanel = new UIPanel(this, shapeController);
        setContentPane(uiPanel);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public UIPanel getUIPanel() {
        return uiPanel;
    }

    @Override
    public void run() {

    }
}
