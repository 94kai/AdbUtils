package com.xk.mutils.view;

import com.xk.mutils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * 设置变量区
 */
public class VariateArea extends JPanel {

    public VariateArea(int x, int y, int width, int height, String defaultVariate) {
        setBounds(x, y, width, height);
        JTextArea textArea = new JTextArea();
        setLayout(null);
        textArea.setBounds(0, 0, width, height);
        textArea.setText(defaultVariate);
        add(textArea);
        Utils.setVariateArea(textArea);

    }


}
