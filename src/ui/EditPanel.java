package ui;

import domain.ShapeController;
import domain.UpdatableView;
import shapes.Shape;
import shapes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class EditPanel extends JPanel {
    private UIFrame uiFrame;
    private EditFrame editFrame;
    private ShapeController shapeController;

    private JComboBox shapeBox;
    private InputPanel inputPanel;

    private class ShapeBox extends JPanel implements UpdatableView {
        private Collection<String> shapeList;

        ShapeBox() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); //TODO Fix layout
            shapeList = new ArrayList<>();
            shapeList.add("cone");
            shapeList.add("cube");
            shapeList.add("cylinder");
            shapeList.add("sphere");
            shapeList.add("squarePyramid");

            add(new Label("Shape type"));

            shapeBox = new JComboBox(shapeList.toArray());
            shapeBox.setEditable(false);
            add(shapeBox);

            shapeBox.addActionListener(e -> {
                Shape shape = shapeController.getEmptyShape(shapeBox.getSelectedItem().toString());
                inputPanel.setInputs(shape.getFormInputs());
            });
        }

        ShapeBox(Shape shape) {
            this();
            shapeBox.setSelectedItem(shape.getType());
            inputPanel.fillInputs(shape);
            updateView();
        }

        @Override
        public void updateView() {
            invalidate();
            validate();
            repaint();
        }
    }

    private class InputPanel extends JPanel implements UpdatableView {
        private ArrayList<String> labels;

        InputPanel() {
            setLayout(new SpringLayout());

            labels = new ArrayList<String>();
            labels.add("Radius:");
            labels.add("Height:");

            setInputs(labels);
        }

        public void setInputs(ArrayList<String> labels) {
            this.labels = labels; //Set new labels
            int numPairs = labels.size();
            removeAll(); //Clear InputPanel

            for (int i = 0; i < numPairs; i++) {
                JLabel l = new JLabel(labels.get(i), JLabel.TRAILING);
                add(l);
                JTextField textField = new JTextField();
                l.setLabelFor(textField);
                add(textField);
            }

            SpringUtilities.makeCompactGrid(this,
                    numPairs, 2, //rows, cols
                    6, 1,        //initX, initY
                    6, 2);       //xPad, yPad
            //TODO Fix layout

            updateView(); //Update to show new Inputs
        }

        @SuppressWarnings("Duplicates")
        public void fillInputs(Shape shape) {
            int count = 0;
            for (Component component : getComponents()) {
                if (component instanceof JTextField) {
                    count++;
                    if (shape instanceof Cone) {
                        Cone cone = (Cone) shape;
                        if (count == 1) {
                            ((JTextField) component).setText(cone.getHeight() + "");
                        } else if (count == 2) {
                            ((JTextField) component).setText(cone.getRadius() + "");
                        }
                    } else if (shape instanceof Cube) {
                        Cube cube = (Cube) shape;
                        if (count == 1) {
                            ((JTextField) component).setText(cube.getLength() + "");
                        } else if (count == 2) {
                            ((JTextField) component).setText(cube.getWidth() + "");
                        } else if (count == 3) {
                            ((JTextField) component).setText(cube.getHeight() + "");
                        }
                    } else if (shape instanceof Cylinder) {
                        Cylinder cylinder = (Cylinder) shape;
                        if (count == 1) {
                            ((JTextField) component).setText(cylinder.getHeight() + "");
                        } else if (count == 2) {
                            ((JTextField) component).setText(cylinder.getRadius() + "");
                        }
                    } else if (shape instanceof Sphere) {
                        Sphere sphere = (Sphere) shape;
                        if (count == 1) {
                            ((JTextField) component).setText(sphere.getRadius() + "");
                        }
                    } else if (shape instanceof SquarePyramid) {
                        SquarePyramid squarePyramid = (SquarePyramid) shape;
                        if (count == 1) {
                            ((JTextField) component).setText(squarePyramid.getLength() + "");
                        } else if (count == 2) {
                            ((JTextField) component).setText(squarePyramid.getWidth() + "");
                        } else if (count == 3) {
                            ((JTextField) component).setText(squarePyramid.getHeight() + "");
                        }
                    }
                }
            }
        }

        @Override
        public void updateView() {
            invalidate();
            validate();
            repaint();
        }
    }

    private class ActionPanel extends JPanel {
        ActionPanel() {
            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setAutoCreateGaps(true);

            JButton buttonCancel = new JButton("Cancel");
            buttonCancel.addActionListener(e -> editFrame.dispose());

            JButton buttonSave = new JButton("Save shape");
            buttonSave.addActionListener(e -> {
                ArrayList<Component> components = new ArrayList<>();
                components.addAll(Arrays.asList(inputPanel.getComponents()));
                components.add(shapeBox);
                Shape shape = shapeController.getShape(uiFrame.getUIPanel().getSphereList().getSelectedValue(), components);
                shapeController.writeShape(shape);


                //TODO Make sure uiPanel gets updated

                System.out.println("Save");
                editFrame.dispose();
            });

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addComponent(buttonCancel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSave, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(buttonCancel)
                                            .addComponent(buttonSave)
                            )
            );

            layout.linkSize(SwingConstants.HORIZONTAL, buttonCancel, buttonSave);
        }
    }

    public EditPanel(UIFrame uiFrame, EditFrame editFrame, ShapeController shapeController) {
        this(uiFrame, editFrame, shapeController, null);
    }

    public EditPanel(UIFrame uiFrame, EditFrame editFrame, ShapeController shapeController, Shape shape) {
        this.uiFrame = uiFrame;
        this.editFrame = editFrame;
        this.shapeController = shapeController;
        this.inputPanel = new InputPanel();

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 0.0; //Use only own space
        c.gridx = 0;
        c.gridy = 0;
        if (shape != null) {
            add(new ShapeBox(shape), c);
        } else {
            add(new ShapeBox(), c);
        }

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(30, 0, 0, 0);  //Top padding
        c.weightx = 1.0;
        c.weighty = 1.0; //Take all extra space
        c.gridx = 0;
        c.gridy = 1;
        add(inputPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(30, 0, 0, 0);  //Top padding
        c.weightx = 1.0;
        c.weighty = 0.0; //These only take their own space
        c.gridx = 0;
        c.gridy = 2;
        add(new ActionPanel(), c);
    }
}
