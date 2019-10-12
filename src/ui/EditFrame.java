package ui;

import domain.ShapeController;
import shapes.Shape;

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

    public EditFrame(UIFrame uiFrame, ShapeController shapeController, Shape shape) {
        super("Add shape");
        setPreferredSize(new Dimension(350, 400));

        setContentPane(new EditPanel(uiFrame, this, shapeController, shape));

        pack();
        setLocationRelativeTo(uiFrame);
        setVisible(true);
    }

    @Override
    public void run() {

    }
}
