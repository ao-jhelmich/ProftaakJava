package ui;

import domain.ShapeController;
import domain.UpdatableView;
import shapes.Shape;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIPanel extends JPanel implements UpdatableView {
    private UIFrame uiFrame;
    private ShapeController shapeController;
    private EditFrame editFrame;

    private SphereListPanel sphereListPanel;
    private JTextArea shapeTextArea;
    private JTextArea totalTextArea;

    private class SphereListPanel extends JPanel implements UpdatableView {
        private JScrollPane scrollPane;

        SphereListPanel() {
            setLayout(new BorderLayout());
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(addNewSphereList());
            add(scrollPane);
        }

        public JList<Shape> getSphereList() {
            return (JList<Shape>) scrollPane.getViewport().getView();
        }

        public void setSphereList() {
            scrollPane.setViewportView(addNewSphereList());
        }

        private JList<Shape> addNewSphereList() {
            JList<Shape> sphereList = new JList(shapeController.getShapeList().toArray());
            sphereList.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        Shape selectedShape = sphereList.getSelectedValue();
                        if (editFrame != null) {
                            editFrame.dispose();
                        }
                        editFrame = new EditFrame(uiFrame, shapeController, selectedShape);
                    }
                }
            });
            sphereList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    Shape selectedShape = sphereList.getSelectedValue();
                    if (selectedShape != null) {
                        shapeTextArea.setText("" + (double) Math.round(selectedShape.calculateVolume() * 100) / 100);
                    } else {
                        System.out.println("Selected shape not found");
                    }
                }
            });
            return sphereList;
        }

        @Override
        public void updateView() {
            setSphereList();
            invalidate();
            validate();
            repaint();
        }
    }

    private class ButtonPanel extends JPanel {
        ButtonPanel() {
            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setAutoCreateGaps(true);

            JButton addButton = new JButton("Add shape");
            addButton.addActionListener(e -> {
                if (editFrame != null) {
                    editFrame.dispose();
                }
                editFrame = new EditFrame(uiFrame, shapeController);
            });

            JButton deleteButton = new JButton("Delete shape");
            deleteButton.addActionListener(e -> {
                if (!sphereListPanel.getSphereList().isSelectionEmpty()) {
                    shapeController.deleteShape(sphereListPanel.getSphereList().getSelectedValue());
                    sphereListPanel.updateView();
                }
            });
            JButton calculateButton = new JButton("Calculate total volume");
            calculateButton.addActionListener(e -> totalTextArea.setText("" + shapeController.calculateTotalVolume()));

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addComponent(addButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calculateButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(addButton)
                                            .addComponent(deleteButton)
                                            .addComponent(calculateButton)
                            )
            );

            layout.linkSize(SwingConstants.HORIZONTAL, addButton, deleteButton, calculateButton);
        }
    }

    private class ResultPanel extends JPanel {
        ResultPanel() {
            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setAutoCreateGaps(true);

            Font font = new Font("Calibri Light", Font.PLAIN, 18);

            JComponent shapeLabel = new Label("Volume:");
            shapeTextArea = new JTextArea(1, 20);
            shapeTextArea.setEnabled(false);
            shapeTextArea.setFont(font);
            shapeTextArea.setDisabledTextColor(Color.black);

            JComponent totalLabel = new Label("Total volume:");
            totalTextArea = new JTextArea(1, 20);
            totalTextArea.setEnabled(false);
            totalTextArea.setFont(font);
            totalTextArea.setDisabledTextColor(Color.black);

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(shapeLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(shapeTextArea, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            )
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(totalLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(totalTextArea, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            )
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(shapeLabel)
                                            .addComponent(totalLabel)
                            )
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(shapeTextArea)
                                            .addComponent(totalTextArea)
                            )
            );

            //layout.linkSize(SwingConstants.HORIZONTAL, shapeTextArea, totalTextArea);
        }
    }

    UIPanel(UIFrame uiFrame, ShapeController shapeController) {
        this.uiFrame = uiFrame;
        this.shapeController = shapeController;
        SphereListPanel sphereListPanel = new SphereListPanel();
        this.sphereListPanel = sphereListPanel;

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0; //Take all extra space
        c.gridx = 0;
        c.gridy = 0;
        add(sphereListPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(30, 0, 0, 0);  //Top padding
        c.weightx = 1.0;
        c.weighty = 0.0; //These only take their own space
        c.gridx = 0;
        c.gridy = 1;
        add(new ButtonPanel(), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(30, 0, 0, 0);  //Top padding
        c.weightx = 1.0;
        c.weighty = 0.0; //These only take their own space
        c.gridx = 0;
        c.gridy = 2;
        add(new ResultPanel(), c);
    }

    public JList<Shape> getSphereList() {
        return sphereListPanel.getSphereList();
    }

    @Override
    public void updateView() {
        sphereListPanel.updateView();
    }
}
