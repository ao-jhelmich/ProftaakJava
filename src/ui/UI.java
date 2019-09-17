package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class UI {
    private Collection<JComponent> componentList;

    public UI() {
        this.componentList = new ArrayList<>();
    }

    public void run()
    {
        JFrame f = new JFrame("Sphere application");
        f.setSize(400, 300);

        JButton button = new JButton("Add shape");
        button.setBounds(290,10,100,30);
        button.addActionListener(e -> new EditPane().run());
        componentList.add(button);

        Collection<String> spheres = new ArrayList<>();
        spheres.add("test");
        spheres.add("nina");
        spheres.add("jasper");
        spheres.add("woop");
        spheres.add("dora");

        JLabel selectedShape = new JLabel("Selected shape: ");
        selectedShape.setBounds(10,150,150,30);
        componentList.add(selectedShape);

        JList sphereList = new JList(spheres.toArray());
        sphereList.setBounds(10,50,380,100);
        sphereList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedValue = sphereList.getSelectedValue();
                selectedShape.setText("Selected shape: " + selectedValue);
            }
        });

        componentList.add(sphereList);

        JTextArea calculateResult = new JTextArea();
        calculateResult.setBounds(10,200,150,20);
        componentList.add(calculateResult);

        JButton calculateVolume = new JButton("Calculate volume");
        calculateVolume.setBounds(230,150,150,30);
        calculateVolume.addActionListener(e -> {
            calculateResult.setText("Het volume is:" + 1239012093);
        });

        componentList.add(calculateVolume);

        for (JComponent c: componentList) {
            f.getContentPane().add(c);
        }

        f.setLayout(null);
        f.setVisible(true);
    }
}
