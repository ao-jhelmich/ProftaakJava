package ui;

import domain.Reader;
import shapes.Shape;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class UI {
    private Collection<JComponent> componentList;
    private Reader reader;
    private Shape selectedShape;

    public UI() {
        this.componentList = new ArrayList<>();
        this.reader = new Reader();
    }

    public void run()
    {
        JFrame f = new JFrame("Sphere application");
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Add shape");
        button.setBounds(290,10,100,30);
        button.addActionListener(e -> new EditPane(f).run());
        componentList.add(button);

        JTextArea shapeResult = new JTextArea();
        shapeResult.setBounds(10,200,150,20);
        componentList.add(shapeResult);

        JList sphereList = new JList(reader.readAll().toArray());
        sphereList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedShape = (Shape) sphereList.getSelectedValue();
                shapeResult.setText("" + selectedShape.calculateVolume());
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(sphereList);
        scrollPane.setBounds(10,50,380,100);
        componentList.add(scrollPane);

        show(f);
    }

    private void show(JFrame f) {
        for (JComponent c: componentList) {
            f.getContentPane().add(c);
        }

        f.setLayout(null);
        f.setVisible(true);
    }
}
