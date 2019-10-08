package ui;

import domain.ShapeController;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame implements Runnable {
    public EditFrame(UIFrame uiFrame, ShapeController shapeController) {
        super("Add shape");
        setPreferredSize(new Dimension(350, 400));

        setContentPane(new EditPanel(uiFrame, this, shapeController));

        pack();
        setLocationRelativeTo(uiFrame);
        setVisible(true);
    }

    @Override
    public void run() {

    }
}
