package ui;

import domain.ShapeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoragePanel extends JPanel implements ActionListener {
    private JButton buttonDb;
    private JButton buttonTxt;
    private JButton buttonJson;
    private ShapeController shapeController;

    StoragePanel(ShapeController shapeController) {
        this.shapeController = shapeController;
        setLayout(new GridLayout(4, 1, 0,10));
        setBorder(new EmptyBorder(30, 30, 30, 30));

        add(new Label("Choose preffered storage:"));

        buttonDb = new JButton("DB");
        buttonDb.addActionListener(this);
        add(buttonDb);

        buttonTxt = new JButton("Text");
        buttonTxt.addActionListener(this);
        add(buttonTxt);

        buttonJson = new JButton("Json");
        buttonJson.addActionListener(this);
        add(buttonJson);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDb) {
            System.out.println("Button DB pressed");
        } else if (e.getSource() == buttonTxt) {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
            new UIFrame(shapeController);
        } else if (e.getSource() == buttonJson) {
            System.out.println("Button Json pressed");
        }
    }
}
