package ui;

import javax.swing.*;

public class Label extends JLabel {

    Label(String text) {
        this(text, 0,0,80,30);
    }

    Label(String text, Integer x, Integer y, Integer width, Integer height) {
        setText(text);
        setBounds(x, y, width, height);
    }
}
